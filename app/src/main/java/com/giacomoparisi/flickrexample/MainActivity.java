package com.giacomoparisi.flickrexample;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import it.umbriajazz.navigation.NavigationAction;
import it.umbriajazz.navigation.NavigationManager;
import it.umbriajazz.navigation.directions.DetailNavigationDestination;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Inject
    public NavigationManager navigationManager;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // navigation
        compositeDisposable.add(
                navigationManager.navigation
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .throttleFirst(1L, TimeUnit.SECONDS)
                        .map(uiEvent -> uiEvent.getContentByHandler("navigation").get())
                        .filter(Objects::nonNull)
                        .subscribe(this::navigate)
        );

    }

    private void navigate(NavigationAction action) {
        if (action instanceof DetailNavigationDestination)
            Navigation.findNavController(this, R.id.nav_host_fragment)
                    .navigate(
                            R.id.action_home_to_detail,
                            ((DetailNavigationDestination) action).getBundle()
                    );
    }

}
