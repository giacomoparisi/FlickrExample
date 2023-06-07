package com.giacomoparisi.data.repositories.photo.network.response;

import com.giacomoparisi.entities.photo.Photo;
import com.google.gson.annotations.SerializedName;

public class PhotoResponse {

    @SerializedName("id")
    public String id;
    @SerializedName("secret")
    public String secret;
    @SerializedName("server")
    public String server;

    @SerializedName("title")
    public String title;

    @SerializedName("ispublic")
    public Integer isPublic;

    @SerializedName("isfamily")
    public Integer isFamily;

    public Photo toPhoto() {

        if (id != null && secret != null && server != null && title != null &&
                isFamily != null && isPublic != null
        )
            return new Photo(
                    "https://live.staticflickr.com" +
                            "/" +
                            server +
                            "/" +
                            id +
                            "_" +
                            secret +
                            "_w.jpg",
                    title,
                    isPublic == 1,
                    isFamily == 1
            );
        else return null;

    }

}
