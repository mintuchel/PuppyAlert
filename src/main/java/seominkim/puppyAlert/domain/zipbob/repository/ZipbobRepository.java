package seominkim.puppyAlert.domain.zipbob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seominkim.puppyAlert.domain.zipbob.entity.Zipbob;

@Repository
public interface ZipbobRepository extends JpaRepository<Zipbob, Long> {

}
