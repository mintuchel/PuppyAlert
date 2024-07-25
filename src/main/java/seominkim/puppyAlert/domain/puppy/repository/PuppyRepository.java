package seominkim.puppyAlert.domain.puppy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;

import java.util.List;
import java.util.Optional;

@Repository
public interface PuppyRepository extends JpaRepository<Puppy, String> {
    public List<Puppy> findByName(String name);

    boolean existsByPuppyIdAndPassword(String puppyId, String password);

    boolean existsByNickName(String nickName);
}
