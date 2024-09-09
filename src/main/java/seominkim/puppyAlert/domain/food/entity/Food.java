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

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "menuName")
    private Menu menu;

    @Column(nullable = false)
    private LocalDateTime time; // 식사시간

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodStatus status; // 매칭상태
}
