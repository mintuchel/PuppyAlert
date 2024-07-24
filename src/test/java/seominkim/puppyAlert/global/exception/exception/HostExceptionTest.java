package seominkim.puppyAlert.global.exception.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.host.api.HostController;
import seominkim.puppyAlert.global.dto.SignUpRequestDTO;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;

@SpringBootTest
public class HostExceptionTest {

    @Autowired HostController hostController;

    @Test
    @Rollback
    @Transactional
    public void hostSignUpExceptionTest(){
        // given
        String testHostId = "Havertz";

        SignUpRequestDTO signUpDTO1 = new SignUpRequestDTO();
        signUpDTO1.setId(testHostId);
        signUpDTO1.setPassword("29");
        signUpDTO1.setName("하베르츠");
        signUpDTO1.setNickName("나는원래첼시야");
        signUpDTO1.setBirth(LocalDate.now());
        signUpDTO1.setAddress("첼시");
        signUpDTO1.setLocation(new Location(100.135135, 135.12435));
        signUpDTO1.setPhoneNumber("010-4822-3636");

        hostController.signUp(signUpDTO1);

        // when

        SignUpRequestDTO signUpDTO2 = new SignUpRequestDTO();
        signUpDTO2.setId(testHostId);
        signUpDTO2.setPassword("29");
        signUpDTO2.setName("하베르츠");
        signUpDTO2.setNickName("나는아스날에서부활했다");
        signUpDTO2.setBirth(LocalDate.now());
        signUpDTO2.setAddress("아스날");
        signUpDTO2.setLocation(new Location(100.135135, 135.12435));
        signUpDTO2.setPhoneNumber("010-4822-3636");

        // then
        Assertions.assertThrows(HostException.class, ()->{
           hostController.signUp(signUpDTO2);
        });
    }
}