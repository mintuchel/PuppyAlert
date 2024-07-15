package seominkim.puppyAlert.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Zipbob {
    @Id @GeneratedValue
    private Long zipbobId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="hostId")
    private Host host;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="puppyId")
    private Puppy puppy;

    private String menu; // 메뉴이름

    private String imageURL;

    private LocalDateTime time; // 식사시간

    @Enumerated(EnumType.STRING)
    private ZipbobStatus status; // 매칭상태
}
