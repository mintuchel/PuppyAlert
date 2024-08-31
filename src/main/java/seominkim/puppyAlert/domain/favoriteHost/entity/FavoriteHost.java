package seominkim.puppyAlert.domain.favoriteHost.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.user.entity.User;

// 외래키 2개 보유
// 2개에 대한 연관관계의 주인
@Entity
@Getter
@Setter
public class FavoriteHost {
    @Id @GeneratedValue
    private Long favoriteHostId;

    // JoinColumn 의 name 에는 조인할 칼럼명을 적는게 아니라
    // Join 할 칼럼을 이 데이터베이스에서 어떤 이름으로 들고있을지
    // 즉 현재 테이블의 FK 변수명을 적는거임
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "puppyId")
    private User puppy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hostId")
    private User host;
}