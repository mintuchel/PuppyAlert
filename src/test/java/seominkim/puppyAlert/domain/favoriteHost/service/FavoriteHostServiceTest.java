package seominkim.puppyAlert.domain.favoriteHost.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.favoriteHost.dto.FavoriteHostRequestDTO;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.zipbob.entity.Zipbob;
import seominkim.puppyAlert.domain.zipbob.entity.ZipbobStatus;
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
        host.setPassword("777");
        host.setBirth(LocalDate.now());
        host.setLocation(new Location(100.5, 100.1));
        host.setPhoneNumber("010-4822-3636");

        Puppy puppy = new Puppy();
        puppy.setPuppyId("Messi");
        puppy.setName("메시");
        puppy.setPassword("10");
        puppy.setBirth(LocalDate.now());
        puppy.setLocation(new Location(200.3, 200.2));
        puppy.setPhoneNumber("010-1111-2222");

        em.persist(host);
        em.persist(puppy);

        Zipbob zipbob1 = new Zipbob();
        zipbob1.setHost(host);
        zipbob1.setPuppy(puppy);
        zipbob1.setMenu("집밥1");
        zipbob1.setTime(LocalDateTime.of(2024,6,18,8,24,16));
        zipbob1.setStatus(ZipbobStatus.MATCHED);

        Zipbob zipbob2 = new Zipbob();
        zipbob2.setHost(host);
        zipbob2.setPuppy(puppy);
        zipbob2.setMenu("집밥2");
        zipbob2.setTime(LocalDateTime.of(2024,7,20,5,50,32));
        zipbob2.setStatus(ZipbobStatus.MATCHED);

        em.persist(zipbob1);
        em.persist(zipbob2);
    }

    @Test
    @Transactional
    @Rollback
    public void getFavoriteHostTest(){
        // given
        FavoriteHostRequestDTO favoriteHostRequestDTO = new FavoriteHostRequestDTO();
        favoriteHostRequestDTO.setHostId(em.find(Host.class,"Ronaldo").getHostId());
        favoriteHostRequestDTO.setPuppyId(em.find(Puppy.class, "Messi").getPuppyId());

        // when
        Long favoriteHostId = favoriteHostService.addFavoriteHost(favoriteHostRequestDTO);

        // then
        List<FavoriteHost> favoriteHostList = favoriteHostService.findAll("Messi");

        Assertions.assertThat(favoriteHostList.get(0).getFavoriteHostId()).isEqualTo(favoriteHostId);
    }

    @Test
    @Transactional
    @Rollback
    public void addDeleteFavoriteHostTest(){

        // given
        FavoriteHostRequestDTO favoriteHostRequestDTO = new FavoriteHostRequestDTO();
        favoriteHostRequestDTO.setHostId(em.find(Host.class,"Ronaldo").getHostId());
        favoriteHostRequestDTO.setPuppyId(em.find(Puppy.class, "Messi").getPuppyId());
        Long addedFavoriteHostId = favoriteHostService.addFavoriteHost(favoriteHostRequestDTO);

        // when
        Long deletedFavoriteHostId = favoriteHostService.deleteFavoriteHost(favoriteHostRequestDTO);

        // then
        List<FavoriteHost> favoriteHostList = favoriteHostService.findAll("Ronaldo");

        Assertions.assertThat(addedFavoriteHostId).isEqualTo(deletedFavoriteHostId);
        Assertions.assertThat(favoriteHostList.size()).isEqualTo(0);
    }
}