package seominkim.puppyAlert.domain.common.api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.common.dto.request.LoginRequestDTO;
import seominkim.puppyAlert.domain.common.dto.response.LoginResponse;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.global.entity.Location;
import seominkim.puppyAlert.global.entity.UserType;

import java.time.LocalDate;

@SpringBootTest
public class CommonControllerTest {

    @Autowired CommonController commonController;

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    @Rollback
    public void hostLoginTest(){
        // given
        Host host = new Host();
        host.setHostId("mbappe");
        host.setPassword("7");
        host.setNickName("나는야음바페");
        host.setName("음바페");
        host.setBirth(LocalDate.now());
        host.setAddress("파리 생제르망");
        host.setLocation(new Location(133.4135,137.58357));
        host.setPhoneNumber("010-4822-3636");

        em.persist(host);

        // when
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setId(host.getHostId());
        loginRequestDTO.setPassword(host.getPassword());

        // then
        LoginResponse responseDTO = commonController.login(loginRequestDTO);

        Assertions.assertThat(responseDTO.getId()).isEqualTo(host.getHostId());
        Assertions.assertThat(responseDTO.getUserType()).isEqualTo(UserType.HOST);
    }

    @Test
    @Transactional
    @Rollback
    public void puppyLoginTest(){
        // when
        Puppy puppy = new Puppy();
        puppy.setPuppyId("Messi");
        puppy.setName("메시");
        puppy.setNickName("응이번발롱도르도나야");
        puppy.setPassword("10");
        puppy.setBirth(LocalDate.now());
        puppy.setAddress("바르셀로나");
        puppy.setLocation(new Location(200.3562254, 200.1241));
        puppy.setPhoneNumber("010-1111-2222");

        em.persist(puppy);

        // then
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setId(puppy.getPuppyId());
        loginRequestDTO.setPassword(puppy.getPassword());

        // then
        LoginResponse responseDTO = commonController.login(loginRequestDTO);

        Assertions.assertThat(responseDTO.getId()).isEqualTo(puppy.getPuppyId());
        Assertions.assertThat(responseDTO.getUserType()).isEqualTo(UserType.PUPPY);
    }
}