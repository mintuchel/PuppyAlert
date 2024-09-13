package seominkim.puppyAlert.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.global.entity.Location;
import seominkim.puppyAlert.global.entity.UserType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_table")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    @Id
    private String id;

    @Column(columnDefinition = "varchar(225)", nullable = false)
    private String password;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String nickName;

    @Column(columnDefinition = "varchar(225)", nullable = false)
    private String name;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate birth;

    @Column(columnDefinition = "varchar(225)", nullable = false)
    private String phoneNumber;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String address;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String detailAddress;

    @Embedded
    private Location location;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    private String profileImageURL;

    @Builder.Default
    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Food> hostFoods = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "puppy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Food> puppyFoods = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "puppy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FavoriteHost> favoriteHostList = new ArrayList<>();
}
