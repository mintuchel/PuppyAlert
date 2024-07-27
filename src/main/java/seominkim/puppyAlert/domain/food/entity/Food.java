package seominkim.puppyAlert.domain.food.entity;

import jakarta.persistence.*;
import lombok.*;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="hostId")
    private Host host;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="puppyId")
    private Puppy puppy;

    @Column(columnDefinition = "varchar(225)", nullable = false)
    private String menu;

    @Column(nullable = false)
    private LocalDateTime time; // 식사시간

    @Column(columnDefinition = "varchar(225)", nullable = false)
    private String imageURL;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodStatus status; // 매칭상태
}
