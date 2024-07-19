package seominkim.puppyAlert.domain.zipbob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seominkim.puppyAlert.domain.zipbob.entity.Zipbob;

import java.util.List;

@Repository
public interface ZipbobRepository extends JpaRepository<Zipbob, Long> {
    List<Zipbob> findByHost_HostId(String hostId);
    List<Zipbob> findByPuppy_PuppyId(String puppyId);
    List<Zipbob> findByPuppy_PuppyIdAndHost_HostId(String puppyId, String hostId);
}
