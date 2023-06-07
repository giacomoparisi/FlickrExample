package it.umbriajazz.navigation.directions;

import android.os.Bundle;

import com.giacomoparisi.entities.photo.Photo;

import it.umbriajazz.navigation.NavigationDestination;

public class DetailNavigationDestination extends NavigationDestination {

    public final Photo photo;

    public DetailNavigationDestination(Photo photo) {
        this.photo = photo;
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("url", photo.getUrl());
        bundle.putString("title", photo.getTitle());
        bundle.putBoolean("is_family", photo.isFamily());
        bundle.putBoolean("is_public", photo.isPublic());
        return bundle;
    }

}
