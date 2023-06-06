package com.giacomoparisi.data.repositories.photo.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotosListResponse {

    @SerializedName("photo")
    public List<PhotoResponse> list;

}
