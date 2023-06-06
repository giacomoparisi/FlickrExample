package com.giacomoparisi.domain.usecases.photo;

import com.giacomoparisi.entities.photo.Photo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface PhotoRepository {

    Single<List<Photo>> searchPhotos(String text, Integer page, Integer pageSize);

}