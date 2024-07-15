package seominkim.puppyAlert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seominkim.puppyAlert.entity.Host;
import seominkim.puppyAlert.entity.Puppy;
import seominkim.puppyAlert.entity.Zipbob;

import java.util.List;

@Repository
public interface ZipbobRepository extends JpaRepository<Zipbob, Long> {

}
