package com.giacomoparisi.domain.datatypes.lazydata;

import com.giacomoparisi.domain.datatypes.errors.FlickerExampleException;

public class LazyDataEmpty<T> implements LazyData<T> {

    @Override
    public T successValue() {
        return null;
    }

    @Override
    public T value() {
        return null;
    }

    @Override
    public FlickerExampleException error() {
        return null;
    }

}
