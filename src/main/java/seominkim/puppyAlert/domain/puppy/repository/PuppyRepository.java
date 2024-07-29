package seominkim.puppyAlert.domain.puppy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;

@Repository
public interface PuppyRepository extends JpaRepository<Puppy, String> {

    boolean existsByPuppyIdAndPassword(String puppyId, String password);

    boolean existsByNickName(String nickName);
}
