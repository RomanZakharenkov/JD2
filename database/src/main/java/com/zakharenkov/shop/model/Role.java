package com.zakharenkov.shop.model;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum Role {

    ADMIN ("ADMIN"),
    USER ("USER");

    private String name;

    Role(String name) {
        this.name = name;
    }
}
