package seominkim.puppyAlert.domain.zipbob.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.zipbob.dto.ZipbobRequestDTO;
import seominkim.puppyAlert.domain.zipbob.entity.Zipbob;
import seominkim.puppyAlert.domain.zipbob.entity.ZipbobStatus;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class ZipbobServiceTest {

    @Autowired ZipbobService zipbobService;
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
    }

    @Test
    @Transactional
    @Rollback
    public void findOneTest(){

        // given
        Host host = em.find(Host.class, "Ronaldo");

        // when
        ZipbobRequestDTO zipbobRequestDTO = new ZipbobRequestDTO();
        zipbobRequestDTO.setHostId(host.getHostId());
        zipbobRequestDTO.setMenu("제육덮밥");
        zipbobRequestDTO.setTime(LocalDateTime.now());
        zipbobRequestDTO.setStatus(ZipbobStatus.MATCHED);

        Long savedId = zipbobService.add(zipbobRequestDTO);

        // then
        Zipbob resultZipbob = zipbobService.findOne(savedId);
        Assertions.assertThat(resultZipbob.getZipbobId()).isEqualTo(savedId);
    }

    @Test
    @Transactional
    @Rollback
    public void getMostRecentZipbobTest(){

        // given
        Zipbob zipbob1 = new Zipbob();
        zipbob1.setHost(em.find(Host.class, "Ronaldo"));
        zipbob1.setPuppy(em.find(Puppy.class, "Messi"));
        zipbob1.setMenu("집밥1");
        zipbob1.setTime(LocalDateTime.of(2024,6,18,8,24,16));
        zipbob1.setStatus(ZipbobStatus.MATCHED);

        Zipbob zipbob2 = new Zipbob();
        zipbob2.setHost(em.find(Host.class, "Ronaldo"));
        zipbob2.setPuppy(em.find(Puppy.class, "Messi"));
        zipbob2.setMenu("집밥2");
        zipbob2.setTime(LocalDateTime.of(2024,7,20,5,50,32));
        zipbob2.setStatus(ZipbobStatus.MATCHED);

        em.persist(zipbob1);
        em.persist(zipbob2);

        // when
        Zipbob mostRecentZipbob = zipbobService.getMostRecentZipbob("Messi","Ronaldo");

        // then
        Assertions.assertThat(mostRecentZipbob).isEqualTo(zipbob2);
    }
}