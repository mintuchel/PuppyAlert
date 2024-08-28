package seominkim.puppyAlert.domain.favoriteHost.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;

// 외래키 2개 보유
// 2개에 대한 연관관계의 주인
@Entity
@Getter
@Setter
public class FavoriteHost {
    @Id @GeneratedValue
    private Long favoriteHostId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "puppyId")
    private Puppy puppy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hostId")
    private Host host;
}