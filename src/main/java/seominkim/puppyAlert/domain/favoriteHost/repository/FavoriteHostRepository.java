package seominkim.puppyAlert.domain.favoriteHost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;

@Repository
public interface FavoriteHostRepository extends JpaRepository<FavoriteHost, Long> {

    boolean existsByPuppy_PuppyIdAndHost_HostId(String puppyId, String hostId);

    FavoriteHost findByPuppy_PuppyIdAndHost_HostId(String puppyId, String hostId);
}
