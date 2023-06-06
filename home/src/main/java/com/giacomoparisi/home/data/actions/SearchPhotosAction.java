package com.giacomoparisi.home.data.actions;

public class SearchPhotosAction extends HomeAction {

    private final String text;

    public SearchPhotosAction(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
