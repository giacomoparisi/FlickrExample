package com.giacomoparisi.home.data;

import com.giacomoparisi.domain.datatypes.lazydata.LazyData;
import com.giacomoparisi.domain.datatypes.lazydata.LazyDataEmpty;
import com.giacomoparisi.entities.photo.Photo;
import com.giacomoparisi.home.ui.PhotoItem;

import java.util.List;

public class HomeState {

    private final LazyData<List<PhotoItem>> photos;

    public HomeState() {
        photos = new LazyDataEmpty<>();
    }

    public HomeState(LazyData<List<PhotoItem>> photos) {
        this.photos = photos;
    }

    public LazyData<List<PhotoItem>> getPhotos() {
        return photos;
    }

}
