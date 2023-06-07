package com.giacomoparisi.domain.datatypes.lazydata;

import com.giacomoparisi.domain.datatypes.errors.FlickerExampleException;

public class LazyDataSuccess<T> implements LazyData<T> {

    private final T value;

    public LazyDataSuccess(T value) {
        this.value = value;
    }

    @Override
    public T successValue() {
        return value;
    }

    @Override
    public T value() {
        return value;
    }

    @Override
    public FlickerExampleException error() {
        return null;
    }

    @Override
    public boolean isLoading() {
        return false;
    }
}
