package com.giacomoparisi.detail.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.giacomoparisi.detail.R;

public class DetailFragment extends Fragment {

    private ImageView image;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        image = view.findViewById(R.id.detail_image);
        setupImage();
    }

    private void setupImage() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String url = arguments.getString("url");
            if (url != null)
                Glide.with(image.getContext())
                        .load(url)
                        .into(image);
        }
    }

}
