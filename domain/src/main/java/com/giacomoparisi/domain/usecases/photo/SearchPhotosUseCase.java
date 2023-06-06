package com.giacomoparisi.domain.usecases.photo;

import com.giacomoparisi.domain.datatypes.paging.PagedList;
import com.giacomoparisi.entities.photo.Photo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class SearchPhotosUseCase {

    private final PhotoRepository repository;

    @Inject
    public SearchPhotosUseCase(PhotoRepository repository) {
        this.repository = repository;
    }

    public Single<PagedList<Photo>> execute(String text, Integer page, Integer pageSize) {
        return repository.searchPhotos(text, page, pageSize);
    }

}
