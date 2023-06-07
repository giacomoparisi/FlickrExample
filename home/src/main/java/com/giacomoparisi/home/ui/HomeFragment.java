package com.giacomoparisi.home.ui;

import static androidx.core.content.ContextCompat.getSystemService;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.giacomoparisi.core.ui.recyclerview.adapter.ItemsAdapter;
import com.giacomoparisi.core.ui.recyclerview.decoration.AdaptiveSpacingItemDecoration;
import com.giacomoparisi.core.ui.recyclerview.item.Item;
import com.giacomoparisi.core.ui.recyclerview.layoutmanagers.NpaGridLayoutManager;
import com.giacomoparisi.domain.datatypes.paging.PagedList;
import com.giacomoparisi.home.R;
import com.giacomoparisi.home.data.HomeState;
import com.giacomoparisi.home.data.HomeViewModel;
import com.giacomoparisi.home.data.actions.HomeAction;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private Disposable stateDisposable;
    private HomeViewModel viewModel;
    private RecyclerView recyclerView;
    private EditText editText;
    private ProgressBar loading;
    private final ItemsAdapter adapter = new ItemsAdapter(PhotoViewHolder.factory());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        viewModel.dispatch(HomeAction.search("cat"));

    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // find views
        recyclerView = view.findViewById(R.id.recycler_view);
        loading = view.findViewById(R.id.loading);
        editText = view.findViewById(R.id.search);

        // setup
        setupRecyclerView();
        setupSearch();

        // bind state
        stateDisposable =
                viewModel.state
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::updateView);

    }

    @Override
    public void onDestroyView() {
        stateDisposable.dispose();
        stateDisposable = null;
        super.onDestroyView();
    }

    private void setupRecyclerView() {
        NpaGridLayoutManager layoutManager =
                new NpaGridLayoutManager(
                        requireContext(),
                        2,
                        RecyclerView.VERTICAL,
                        false
                );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new AdaptiveSpacingItemDecoration(50, true));
        recyclerView.setAdapter(adapter);
        adapter.setNextPageListener(() -> viewModel.dispatch(HomeAction.nextPhotosPage()));
    }

    private void setupSearch() {
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.dispatch(HomeAction.search(editText.getEditableText().toString()));
                InputMethodManager imm =
                        getSystemService(requireContext(), InputMethodManager.class);
                if (imm != null) imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        });
    }

    private void updateView(HomeState state) {

        PagedList<PhotoItem> photos = state.getPhotos().value();
        if (photos != null && photos.getData() != null) {
            List<Item> items = new ArrayList<>(photos.getData());
            adapter.submitList(items);
            // TODO: optimize list refresh
            adapter.notifyDataSetChanged();
        }

        loading.setVisibility(
                state.getPhotos().isLoading() ?
                        View.VISIBLE :
                        View.GONE
        );

    }

}
