package seominkim.puppyAlert.domain.food.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.menu.entity.Menu;
import seominkim.puppyAlert.domain.food.entity.FoodStatus;
import seominkim.puppyAlert.domain.user.entity.User;
import seominkim.puppyAlert.global.entity.Location;
import seominkim.puppyAlert.global.entity.UserType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@ActiveProfiles("test")
public class FoodServiceTest {

    @Autowired FoodService foodService;
    @Autowired EntityManager em;

    @BeforeEach
    public void initTestDummy(){
        User host1 = new User();
        host1.setId("Ronaldo");
        host1.setName("호날두");
        host1.setNickName("iam노쇼에요");
        host1.setPassword("7");
        host1.setBirth(LocalDate.now());
        host1.setAddress("레알 마드리드");
        host1.setDetailAddress("산티아고 베르나베우");
        host1.setLocation(new Location(100.5, 100.1));
        host1.setPhoneNumber("010-4822-3636");
        host1.setUserType(UserType.HOST);

        User host2 = new User();
        host2.setId("Neymar");
        host2.setName("네이마르");
        host2.setNickName("밥은묵고다니냐");
        host2.setPassword("11");
        host2.setBirth(LocalDate.now());
        host2.setAddress("파리생제르망");
        host2.setDetailAddress("구장 이름 까먹음");
        host2.setLocation(new Location(100.5, 100.1));
        host2.setPhoneNumber("010-4465-8798");
        host2.setUserType(UserType.HOST);

        User user1 = new User();
        user1.setId("Messi");
        user1.setName("메시");
        user1.setNickName("요리조리비사이로막가드리블러");
        user1.setPassword("10");
        user1.setBirth(LocalDate.now());
        user1.setAddress("바르셀로나");
        user1.setDetailAddress("캄프누");
        user1.setLocation(new Location(200.3, 200.2));
        user1.setPhoneNumber("010-1111-2222");
        user1.setUserType(UserType.PUPPY);

        Menu menu1 = new Menu();
        menu1.setMenuName("testMenu1");
        menu1.setImageURL("testURL1");

        Menu menu2 = new Menu();
        menu2.setMenuName("testMenu2");
        menu2.setImageURL("testURL2");

        em.persist(host1);
        em.persist(host2);
        em.persist(user1);
        em.persist(menu1);
        em.persist(menu2);
    }

    @Test
    @Transactional
    @Rollback
    public void getHistoryTest(){
        // given
        User host1 = em.find(User.class, "Ronaldo");
        User host2 = em.find(User.class, "Neymar");

        System.out.println(host1.getHostFoods());

        // when
        Assertions.assertThat(host2.getHostFoods().size()).isEqualTo(0);
    }

    @Test
    @Transactional
    @Rollback
    public void getMostRecentFoodTest(){

        // given
        Food food1 = new Food();
        food1.setHost(em.find(User.class, "Ronaldo"));
        food1.setPuppy(em.find(User.class, "Messi"));
        food1.setMenu(em.find(Menu.class, "testMenu1"));
        food1.setTime(LocalDateTime.of(2020,6,18,8,24,16));
        food1.setStatus(FoodStatus.MATCHED);

        Food food2 = new Food();
        food2.setHost(em.find(User.class, "Ronaldo"));
        food2.setPuppy(em.find(User.class, "Messi"));
        food2.setMenu(em.find(Menu.class, "testMenu2"));
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