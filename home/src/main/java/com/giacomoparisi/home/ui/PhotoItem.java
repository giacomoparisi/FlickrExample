package com.giacomoparisi.home.ui;

import com.giacomoparisi.core.ui.recyclerview.item.Item;
import com.giacomoparisi.entities.photo.Photo;

public class PhotoItem extends Item {

    private Photo photo;

    public PhotoItem(Photo photo) {
        this.photo = photo;
    }

    public Photo getPhoto() {
        return photo;
    }

    @Override
    public boolean areTheSame(Item other) {
        return compare(Object::equals, other);
    }

    @Override
    public boolean haveTheSameContent(Item other) {
        return compare(Object::equals, other);
    }

}

