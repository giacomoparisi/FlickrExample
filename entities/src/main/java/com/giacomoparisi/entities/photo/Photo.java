package com.giacomoparisi.entities.photo;

public class Photo {

    private final String url;

    public Photo(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Photo) return url.equals(((Photo) obj).url);
        else return false;
    }
}
