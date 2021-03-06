package com.zakharenkov.shop.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Set;

@Builder
@ToString(exclude = {"category", "storage", "reviews", "lineItems", "users"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
@Entity
@Table(name = "product", schema = "shop")
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Product implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ProductDetail productDetail;

    @Column(name = "price", nullable = false)
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToOne(mappedBy = "product")
    private Storage storage;

    @Version
    private Long version;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<LineItem> lineItems;

    @ManyToMany
    @JoinTable(name = "favorite", schema = "shop",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    public Product(ProductDetail productDetail, Integer price, Category category, Storage storage) {
        this.productDetail = productDetail;
        this.price = price;
        this.category = category;
        this.storage = storage;
    }
}
