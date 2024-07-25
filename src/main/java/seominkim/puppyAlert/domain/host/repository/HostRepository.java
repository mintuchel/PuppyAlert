package seominkim.puppyAlert.domain.host.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seominkim.puppyAlert.domain.host.entity.Host;

import java.util.List;
import java.util.Optional;

@Repository
public interface HostRepository extends JpaRepository<Host, String> {
    public List<Host> findByName(String name);

    boolean existsByHostIdAndPassword(String hostId, String password);

    boolean existsByNickName(String nickName);
}
