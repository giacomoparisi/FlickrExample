package com.giacomoparisi.domain.usecases.photo;

import com.giacomoparisi.domain.datatypes.paging.PagedList;
import com.giacomoparisi.entities.photo.Photo;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetRecentPhotosUseCase {

    private final PhotoRepository repository;

    @Inject
    public GetRecentPhotosUseCase(PhotoRepository repository) {
        this.repository = repository;
    }

    public Single<PagedList<Photo>> execute(Integer page, Integer pageSize) {
        return repository.getRecentPhotos(page, pageSize);
    }

}
