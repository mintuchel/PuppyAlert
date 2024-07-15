package seominkim.puppyAlert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seominkim.puppyAlert.entity.Host;
import seominkim.puppyAlert.entity.Puppy;

import java.util.List;

@Repository
public interface PuppyRepository extends JpaRepository<Puppy, String> {
    public List<Puppy> findByName(String name);
}
