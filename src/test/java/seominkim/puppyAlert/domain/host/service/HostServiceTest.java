package seominkim.puppyAlert.domain.host.service;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.food.dto.request.FoodRequest;
import seominkim.puppyAlert.domain.food.dto.response.AddFoodResponse;
import seominkim.puppyAlert.domain.food.dto.response.FoodInfoResponse;
import seominkim.puppyAlert.domain.food.entity.FoodStatus;
import seominkim.puppyAlert.domain.food.service.FoodService;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class HostServiceTest {

    // 일반적으로, 테스트 코드에서 서비스 클래스를 테스트할 때 해당 서비스 클래스에서 사용하는
    // 리포지토리나 다른 의존성을 명시적으로 주입하지 않아도 됩니다.
    // 서비스 클래스는 스프링의 의존성 주입(DI)에 의해 자동으로 리포지토리 빈을 주입받기 때문입니다.
    @Autowired HostService hostService;
    @Autowired FoodService foodService;
    @Autowired EntityManager em;

    @BeforeEach
    public void initTestDummy(){
        Host host = new Host();
        host.setHostId("Ronaldo");
        host.setName("호날두");
        host.setNickName("내가바로좆두다");
        host.setPassword("777");
        host.setBirth(LocalDate.now());
        host.setAddress("레알 마드리드");
        host.setDetailAddress("산티아고 베르나베우");
        host.setLocation(new Location(100.5, 100.1));
        host.setPhoneNumber("010-4822-3636");

        Puppy puppy = new Puppy();
        puppy.setPuppyId("Messi");
        puppy.setName("메시");
        puppy.setNickName("신이라불러라");
        puppy.setPassword("10");
        puppy.setBirth(LocalDate.now());
        puppy.setAddress("바르셀로나");
        puppy.setDetailAddress("캄프누");
        puppy.setLocation(new Location(200.3, 200.2));
        puppy.setPhoneNumber("010-1111-2222");

        em.persist(host);
        em.persist(puppy);
    }

    @Test
    @Transactional
    @Rollback
    public void addFoodTest(){
        // given
        Host host = em.find(Host.class, "Ronaldo");

        // when
        FoodRequest foodRequest = new FoodRequest(
                host.getHostId(),
                "제육덮밥",
                LocalDateTime.now(),
                FoodStatus.READY
        );

        AddFoodResponse addFoodResponse = hostService.addFood(foodRequest);
        Long savedId = addFoodResponse.foodId();

        // then
        FoodInfoResponse response = foodService.findById(savedId);
        Assertions.assertThat(response.foodId()).isEqualTo(savedId);
    }
}