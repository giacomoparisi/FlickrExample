package com.giacomoparisi.home.data;

import androidx.lifecycle.ViewModel;

import com.giacomoparisi.data.error.ErrorMapper;
import com.giacomoparisi.domain.datatypes.lazydata.LazyDataError;
import com.giacomoparisi.domain.datatypes.lazydata.LazyDataSuccess;
import com.giacomoparisi.domain.datatypes.paging.PagedList;
import com.giacomoparisi.domain.usecases.photo.SearchPhotosUseCase;
import com.giacomoparisi.home.data.actions.HomeAction;
import com.giacomoparisi.home.data.actions.NextPhotosPageAction;
import com.giacomoparisi.home.data.actions.SearchPhotosAction;
import com.giacomoparisi.home.ui.PhotoItem;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import it.umbriajazz.navigation.NavigationDestination;
import it.umbriajazz.navigation.NavigationManager;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    /* --- state --- */
    private final BehaviorSubject<HomeState> stateBehaviorSubject =
            BehaviorSubject.createDefault(new HomeState());

    public Observable<HomeState> state = stateBehaviorSubject;

    private HomeState state() {
        return stateBehaviorSubject.getValue();
    }

    /* --- pagination --- */
    private final int pageSize = 20;
    private Disposable firstPageDisposable;
    private Disposable nextPageDisposable;

    /* --- use cases --- */
    private final SearchPhotosUseCase searchPhotosUseCase;

    /* --- error --- */
    private final ErrorMapper errorMapper;

    /* --- navigation --- */
    private final NavigationManager navigationManager;

    @Inject
    public HomeViewModel(
            SearchPhotosUseCase searchPhotosUseCase,
            ErrorMapper errorMapper,
            NavigationManager navigationManager
    ) {
        this.searchPhotosUseCase = searchPhotosUseCase;
        this.errorMapper = errorMapper;
        this.navigationManager = navigationManager;
    }

    /* --- search --- */

    private void searchFirstPage(String text) {
        if (firstPageDisposable != null) firstPageDisposable.dispose();
        if (nextPageDisposable != null) nextPageDisposable.dispose();
        firstPageDisposable =
                searchPhotos(text, 1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnTerminate(() -> {
                            firstPageDisposable = null;
                        })
                        .subscribe(
                                photos -> {
                                    HomeState newState =
                                            new HomeState(new LazyDataSuccess<>(photos), text);
                                    stateBehaviorSubject.onNext(newState);
                                },
                                throwable -> {
                                    HomeState newState =
                                            new HomeState(
                                                    new LazyDataError<>(
                                                            errorMapper.map(throwable),
                                                            stateBehaviorSubject.getValue().getPhotos().value()
                                                    ),
                                                    text
                                            );
                                    stateBehaviorSubject.onNext(newState);
                                }
                        );
    }

    private boolean needNextPage() {
        // Avoid to search a new page if a request is already running
        PagedList<PhotoItem> currentList = stateBehaviorSubject.getValue().getPhotos().value();
        boolean isNextPageIdle = nextPageDisposable == null && firstPageDisposable == null;
        return isNextPageIdle && currentList != null && !currentList.getIsCompleted();
    }

    private void searchNextPage() {

        PagedList<PhotoItem> currentList = stateBehaviorSubject.getValue().getPhotos().value();

        if (needNextPage()) {
            nextPageDisposable =
                    searchPhotos(state().getText(), (currentList.getPage() + 1))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnTerminate(() -> {
                                nextPageDisposable = null;
                            })
                            .subscribe(
                                    photos -> {
                                        HomeState newState =
                                                new HomeState(
                                                        new LazyDataSuccess<>(currentList.addPage(photos)),
                                                        state().getText()
                                                );
                                        stateBehaviorSubject.onNext(newState);
                                    },
                                    throwable -> {
                                        HomeState newState =
                                                new HomeState(
                                                        new LazyDataError<>(
                                                                errorMapper.map(throwable),
                                                                stateBehaviorSubject.getValue().getPhotos().value()
                                                        ),
                                                        state().getText()
                                                );
                                        stateBehaviorSubject.onNext(newState);
                                    }
                            );
        }
    }

    private Single<PagedList<PhotoItem>> searchPhotos(String text, int page) {
        return searchPhotosUseCase
                .execute(text, page, pageSize)
                .map(photos ->
                        photos.map(photo ->
                                new PhotoItem(
                                        photo,
                                        v -> {
                                            String url = photo.getUrl();
                                            navigationManager.navigate(
                                                    NavigationDestination.detail(photo)
                                            );
                                        }
                                ))
                );
    }

    /* --- actions --- */

    public void dispatch(HomeAction action) {
        if (action instanceof SearchPhotosAction)
            searchFirstPage(((SearchPhotosAction) action).getText());
        else if (action instanceof NextPhotosPageAction)
            searchNextPage();
    }

    @Override
    protected void onCleared() {
        if (firstPageDisposable != null) firstPageDisposable.dispose();
        if(nextPageDisposable != null) nextPageDisposable.dispose();
        firstPageDisposable = null;
        nextPageDisposable = null;
        super.onCleared();
    }
}
