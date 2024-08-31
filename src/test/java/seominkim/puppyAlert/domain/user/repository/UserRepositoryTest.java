package seominkim.puppyAlert.domain.user.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired UserRepository userRepository;

    @Test
    @DisplayName("Host 저장")
    void saveHost(){

    }

    @Test
    @DisplayName("Puppy 저장")
    void savePuppy(){

    }

}