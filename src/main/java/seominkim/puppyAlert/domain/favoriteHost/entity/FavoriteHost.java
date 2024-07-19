package seominkim.puppyAlert.domain.favoriteHost.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;

@Entity
@Getter
@Setter
public class FavoriteHost {
    @Id @GeneratedValue
    private Long favoriteHostId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "puppyId")
    private Puppy puppy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hostId")
    private Host host;
}