package com.zakharenkov.shop.model;

import java.io.Serializable;

public interface BaseEntity<P extends Serializable> {

    P getId();
}
