package seominkim.puppyAlert.domain.favoriteHost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;

@Repository
public interface FavoriteHostRepository extends JpaRepository<FavoriteHost, Long> {

    boolean existsByPuppyIdAndHostId(String puppyId, String hostId);

    FavoriteHost findByPuppyIdAndHostId(String puppyId, String hostId);
}