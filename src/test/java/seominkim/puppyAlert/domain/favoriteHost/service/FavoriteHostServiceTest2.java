package seominkim.puppyAlert.domain.favoriteHost.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.favoriteHost.dto.request.FavoriteHostRequest;
import seominkim.puppyAlert.domain.favoriteHost.dto.response.FavoriteHostResponse;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.puppy.service.PuppyService;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class FavoriteHostServiceTest2 {

    @Autowired PuppyService puppyService;
    @Autowired FavoriteHostService favoriteHostService;
    @Autowired EntityManager em;

    @BeforeEach
    public void initTestDummy(){
        Host host1 = new Host();
        host1.setHostId("Ronaldo");
        host1.setName("호날두");
        host1.setNickName("iam노쇼에요");
        host1.setPassword("7");
        host1.setBirth(LocalDate.now());
        host1.setAddress("레알 마드리드");
        host1.setDetailAddress("산티아고 베르나베우");
        host1.setLocation(new Location(100.5, 100.1));
        host1.setPhoneNumber("010-4822-3636");

        Host host2 = new Host();
        host2.setHostId("Neymar");
        host2.setName("네이마르");
        host2.setNickName("밥은묵고다니냐");
        host2.setPassword("11");
        host2.setBirth(LocalDate.now());
        host2.setAddress("파리생제르망");
        host2.setDetailAddress("구장 이름 까먹음");
        host2.setLocation(new Location(100.5, 100.1));
        host2.setPhoneNumber("010-4465-8798");

        Puppy puppy = new Puppy();
        puppy.setPuppyId("Messi");
        puppy.setName("메시");
        puppy.setNickName("요리조리비사이로막가드리블러");
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

    @Test
    @Transactional(readOnly = true)
    @Rollback
    public void getFavoriteHostTest(){
        // given
        Host host = em.find(Host.class,"Ronaldo");
        Host host2 = em.find(Host.class, "Neymar");
        Puppy puppy = em.find(Puppy.class, "Messi");

        String hostId = host.getHostId();
        String puppyId = puppy.getPuppyId();

        FavoriteHostRequest request = new FavoriteHostRequest(hostId, puppyId);

        puppyService.addFavoriteHost(request);

        // when
        List<FavoriteHostResponse> list = puppyService.getFavoriteHost(puppyId);

        // then
        System.out.println(list);
    }
}