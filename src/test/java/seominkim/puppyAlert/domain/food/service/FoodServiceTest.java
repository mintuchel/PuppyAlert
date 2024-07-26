package seominkim.puppyAlert.domain.food.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.food.dto.FoodResponse;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.food.dto.FoodRequest;
import seominkim.puppyAlert.domain.food.entity.FoodStatus;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class FoodServiceTest {

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
    public void findOneTest(){

        // given
        Host host = em.find(Host.class, "Ronaldo");

        // when
        FoodRequest foodRequest = new FoodRequest(
                host.getHostId(),
                "제육덮밥",
                LocalDateTime.now(),
                FoodStatus.MATCHED
        );

        Long savedId = foodService.add(foodRequest);

        // then
        FoodResponse response = foodService.findById(savedId);
        Assertions.assertThat(response.foodId()).isEqualTo(savedId);
    }

    @Test
    @Transactional
    @Rollback
    public void getMostRecentFoodTest(){

        // given
        Food food1 = new Food();
        food1.setHost(em.find(Host.class, "Ronaldo"));
        food1.setPuppy(em.find(Puppy.class, "Messi"));
        food1.setMenu("집밥1");
        food1.setTime(LocalDateTime.of(2024,6,18,8,24,16));
        food1.setStatus(FoodStatus.MATCHED);

        Food food2 = new Food();
        food2.setHost(em.find(Host.class, "Ronaldo"));
        food2.setPuppy(em.find(Puppy.class, "Messi"));
        food2.setMenu("집밥2");
        food2.setTime(LocalDateTime.of(2024,7,20,5,50,32));
        food2.setStatus(FoodStatus.MATCHED);

        em.persist(food1);
        em.persist(food2);

        // when
        Food mostRecentFood = foodService.getMostRecentFood("Messi","Ronaldo");

        // then
        Assertions.assertThat(mostRecentFood).isEqualTo(food2);
    }
}