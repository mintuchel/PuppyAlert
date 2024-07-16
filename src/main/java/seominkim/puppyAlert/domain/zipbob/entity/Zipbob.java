package seominkim.puppyAlert.domain.zipbob.entity;

import jakarta.persistence.*;
import lombok.*;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    private LocalDateTime time; // 식사시간

    @Enumerated(EnumType.STRING)
    private ZipbobStatus status; // 매칭상태
}
