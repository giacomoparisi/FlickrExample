package com.giacomoparisi.home.data;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    @Inject
    public HomeViewModel() {
    }

}
