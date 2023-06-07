package com.giacomoparisi.domain.datatypes.uievent;

public abstract class Handle<T> {

    public T get() {
        if (this instanceof ToHandle) return ((ToHandle<T>) this).getValue();
        else return null;
    }

}
