package it.umbriajazz.navigation;

import com.giacomoparisi.entities.photo.Photo;

import it.umbriajazz.navigation.directions.DetailNavigationDestination;

public class NavigationDestination implements NavigationAction {

    public static DetailNavigationDestination detail(Photo photo) {
        return new DetailNavigationDestination(photo);
    }

}
