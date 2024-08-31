package seominkim.puppyAlert.domain.puppy.entity;

import jakarta.persistence.*;
import lombok.*;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;
import seominkim.puppyAlert.domain.user.entity.User;
import seominkim.puppyAlert.global.entity.Location;
import seominkim.puppyAlert.domain.food.entity.Food;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Puppy extends User {
    // 연관관계 매핑
    // 집밥
    @OneToMany(mappedBy = "puppy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Food> foodList = new ArrayList<>();

    // 팔로우
    @OneToMany(mappedBy = "puppy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FavoriteHost> favoriteHostList = new ArrayList<>();
}
