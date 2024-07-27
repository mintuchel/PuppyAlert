package seominkim.puppyAlert.domain.food.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
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
        Host host1 = new Host();
        host1.setHostId("Ronaldo");
        host1.setName("호날두");
        host1.setNickName("내가바로좆두다");
        host1.setPassword("777");
        host1.setBirth(LocalDate.now());
        host1.setAddress("레알 마드리드");
        host1.setDetailAddress("산티아고 베르나베우");
        host1.setLocation(new Location(100.5, 100.1));
        host1.setPhoneNumber("010-4822-3636");

        Host host2 = new Host();
        host2.setHostId("Neymar");
        host2.setName("네이마르");
        host2.setNickName("아임네이마아르");
        host2.setPassword("101011");
        host2.setBirth(LocalDate.now());
        host2.setAddress("파리생제르망");
        host2.setDetailAddress("구장 이름 까먹음");
        host2.setLocation(new Location(100.5, 100.1));
        host2.setPhoneNumber("010-4465-8798");

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

        em.persist(host1);
        em.persist(host2);
        em.persist(puppy);
    }

    @Test
    @Transactional
    @Rollback
    public void getHistoryTest(){
        // given
        Host host1 = em.find(Host.class, "Ronaldo");
        Host host2 = em.find(Host.class, "Neymar");

        System.out.println(host1.getFoodList());

        // when
        Assertions.assertThat(host2.getFoodList().size()).isEqualTo(0);
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
        food1.setImageURL("dummyUrl");
        food1.setTime(LocalDateTime.of(2024,6,18,8,24,16));
        food1.setStatus(FoodStatus.MATCHED);

        Food food2 = new Food();
        food2.setHost(em.find(Host.class, "Ronaldo"));
        food2.setPuppy(em.find(Puppy.class, "Messi"));
        food2.setMenu("집밥2");
        food2.setImageURL("dummyUrl");
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