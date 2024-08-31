package seominkim.puppyAlert.domain.host.entity;

import jakarta.persistence.*;
import lombok.*;
import seominkim.puppyAlert.domain.user.entity.User;
import seominkim.puppyAlert.global.entity.Location;
import seominkim.puppyAlert.domain.food.entity.Food;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Host extends User {
    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Food> foodList = new ArrayList<>();
}