package com.giacomoparisi.home.data;

import com.giacomoparisi.domain.datatypes.lazydata.LazyData;
import com.giacomoparisi.domain.datatypes.lazydata.LazyDataEmpty;
import com.giacomoparisi.domain.datatypes.paging.PagedList;
import com.giacomoparisi.entities.photo.Photo;
import com.giacomoparisi.home.ui.PhotoItem;

import java.util.List;

public class HomeState {

    private final LazyData<PagedList<PhotoItem>> photos;
    private final String text;

    public HomeState() {
        photos = new LazyDataEmpty<>();
        text = "";
    }

    public HomeState(LazyData<PagedList<PhotoItem>> photos, String text) {
        this.photos = photos;
        this.text = text;
    }

    public LazyData<PagedList<PhotoItem>> getPhotos() {
        return photos;
    }

    public String getText() {
        return text;
    }

}
