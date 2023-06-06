package com.giacomoparisi.data.error;

import com.giacomoparisi.domain.datatypes.errors.FlickerExampleException;
import com.giacomoparisi.domain.datatypes.errors.NetworkFlickerExampleException;
import com.giacomoparisi.domain.datatypes.errors.UnknownFlickerExampleException;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import retrofit2.HttpException;

public class ErrorMapper {

    @Inject
    public ErrorMapper() {
    }

    public FlickerExampleException map(Throwable throwable) {
        if (throwable instanceof FlickerExampleException)
            return (FlickerExampleException) throwable;
        else if (
                throwable instanceof HttpException ||
                        throwable instanceof TimeoutException ||
                        throwable instanceof SocketTimeoutException
        ) return new NetworkFlickerExampleException();
        else return new UnknownFlickerExampleException();
    }

}
