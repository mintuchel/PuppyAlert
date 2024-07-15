package seominkim.puppyAlert.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FavoriteHost {
    @Id @GeneratedValue
    private Long favorite_host_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "puppyId")
    private Puppy puppy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hostId")
    private Host host;
}