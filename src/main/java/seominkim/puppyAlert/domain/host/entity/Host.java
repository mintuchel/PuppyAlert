package seominkim.puppyAlert.domain.host.entity;

import jakarta.persistence.*;
import lombok.*;
import seominkim.puppyAlert.global.entity.Location;
import seominkim.puppyAlert.domain.food.entity.Food;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Host {
    @Id
    private String hostId;

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

    @Embedded
    private Location location;

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
    private List<Food> foodList = new ArrayList<>();
}