package seominkim.puppyAlert.domain.market.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seominkim.puppyAlert.domain.shop.entity.Shop;
import seominkim.puppyAlert.global.entity.Location;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Market {
    @Id @GeneratedValue
    private int marketId;

    @Column(columnDefinition = "varchar(225)", nullable=false)
    private String name;

    @Column(columnDefinition = "varchar(225)", nullable = false)
    private String address;

    @Column(columnDefinition = "varchar(225)", nullable = false)
    private String detailAddress;

    @Embedded
    private Location location;

    @Column(columnDefinition = "varchar(225)", nullable = false)
    private String imageURL;

    @OneToMany(mappedBy="market", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Shop> shopList = new ArrayList<>();
}
