package com.giacomoparisi.home.data.actions;

public abstract class HomeAction {

    public static SearchPhotosAction search(String text) {
        return new SearchPhotosAction(text);
    }

    public static NextPhotosPageAction nextPhotosPage() {
        return new NextPhotosPageAction();
    }

}
