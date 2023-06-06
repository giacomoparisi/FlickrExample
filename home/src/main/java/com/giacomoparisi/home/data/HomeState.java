package com.giacomoparisi.home.data;

import com.giacomoparisi.domain.datatypes.lazydata.LazyData;
import com.giacomoparisi.domain.datatypes.lazydata.LazyDataEmpty;
import com.giacomoparisi.entities.photo.Photo;

import java.util.List;

public class HomeState {

    private final LazyData<List<Photo>> photos;

    public HomeState() {
        photos = new LazyDataEmpty<>();
    }

    public HomeState(LazyData<List<Photo>> photos) {
        this.photos = photos;
    }

    public LazyData<List<Photo>> getPhotos() {
        return photos;
    }

}
