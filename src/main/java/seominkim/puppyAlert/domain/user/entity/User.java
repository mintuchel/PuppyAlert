package seominkim.puppyAlert.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;

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

    private String profileImageURL;
}
