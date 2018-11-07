package model;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum OrderStatus {

    RESERVED ("Зарезервирован"), PROCESSED ("Обрабатывается"), COMPLITED ("Завершён");

    private String name;

    OrderStatus(String name) {
        this.name = name;
    }
}
