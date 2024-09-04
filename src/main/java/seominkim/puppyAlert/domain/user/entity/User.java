package seominkim.puppyAlert.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.global.entity.Location;
import seominkim.puppyAlert.global.entity.UserType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user_table")
@Getter
@Setter
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    private String profileImageURL;

    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Food> hostFoods = new ArrayList<>();

    @OneToMany(mappedBy = "puppy", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Food> puppyFoods = new ArrayList<>();

    @OneToMany(mappedBy = "puppy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FavoriteHost> favoriteHostList = new ArrayList<>();
}
