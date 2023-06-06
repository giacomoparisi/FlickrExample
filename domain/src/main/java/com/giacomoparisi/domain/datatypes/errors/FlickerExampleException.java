package com.giacomoparisi.domain.datatypes.errors;

public abstract class FlickerExampleException extends Throwable {

    public static UnknownFlickerExampleException unknown() {
        return new UnknownFlickerExampleException();
    }

}
