package seominkim.puppyAlert.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private List<Zipbob> zipbobList = new ArrayList<Zipbob>();

    @OneToMany(mappedBy = "puppy", cascade = CascadeType.ALL)
    private List<FavoriteHost> favoriteHostList = new ArrayList<>();
}
