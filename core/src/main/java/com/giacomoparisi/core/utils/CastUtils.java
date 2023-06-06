package com.giacomoparisi.core.utils;

public class CastUtils {

    public static <T> T safeCast(Object castObject) {
        try {
            return (T) castObject;
        } catch (Throwable throwable) {
            return null;
        }
    }

}
