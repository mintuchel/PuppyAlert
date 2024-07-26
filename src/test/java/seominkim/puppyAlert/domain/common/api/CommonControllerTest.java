package seominkim.puppyAlert.domain.common.api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.common.dto.request.LoginRequest;
import seominkim.puppyAlert.domain.common.dto.request.SignUpRequest;
import seominkim.puppyAlert.domain.common.dto.response.LoginResponse;
import seominkim.puppyAlert.domain.common.dto.response.SignUpResponse;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.service.HostService;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.global.dto.UserInfoResponse;
import seominkim.puppyAlert.global.entity.Location;
import seominkim.puppyAlert.global.entity.UserType;

import java.time.LocalDate;

@SpringBootTest
public class CommonControllerTest {

    @Autowired CommonController commonController;
    @Autowired HostService hostService;

    @PersistenceContext
    EntityManager em;

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
        String detailAddress = "축구장에 사는 놈임";
        Location location = new Location(100.135135, 135.12435);
        String phoneNumber = "010-4822-3636";
        UserType userType = UserType.HOST;

        SignUpRequest signUpRequest = new SignUpRequest(
                testHostId,
                password,
                nickName,
                name,
                birth,
                address,
                detailAddress,
                location,
                phoneNumber,
                userType
        );

        // when
        SignUpResponse signUpResponse = commonController.signUp(signUpRequest);

        // then
        UserInfoResponse findHost = hostService.findById(signUpResponse.id());
        Assertions.assertThat(findHost.userId()).isEqualTo(testHostId);
    }

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
        host.setDetailAddress("파리 어느 아파트에 살겠찌");
        host.setLocation(new Location(133.4135,137.58357));
        host.setPhoneNumber("010-4822-3636");

        em.persist(host);

        // when
        LoginRequest request = new LoginRequest(
                host.getHostId(),
                host.getPassword()
        );

        // then
        LoginResponse loginResponse = commonController.login(request);

        Assertions.assertThat(loginResponse.id()).isEqualTo(host.getHostId());
        Assertions.assertThat(loginResponse.userType()).isEqualTo(UserType.HOST);
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
        puppy.setDetailAddress("캄프누");
        puppy.setLocation(new Location(200.3562254, 200.1241));
        puppy.setPhoneNumber("010-1111-2222");

        em.persist(puppy);

        // then
        LoginRequest request = new LoginRequest(
                puppy.getPuppyId(),
                puppy.getPassword()
        );

        // then
        LoginResponse loginResponse = commonController.login(request);

        Assertions.assertThat(loginResponse.id()).isEqualTo(puppy.getPuppyId());
        Assertions.assertThat(loginResponse.userType()).isEqualTo(UserType.PUPPY);
    }
}