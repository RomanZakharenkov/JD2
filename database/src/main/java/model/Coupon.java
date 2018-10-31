package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@Entity
@Table(name = "Coupon", schema = "shop")
@PrimaryKeyJoinColumn(name = "sale_id")
public class Coupon extends Sale {

    @Column(name = "count", nullable = false)
    private Integer count;

    public Coupon(User user, LocalDate start, LocalDate end, TypeSale type, SaleStatus saleStatus, String description, Integer count) {
        super(user, start, end, type, saleStatus, description);
        this.count = count;
    }
}
