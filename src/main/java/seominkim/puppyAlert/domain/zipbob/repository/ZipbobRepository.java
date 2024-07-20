package seominkim.puppyAlert.domain.zipbob.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import seominkim.puppyAlert.domain.zipbob.entity.Zipbob;

import java.util.List;

@Repository
public interface ZipbobRepository extends JpaRepository<Zipbob, Long> {
    List<Zipbob> findByHost_HostId(String hostId);
    List<Zipbob> findByPuppy_PuppyId(String puppyId);
    List<Zipbob> findByPuppy_PuppyIdAndHost_HostId(String puppyId, String hostId);

    // JPQL 에는 LIMIT 절이 없으므로 Pageable 로 해결해야함
    @Query("SELECT z FROM Zipbob z WHERE z.puppy.puppyId = :puppyId AND z.host.hostId = :hostId ORDER BY z.time DESC")
    List<Zipbob> findMostRecentByPuppyIdAndHostId(@Param("puppyId") String puppyId, @Param("hostId") String hostId, Pageable pageable);
}
