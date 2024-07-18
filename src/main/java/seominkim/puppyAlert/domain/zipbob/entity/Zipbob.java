package seominkim.puppyAlert.domain.zipbob.entity;

import jakarta.persistence.*;
import lombok.*;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Zipbob{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long zipbobId;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="hostId")
    private Host host;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="puppyId")
    private Puppy puppy;

    @Column(columnDefinition = "varchar(225)", nullable = false)
    private String menu; // 메뉴이름

    @Column(nullable = false)
    private LocalDateTime time; // 식사시간

    //@Column(columnDefinition = "varchar(225)", nullable = false)
    //private String imageURL;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ZipbobStatus status; // 매칭상태
}
