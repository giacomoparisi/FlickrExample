package it.umbriajazz.navigation;

import it.umbriajazz.navigation.directions.DetailNavigationDestination;

public class NavigationDestination implements NavigationAction {

    public static DetailNavigationDestination detail(String url) {
        return new DetailNavigationDestination(url);
    }

}
