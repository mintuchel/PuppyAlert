package seominkim.puppyAlert.global.exception.exception;


import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.puppy.api.PuppyController;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.global.dto.LoginRequestDTO;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;

@SpringBootTest
public class PuppyExceptionTest {

    @Autowired private PuppyController puppyController;
    @Autowired private EntityManager em;

    @Test
    @Rollback
    @Transactional
    public void puppyLoginExceptionTest(){
        // given
        Puppy puppy = new Puppy();
        puppy.setPuppyId("Mbappe");
        puppy.setName("음바페");
        puppy.setNickName("아이엠넥스트호날두");
        puppy.setPassword("7");
        puppy.setBirth(LocalDate.now());
        puppy.setAddress("레알 마드리드");
        puppy.setLocation(new Location(200.3562254, 200.1241));
        puppy.setPhoneNumber("010-7777-7777");
        em.persist(puppy);

        // when
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setId(puppy.getPuppyId());
        loginRequestDTO.setPassword("1010101010");

        // then
        Assertions.assertThrows(PuppyException.class, ()->{
           puppyController.login(loginRequestDTO);
        });
    }
}