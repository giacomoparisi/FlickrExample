package com.giacomoparisi.data.repositories.photo;

import android.content.Context;

import com.giacomoparisi.data.R;
import com.giacomoparisi.data.repositories.photo.network.PhotoApi;
import com.giacomoparisi.data.repositories.photo.network.response.PhotoResponse;
import com.giacomoparisi.data.repositories.photo.network.response.PhotosResponse;
import com.giacomoparisi.domain.usecases.photo.PhotoRepository;
import com.giacomoparisi.entities.photo.Photo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.rxjava3.core.Single;

public class PhotoRepositoryImpl implements PhotoRepository {

    private final PhotoApi api;
    private final Context application;

    @Inject
    public PhotoRepositoryImpl(PhotoApi api, @ApplicationContext Context application) {
        this.api = api;
        this.application = application;
    }

    @Override
    public Single<List<Photo>> searchPhotos(String text, Integer page, Integer pageSize) {
        return api.searchPhotos(
                application.getString(R.string.flicker_api_key),
                text,
                page,
                pageSize
        ).map(PhotosResponse::toPhotoList);
    }

}
