package com.giacomoparisi.home.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.giacomoparisi.core.ui.racyclerview.adapter.ItemsAdapter;
import com.giacomoparisi.core.ui.racyclerview.decoration.AdaptiveSpacingItemDecoration;
import com.giacomoparisi.core.ui.racyclerview.item.Item;
import com.giacomoparisi.core.ui.racyclerview.layoutmanagers.NpaGridLayoutManager;
import com.giacomoparisi.home.R;
import com.giacomoparisi.home.data.HomeState;
import com.giacomoparisi.home.data.HomeViewModel;
import com.giacomoparisi.home.data.actions.HomeAction;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private final CompositeDisposable compositeDisposable =
            new CompositeDisposable();
    private HomeViewModel viewModel;
    private RecyclerView recyclerView;
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
        recyclerView = view.findViewById(R.id.recycler_view);
        setupRecyclerView();

        compositeDisposable.add(
                viewModel.state
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::updateView)
        );

    }

    @Override
    public void onDestroyView() {
        compositeDisposable.dispose();
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
    }

    private void updateView(HomeState state) {

        List<PhotoItem> photos = state.getPhotos().value();
        if (photos != null) {
            List<Item> items = new ArrayList<>(photos);
            adapter.submitList(items);
            adapter.notifyDataSetChanged();
        }

    }

}
