package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@ToString
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Embeddable
public class ProductDetail {

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;
}
