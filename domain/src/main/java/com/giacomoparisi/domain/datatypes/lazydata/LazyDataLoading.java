package com.giacomoparisi.domain.datatypes.lazydata;

import com.giacomoparisi.domain.datatypes.errors.FlickerExampleException;

public class LazyDataLoading<T> implements LazyData<T> {

    private final T value;

    public LazyDataLoading(T value) {
        this.value = value;
    }

    @Override
    public T successValue() {
        return null;
    }

    @Override
    public T value() {
        return value;
    }

    @Override
    public FlickerExampleException error() {
        return null;
    }
}
