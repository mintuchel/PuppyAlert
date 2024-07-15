package seominkim.puppyAlert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seominkim.puppyAlert.entity.Host;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host, String> {
    public List<Host> findByName(String name);
}
