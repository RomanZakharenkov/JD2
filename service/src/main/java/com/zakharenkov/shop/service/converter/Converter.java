package com.zakharenkov.shop.service.converter;

@FunctionalInterface
public interface Converter<T, R> {

    R convert(T object);
}
