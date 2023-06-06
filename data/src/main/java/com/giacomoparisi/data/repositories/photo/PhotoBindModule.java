package com.giacomoparisi.data.repositories.photo;

import com.giacomoparisi.domain.usecases.photo.PhotoRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class PhotoBindModule {

    @Binds
    public abstract PhotoRepository repository(PhotoRepositoryImpl repository);

}
