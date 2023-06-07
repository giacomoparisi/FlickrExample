package com.giacomoparisi.domain.datatypes.uievent;

public class ToHandle<T> extends Handle<T> {

    private final T value;

    public ToHandle(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

}
