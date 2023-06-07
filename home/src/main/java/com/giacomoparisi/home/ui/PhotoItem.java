package com.giacomoparisi.home.ui;

import android.view.View;

import com.giacomoparisi.core.ui.recyclerview.item.Item;
import com.giacomoparisi.entities.photo.Photo;

public class PhotoItem extends Item {

    private Photo photo;
    private View.OnClickListener onClickListener;

    public PhotoItem(Photo photo, View.OnClickListener onClickListener) {
        this.photo = photo;
        this.onClickListener = onClickListener;
    }

    public Photo getPhoto() {
        return photo;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
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

