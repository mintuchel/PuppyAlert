package seominkim.puppyAlert.domain.food.entity;

import jakarta.persistence.*;
import lombok.*;
import seominkim.puppyAlert.domain.menu.entity.Menu;
import seominkim.puppyAlert.domain.user.entity.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private User host;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "puppy_id")
    private User puppy;

    // cascade 안쓰면 에러 터짐
    // Food 가 Menu 참조하는 단방향이라
    // food 저장하면 cascade로 menu도 저장되게 하는 구조라서
    // @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuName")
    private Menu menu;

    @Column(nullable = false)
    private LocalDateTime time; // 식사시간

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MatchStatus matchStatus; // 매칭상태
}
