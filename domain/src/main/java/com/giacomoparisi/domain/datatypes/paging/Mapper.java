package com.giacomoparisi.domain.datatypes.paging;

public interface Mapper<T, P> {

    P map(T source);

}
