package seominkim.puppyAlert.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.service.HostService;
import seominkim.puppyAlert.global.dto.SignUpDTO;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;

@SpringBootTest
public class HostServiceTest {

    // 일반적으로, 테스트 코드에서 서비스 클래스를 테스트할 때 해당 서비스 클래스에서 사용하는
    // 리포지토리나 다른 의존성을 명시적으로 주입하지 않아도 됩니다.
    // 서비스 클래스는 스프링의 의존성 주입(DI)에 의해 자동으로 리포지토리 빈을 주입받기 때문입니다.
    @Autowired HostService hostService;

    @Test
    @Transactional
    @Rollback
    public void signUpTest(){
        // given
        String testHostId = "Havertz";

        SignUpDTO signUpDTO = new SignUpDTO();
        signUpDTO.setId(testHostId);
        signUpDTO.setPassword("29");
        signUpDTO.setName("하베르츠");
        signUpDTO.setBirth(LocalDate.now());
        signUpDTO.setLocation(new Location(100L, 100L));
        signUpDTO.setPhoneNumber("010-4822-3636");

        // when
        String findId = hostService.signUp(signUpDTO);

        // then
        Host findHost = hostService.findById(findId);
        Assertions.assertThat(findHost.getHostId()).isEqualTo(testHostId);
    }
}