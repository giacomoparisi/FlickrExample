package com.giacomoparisi.domain.datatypes.lazydata;

import com.giacomoparisi.domain.datatypes.errors.FlickerExampleException;

public class LazyDataError<T> implements LazyData<T> {

    private final FlickerExampleException error;
    private final T value;

    public LazyDataError(FlickerExampleException error, T value) {
        this.error = error;
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
        return error;
    }
}
