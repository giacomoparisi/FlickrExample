package it.umbriajazz.navigation.directions;

import android.os.Bundle;

import it.umbriajazz.navigation.NavigationDestination;

public class DetailNavigationDestination extends NavigationDestination {

    public final String url;

    public DetailNavigationDestination(String url) {
        this.url = url;
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        return bundle;
    }

}
