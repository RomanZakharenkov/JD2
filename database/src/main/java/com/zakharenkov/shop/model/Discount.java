package com.zakharenkov.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Entity
@Table(name = "discount", schema = "shop")
@PrimaryKeyJoinColumn(name = "sale_id")
public class Discount extends Sale {

    @Column(name = "precent", nullable = false)
    private Integer precent;

    public Discount(User user, LocalDate startDate, LocalDate endDate, TypeSale type, SaleStatus status, String description, Integer precent) {
        super(user, startDate, endDate, type, status, description);
        this.precent = precent;
    }
}
