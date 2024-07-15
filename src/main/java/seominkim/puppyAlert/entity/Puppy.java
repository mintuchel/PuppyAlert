package seominkim.puppyAlert.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Puppy {
    @Id
    private String puppy_id;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String password;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String name;

    private Date birth;

    @Column(columnDefinition = "varchar(11)", nullable = false)
    private String phone_number;

    @Embedded
    private Location location;

    @OneToMany(mappedBy = "puppy")
    private List<Zipbob> zipbobList = new ArrayList<Zipbob>();

    @OneToMany(mappedBy="puppy")
    private List<Long> favoriteHostList = new ArrayList<>();
}
