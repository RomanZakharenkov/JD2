package model;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@ToString
@Getter
public enum Role {

    ADMIN ("ADMIN"), USER ("USER");

    private String name;

    Role(String name) {
        this.name = name;
    }

}
