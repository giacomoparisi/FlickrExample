package com.giacomoparisi.entities.photo;

public class Photo {

    private final String url;

    private final String title;

    private final boolean isPublic;

    private final boolean isFamily;


    public Photo(
            String url,
            String title,
            boolean isPublic,
            boolean isFamily
    ) {
        this.url = url;
        this.title = title;
        this.isPublic = isPublic;
        this.isFamily = isFamily;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public boolean isFamily() {
        return isFamily;
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
