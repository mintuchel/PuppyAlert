package seominkim.puppyAlert.domain.puppy.entity;

import jakarta.persistence.*;
import lombok.*;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;
import seominkim.puppyAlert.global.entity.Location;
import seominkim.puppyAlert.domain.zipbob.entity.Zipbob;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Puppy {
    @Id
    private String puppyId;

    @Column(columnDefinition = "varchar(225)", nullable = false)
    private String password;

    @Column(columnDefinition = "varchar(225)", nullable = false)
    private String name;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate birth;

    @Column(columnDefinition = "varchar(225)", nullable = false)
    private String phoneNumber;

    @Embedded
    private Location location;

    // 연관관계 매핑
    @OneToMany(mappedBy = "puppy", cascade = CascadeType.ALL)
    private List<Zipbob> zipbobList = new ArrayList<>();

    @OneToMany(mappedBy = "puppy", cascade = CascadeType.ALL)
    private List<FavoriteHost> favoriteHostList = new ArrayList<>();
}
