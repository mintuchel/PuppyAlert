package seominkim.puppyAlert.domain.favoriteHost.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.favoriteHost.dto.FavoriteHostRequest;
import seominkim.puppyAlert.domain.favoriteHost.dto.FavoriteHostResponse;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.food.entity.FoodStatus;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class FavoriteHostServiceTest {

    @Autowired FavoriteHostService favoriteHostService;
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
        host.setLocation(new Location(100.5, 100.1));
        host.setPhoneNumber("010-4822-3636");

        Puppy puppy = new Puppy();
        puppy.setPuppyId("Messi");
        puppy.setName("메시");
        puppy.setNickName("메신이라불러줘");
        puppy.setPassword("10");
        puppy.setBirth(LocalDate.now());
        puppy.setAddress("바르셀로나");
        puppy.setLocation(new Location(200.3, 200.2));
        puppy.setPhoneNumber("010-1111-2222");

        em.persist(host);
        em.persist(puppy);

        Food food1 = new Food();
        food1.setHost(host);
        food1.setPuppy(puppy);
        food1.setMenu("집밥1");
        food1.setTime(LocalDateTime.of(2024,6,18,8,24,16));
        food1.setStatus(FoodStatus.MATCHED);

        Food food2 = new Food();
        food2.setHost(host);
        food2.setPuppy(puppy);
        food2.setMenu("집밥2");
        food2.setTime(LocalDateTime.of(2024,7,20,5,50,32));
        food2.setStatus(FoodStatus.MATCHED);

        em.persist(food1);
        em.persist(food2);
    }

    @Test
    @Transactional
    @Rollback
    public void getFavoriteHostTest(){
        // given
        String hostId = em.find(Host.class,"Ronaldo").getHostId();
        String puppyId = em.find(Puppy.class, "Messi").getPuppyId();

        FavoriteHostRequest request = new FavoriteHostRequest(hostId, puppyId);

        // when
        Long favoriteHostId = favoriteHostService.addFavoriteHost(request);

        // then
        List<FavoriteHostResponse> favoriteHostDTOList = favoriteHostService.findAll("Messi");

        Assertions.assertThat(favoriteHostDTOList.get(0).hostId()).isEqualTo("Ronaldo");
    }

    @Test
    @Transactional
    @Rollback
    public void addDeleteFavoriteHostTest(){

        // given
        String hostId = em.find(Host.class,"Ronaldo").getHostId();
        String puppyId = em.find(Puppy.class, "Messi").getPuppyId();

        FavoriteHostRequest request = new FavoriteHostRequest(hostId, puppyId);

        Long addedFavoriteHostId = favoriteHostService.addFavoriteHost(request);

        // when
        Long deletedFavoriteHostId = favoriteHostService.deleteFavoriteHost(request);

        // then
        List<FavoriteHostResponse> favoriteHostDTOList = favoriteHostService.findAll("Ronaldo");

        Assertions.assertThat(addedFavoriteHostId).isEqualTo(deletedFavoriteHostId);
        Assertions.assertThat(favoriteHostDTOList.size()).isEqualTo(0);
    }
}