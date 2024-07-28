package seominkim.puppyAlert.domain.menu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Menu {
    @Id
    private String menuName;

    @Column(columnDefinition = "varchar(225)", nullable = false)
    private String imageURL;
}
