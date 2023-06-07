package it.umbriajazz.navigation;

import com.giacomoparisi.domain.datatypes.uievent.UIEvent;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

@Singleton
public class NavigationManager {

    private final PublishSubject<UIEvent<NavigationAction>> navigationSubject =
            PublishSubject.create();

    public final Observable<UIEvent<NavigationAction>> navigation = navigationSubject;

    @Inject
    public NavigationManager() {
    }

    public void navigate(NavigationDestination destination) {
        navigationSubject.onNext(new UIEvent<>(destination));
    }

}
