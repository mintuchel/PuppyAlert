package seominkim.puppyAlert.domain.puppy.service;

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
import seominkim.puppyAlert.domain.puppy.dto.MatchRequestDTO;
import seominkim.puppyAlert.domain.puppy.dto.MatchResponseDTO;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.food.dto.FoodRequestDTO;
import seominkim.puppyAlert.domain.food.entity.FoodStatus;
import seominkim.puppyAlert.domain.food.service.FoodService;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class PuppyServiceTest {

    @Autowired PuppyService puppyService;
    @Autowired
    FoodService foodService;
    @Autowired EntityManager em;

    @BeforeEach
    public void initTestDummy(){
        Host host = new Host();
        host.setHostId("Ronaldo");
        host.setName("호날두");
        host.setNickName("나는사실수비수야");
        host.setPassword("777");
        host.setBirth(LocalDate.now());
        host.setAddress("레알 마드리드");
        host.setLocation(new Location(100.12, 100.44));
        host.setPhoneNumber("010-4822-3636");

        Puppy puppy = new Puppy();
        puppy.setPuppyId("Messi");
        puppy.setName("메시");
        puppy.setNickName("응이번발롱도르도나야");
        puppy.setPassword("10");
        puppy.setBirth(LocalDate.now());
        puppy.setAddress("바르셀로나");
        puppy.setLocation(new Location(200.3562254, 200.1241));
        puppy.setPhoneNumber("010-1111-2222");

        em.persist(host);
        em.persist(puppy);
    }

    @Test
    @Transactional
    @Rollback
    public void matchZipbobTest(){

        // given
        Host host = em.find(Host.class, "Ronaldo");
        Puppy puppy = em.find(Puppy.class, "Messi");

        // 아직 매칭안된 집밥
        FoodRequestDTO foodRequestDTO = new FoodRequestDTO();
        foodRequestDTO.setHostId(host.getHostId());
        foodRequestDTO.setMenu("제육덮밥");
        foodRequestDTO.setTime(LocalDateTime.now());
        foodRequestDTO.setStatus(FoodStatus.READY);

        Long savedId = foodService.add(foodRequestDTO);

        System.out.println("Host ID : " + em.find(Food.class, savedId).getHost().getHostId());

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


        MatchRequestDTO matchRequestDTO = new MatchRequestDTO();
        matchRequestDTO.setFoodId(savedId);
        matchRequestDTO.setPuppyId(puppy.getPuppyId());

        MatchResponseDTO matchResponseDTO = puppyService.matchZipbob(matchRequestDTO);

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

        Assertions.assertThat(savedId).isEqualTo(matchResponseDTO.getFoodId());
        // 이거 왜 null 뜨지??
        Assertions.assertThat(host.getHostId()).isEqualTo(matchedFood.getHost().getHostId());
        Assertions.assertThat(puppy.getPuppyId()).isEqualTo(matchedFood.getPuppy().getPuppyId());
    }
}