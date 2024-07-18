package seominkim.puppyAlert.domain.puppy.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.dto.MatchRequestDTO;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.zipbob.dto.ZipbobRequestDTO;
import seominkim.puppyAlert.domain.zipbob.entity.Zipbob;
import seominkim.puppyAlert.domain.zipbob.entity.ZipbobStatus;
import seominkim.puppyAlert.domain.zipbob.service.ZipbobService;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class PuppyServiceTest {

    @Autowired PuppyService puppyService;
    @Autowired ZipbobService zipbobService;
    @Autowired EntityManager em;

    @Test
    @Transactional
    @Rollback
    public void matchZipbobTest(){

        // given
        Host host = new Host();
        host.setHostId("Havertz");
        host.setName("하베르츠");
        host.setPassword("123");
        host.setBirth(LocalDate.now());
        host.setLocation(new Location(100L, 100L));
        host.setPhoneNumber("010-4822-3636");

        Puppy puppy = new Puppy();
        puppy.setPuppyId("Werner");
        puppy.setName("베르너");
        puppy.setPassword("456");
        puppy.setBirth(LocalDate.now());
        puppy.setLocation(new Location(200L, 200L));
        puppy.setPhoneNumber("010-1111-2222");

        em.persist(host);
        em.persist(puppy);

        // 아직 매칭안된 집밥
        ZipbobRequestDTO zipbobRequestDTO = new ZipbobRequestDTO();
        zipbobRequestDTO.setHostId(host.getHostId());
        zipbobRequestDTO.setMenu("제육덮밥");
        zipbobRequestDTO.setTime(LocalDateTime.now());
        zipbobRequestDTO.setStatus(ZipbobStatus.READY);

        Long savedId = zipbobService.add(zipbobRequestDTO);

        System.out.println("Host ID : " + em.find(Zipbob.class, savedId).getHost().getHostId());
        // when
        System.out.println("Before Matched : " + em.find(Zipbob.class, savedId).getStatus().toString());

        MatchRequestDTO matchRequestDTO = new MatchRequestDTO();
        matchRequestDTO.setZipbobId(savedId);
        matchRequestDTO.setPuppyId(puppy.getPuppyId());

        Zipbob matchedZipbob = puppyService.matchZipbob(matchRequestDTO);

        // then
        System.out.println("After Matched : " + em.find(Zipbob.class, savedId).getStatus().toString());

        Assertions.assertThat(savedId).isEqualTo(matchedZipbob.getZipbobId());
        Assertions.assertThat(host.getHostId()).isEqualTo(matchedZipbob.getHost().getHostId());
        Assertions.assertThat(puppy.getPuppyId()).isEqualTo(matchedZipbob.getPuppy().getPuppyId());
    }
}