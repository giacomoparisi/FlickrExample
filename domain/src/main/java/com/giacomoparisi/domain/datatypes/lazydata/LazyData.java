package com.giacomoparisi.domain.datatypes.lazydata;

import com.giacomoparisi.domain.datatypes.errors.FlickerExampleException;

public interface LazyData<T> {

   T successValue();

   T value();

   FlickerExampleException error();

}

