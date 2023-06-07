package com.giacomoparisi.data.repositories.photo.network;

import com.giacomoparisi.data.repositories.photo.network.response.PhotosResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhotoApi {

    @GET("rest/?method=flickr.photos.search&format=json&nojsoncallback=1")
    Single<PhotosResponse> searchPhotos(
            @Query("api_key") String apiKey,
            @Query("text") String text,
            @Query("page") Integer page,
            @Query("per_page") Integer perPage
    );

    @GET("rest/?method=flickr.photos.getRecent&format=json&nojsoncallback=1")
    Single<PhotosResponse> getRecentPhotos(
            @Query("api_key") String apiKey,
            @Query("page") Integer page,
            @Query("per_page") Integer perPage
    );

}
