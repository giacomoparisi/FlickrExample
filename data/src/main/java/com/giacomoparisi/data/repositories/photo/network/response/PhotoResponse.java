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

    Photo toPhoto() {

        if (id != null && secret != null && server != null)
            return new Photo(
                    "https://live.staticflickr.com" +
                            "/" +
                            server +
                            "/" +
                            id +
                            "_" +
                            secret +
                            "w.jpg"
            );
        else return null;

    }

}
