package seominkim.puppyAlert.domain.puppy.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.food.dto.response.AddFoodResponse;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.food.service.FoodService;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.service.HostService;
import seominkim.puppyAlert.domain.menu.entity.Menu;
import seominkim.puppyAlert.domain.puppy.dto.request.MatchRequest;
import seominkim.puppyAlert.domain.puppy.dto.response.MatchResponse;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.food.dto.request.FoodRequest;
import seominkim.puppyAlert.domain.food.entity.FoodStatus;
import seominkim.puppyAlert.global.dto.response.MatchHistoryResponse;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class PuppyServiceTest {

    @Autowired FoodService foodService;
    @Autowired PuppyService puppyService;
    @Autowired HostService hostService;
    @Autowired EntityManager em;

    @BeforeEach
    public void initTestDummy(){
        Host host = new Host();
        host.setHostId("Ronaldo");
        host.setName("호날두");
        host.setNickName("임마나는사실수비수야");
        host.setPassword("7");
        host.setBirth(LocalDate.now());
        host.setAddress("레알 마드리드");
        host.setDetailAddress("산티아고 베르나베우");
        host.setLocation(new Location(100.12, 100.44));
        host.setPhoneNumber("010-4822-3636");

        Puppy puppy = new Puppy();
        puppy.setPuppyId("Messi");
        puppy.setName("메시");
        puppy.setNickName("요리조리비사이로막가드리블러");
        puppy.setPassword("10");
        puppy.setBirth(LocalDate.now());
        puppy.setAddress("바르셀로나");
        puppy.setDetailAddress("캄프누");
        puppy.setLocation(new Location(200.3562254, 200.1241));
        puppy.setPhoneNumber("010-1111-2222");

        em.persist(host);
        em.persist(puppy);
    }

    @Test
    @Transactional
    @Rollback
    public void matchFoodTest(){
        // given
        Host host = em.find(Host.class, "Ronaldo");
        Puppy puppy = em.find(Puppy.class, "Messi");

        // 아직 매칭안된 집밥
        FoodRequest foodRequest = new FoodRequest(
                host.getHostId(),
                "알리오올리오",
                LocalDateTime.now(),
                FoodStatus.READY
        );

        AddFoodResponse addFoodResponse = hostService.addFood(foodRequest);
        Long savedId = addFoodResponse.foodId();

        // when
        System.out.println("==========");
        System.out.println("Before Matched");

        Food targetFood = em.find(Food.class, savedId);
        System.out.println("zipbobId : " + targetFood.getFoodId());
        System.out.println("hostId   : " + targetFood.getHost().getHostId());

        if(targetFood.getPuppy()==null) System.out.println("puppyId  : null");
        else System.out.println("puppyId  : " + targetFood.getPuppy().getPuppyId());

        System.out.println("menu     : " + targetFood.getMenu());
        System.out.println("time     : " + targetFood.getTime());
        System.out.println("status   : " + targetFood.getStatus().toString());


        MatchRequest matchRequest = new MatchRequest(
                savedId,
                puppy.getPuppyId()
        );

        MatchResponse matchResponse = puppyService.handleMatchRequest(matchRequest);

        // then
        System.out.println("==========");
        System.out.println("After Matched");
        Food matchedFood = em.find(Food.class, savedId);
        System.out.println("zipbobId : " + matchedFood.getFoodId());
        System.out.println("hostId   : " + matchedFood.getHost().getHostId());
        System.out.println("puppyId  : " + matchedFood.getPuppy().getPuppyId());
        System.out.println("menu     : " + matchedFood.getMenu());
        System.out.println("time     : " + matchedFood.getTime());
        System.out.println("status   : " + matchedFood.getStatus().toString());

        Assertions.assertThat(savedId).isEqualTo(matchResponse.foodId());
        Assertions.assertThat(host).isEqualTo(matchedFood.getHost());
        Assertions.assertThat(puppy).isEqualTo(matchedFood.getPuppy());
    }

    /*
    @Test
    @Transactional
    @Rollback
    public void getHistoryTest(){
        // given
        Menu menu = new Menu();
        menu.setMenuName("testMenu");
        menu.setImageURL("testURL");

        // 엔티티 영속
        // 1차 캐시에 저장
        em.persist(menu);

        Host host = em.find(Host.class, "Ronaldo");
        Puppy puppy = em.find(Puppy.class, "Messi");

        // Host 집밥 등록
        FoodRequest foodRequest = new FoodRequest(
                host.getHostId(),
                "testMenu",
                LocalDateTime.now(),
                FoodStatus.READY
        );

        AddFoodResponse addFoodResponse = hostService.addFood(foodRequest);
        Long savedId = addFoodResponse.foodId();
        System.out.println(savedId);

        // Puppy 집밥 신청
        MatchRequest matchRequest = new MatchRequest(
                savedId,
                puppy.getPuppyId()
        );

        MatchResponse matchResponse = puppyService.handleMatchRequest(matchRequest);
        System.out.println(matchResponse.hostId());
        System.out.println(matchResponse.puppyId());
        System.out.println(matchResponse.foodId());

        // when
        List<MatchHistoryResponse> matchHistoryResponseList = puppyService.getHistory("Messi");
        MatchHistoryResponse matchHistoryResponse = matchHistoryResponseList.get(0);

        // then
        Assertions.assertThat(matchHistoryResponse.partnerId()).isEqualTo("Ronaldo");
        Assertions.assertThat(matchHistoryResponse.menuName()).isEqualTo("testMenu");
    }
     */
}