package seominkim.puppyAlert.domain.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Shop {
    @Id @GeneratedValue
    private int id;

    @Column(columnDefinition = "varchar(225)", nullable = false)
    private String shopName;

    @Column(columnDefinition = "varchar(225)", nullable = false)
    private String detailAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType productType;
}
