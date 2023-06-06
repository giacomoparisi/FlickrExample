package com.giacomoparisi.data.repositories.photo.network.response;

import com.giacomoparisi.domain.datatypes.errors.FlickerExampleException;
import com.giacomoparisi.domain.datatypes.errors.UnknownFlickerExampleException;
import com.giacomoparisi.entities.photo.Photo;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PhotosResponse {

    @SerializedName("photos")
    public PhotosListResponse photos;
    @SerializedName("stat")
    public String status;

    public List<Photo> toPhotoList() throws UnknownFlickerExampleException {

        if (Objects.equals(status, "ok")) {
            if (photos != null && photos.list != null)
                return photos
                        .list
                        .stream()
                        .map(PhotoResponse::toPhoto)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
            else return new ArrayList<>();
        } else throw FlickerExampleException.unknown();

    }

}
