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
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.puppy.service.PuppyService;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class FavoriteHostServiceTest {

    @Autowired PuppyService puppyService;
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
        host.setDetailAddress("산티아고 베르나베우");
        host.setLocation(new Location(100.5, 100.1));
        host.setPhoneNumber("010-4822-3636");

        Puppy puppy = new Puppy();
        puppy.setPuppyId("Messi");
        puppy.setName("메시");
        puppy.setNickName("메신이라불러줘");
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
    public void addFavoriteHostTest(){
        // given
        Host host = em.find(Host.class,"Ronaldo");
        Puppy puppy = em.find(Puppy.class, "Messi");
        String hostId = host.getHostId();
        String puppyId = puppy.getPuppyId();

        FavoriteHostRequest request = new FavoriteHostRequest(hostId, puppyId);

        // when
        puppyService.addFavoriteHost(request);

        // then
        Assertions.assertThat(favoriteHostService.isFavoriteHost(puppy, host)).isTrue();
    }

    @Test
    @Transactional
    @Rollback
    public void deleteFavoriteHostTest(){

        // given
        Host host = em.find(Host.class,"Ronaldo");
        Puppy puppy = em.find(Puppy.class, "Messi");
        String hostId = host.getHostId();
        String puppyId = puppy.getPuppyId();

        FavoriteHostRequest request = new FavoriteHostRequest(hostId, puppyId);

        puppyService.addFavoriteHost(request);

        Assertions.assertThat(favoriteHostService.isFavoriteHost(puppy, host)).isTrue();

        // when
        puppyService.deleteFavoriteHost(request);

        // then
        Assertions.assertThat(favoriteHostService.isFavoriteHost(puppy, host)).isFalse();
    }
}