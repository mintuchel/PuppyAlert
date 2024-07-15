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
public class Host {
    @Id
    private String host_id;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String password;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String name;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private Date birth;

    @Column(columnDefinition = "varchar(11)", nullable = false)
    private String phone_number;

    @Embedded
    private Location location;

    @OneToMany(mappedBy="host")
    private List<Zipbob> zipbobList = new ArrayList<Zipbob>();
}