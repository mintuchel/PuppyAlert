package seominkim.puppyAlert.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import seominkim.puppyAlert.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByIdAndPassword(String id, String password);
    boolean existsByNickName(String nickName);

    @Query(value = "SELECT * FROM user_table WHERE id = :hostId AND user_type = 'HOST'", nativeQuery = true)
    Optional<User> findHostById(@Param("hostId") String id);

    @Query(value = "SELECT * FROM user_table WHERE id = :puppyId AND user_type = 'PUPPY'", nativeQuery = true)
    Optional<User> findPuppyById(@Param("puppyId") String id);

    @Query(value = "SELECT * FROM user_table WHERE user_type = 'HOST'", nativeQuery = true)
    List<User> findAllHost();

    @Query(value = "SELECT * FROM user_table WHERE user_type = 'PUPPY'", nativeQuery = true)
    List<User> findAllPuppy();
}
