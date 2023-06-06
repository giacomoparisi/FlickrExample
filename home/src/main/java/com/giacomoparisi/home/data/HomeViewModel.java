package com.giacomoparisi.home.data;

import androidx.lifecycle.ViewModel;

import com.giacomoparisi.data.error.ErrorMapper;
import com.giacomoparisi.domain.datatypes.lazydata.LazyDataError;
import com.giacomoparisi.domain.datatypes.lazydata.LazyDataSuccess;
import com.giacomoparisi.domain.usecases.photo.SearchPhotosUseCase;
import com.giacomoparisi.home.data.actions.HomeAction;
import com.giacomoparisi.home.data.actions.SearchPhotosAction;
import com.giacomoparisi.home.ui.PhotoItem;

import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    /* --- state --- */
    private final BehaviorSubject<HomeState> stateBehaviorSubject =
            BehaviorSubject.createDefault(new HomeState());

    public Observable<HomeState> state = stateBehaviorSubject;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();


    /* --- use cases --- */
    private final SearchPhotosUseCase searchPhotosUseCase;

    /* --- error --- */
    private final ErrorMapper errorMapper;

    @Inject
    public HomeViewModel(SearchPhotosUseCase searchPhotosUseCase, ErrorMapper errorMapper) {
        this.searchPhotosUseCase = searchPhotosUseCase;
        this.errorMapper = errorMapper;
    }

    private void searchPhotos(String text) {
        Disposable disposable =
                searchPhotosUseCase.execute(text, 0, 10)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(
                                photos ->
                                        photos.getData()
                                                .stream()
                                                .map(PhotoItem::new)
                                                .collect(Collectors.toList())
                        )
                        .subscribe(
                                photos -> {
                                    HomeState newState =
                                            new HomeState(new LazyDataSuccess<>(photos));
                                    stateBehaviorSubject.onNext(newState);
                                },
                                throwable -> {
                                    HomeState newState =
                                            new HomeState(
                                                    new LazyDataError<>(
                                                            errorMapper.map(throwable),
                                                            stateBehaviorSubject.getValue().getPhotos().value()
                                                    )
                                            );
                                    stateBehaviorSubject.onNext(newState);
                                }
                        );

        compositeDisposable.add(disposable);

    }

    /* --- actions --- */

    public void dispatch(HomeAction action) {
        if (action instanceof SearchPhotosAction)
            searchPhotos(((SearchPhotosAction) action).getText());
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
