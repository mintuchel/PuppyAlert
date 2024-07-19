package seominkim.puppyAlert.domain.favoriteHost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteHostRepository extends JpaRepository<FavoriteHost, Long> {
    // 1개만 반환
    Optional<FavoriteHost> findByPuppy_PuppyIdAndHost_HostId(String puppyId, String hostId);
    // 여러개 반환
    List<FavoriteHost> findByPuppy_PuppyId(String puppyId);
}
