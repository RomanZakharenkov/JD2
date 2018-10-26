package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.security.PrivateKey;

@Builder
@Data
@ToString(exclude = "product")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@Entity
@Table(name = "storage", schema = "shop")
public class Storage implements BaseEntity<Long> {

    @Id
    @Column(name = "product_id")
    private Long id;

    @Column(name = "count")
    private Integer count;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
