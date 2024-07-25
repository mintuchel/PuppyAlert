package seominkim.puppyAlert.domain.host.service;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.common.dto.request.SignUpRequest;
import seominkim.puppyAlert.global.dto.UserInfoResponse;
import seominkim.puppyAlert.global.entity.Location;
import seominkim.puppyAlert.global.entity.UserType;

import java.time.LocalDate;

@SpringBootTest
public class HostServiceTest {

    // 일반적으로, 테스트 코드에서 서비스 클래스를 테스트할 때 해당 서비스 클래스에서 사용하는
    // 리포지토리나 다른 의존성을 명시적으로 주입하지 않아도 됩니다.
    // 서비스 클래스는 스프링의 의존성 주입(DI)에 의해 자동으로 리포지토리 빈을 주입받기 때문입니다.
    @Autowired HostService hostService;
    @Autowired EntityManager em;

    @Test
    @Transactional
    @Rollback
    public void signUpTest(){
        // given
        String testHostId = "Havertz";
        String password = "29";
        String nickName = "내이름은HAVERTZ";
        String name = "하베르츠";
        LocalDate birth = LocalDate.now();
        String address = "독일 베를린";
        Location location = new Location(100.135135, 135.12435); // Initialize Location as needed
        String phoneNumber = "010-4822-3636";
        UserType userType = UserType.HOST; // Replace with appropriate UserType value

        SignUpRequest signUpRequest = new SignUpRequest(
                testHostId,
                password,
                nickName,
                name,
                birth,
                address,
                location,
                phoneNumber,
                userType
        );

        // when
        String findId = hostService.signUp(signUpRequest);

        // then
        UserInfoResponse findHost = hostService.findById(findId);
        Assertions.assertThat(findHost.userId()).isEqualTo(testHostId);
    }
}