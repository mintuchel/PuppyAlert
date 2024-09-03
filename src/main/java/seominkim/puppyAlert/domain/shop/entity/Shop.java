package seominkim.puppyAlert.domain.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seominkim.puppyAlert.domain.market.entity.Market;

@Entity
@Getter
@Setter
public class Shop {
    @Id @GeneratedValue
    private int shopId;

    // 보편적인 다대일에서 "다" 쪽을 주인으로 설정
    // JoinColumn에 의해 Market의 PK가 Shop 테이블에 market_id 라는 FK로 저장됨
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="marketId")
    private Market market;

    @Column(columnDefinition = "varchar(225)", nullable = false)
    private String shopName;

    @Column(columnDefinition = "varchar(225)", nullable = false)
    private String detailAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType productType;
}
