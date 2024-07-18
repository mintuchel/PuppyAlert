package seominkim.puppyAlert.domain.zipbob.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.service.HostService;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.puppy.service.PuppyService;
import seominkim.puppyAlert.domain.zipbob.dto.ZipbobRequestDTO;
import seominkim.puppyAlert.domain.zipbob.entity.Zipbob;
import seominkim.puppyAlert.domain.zipbob.entity.ZipbobStatus;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class ZipbobServiceTest {

    @Autowired
    ZipbobService zipbobService;
    @Autowired
    HostService hostService;
    @Autowired
    PuppyService puppyService;

    @Autowired EntityManager em;

    @Test
    @Transactional
    @Rollback
    public void findOneTest(){

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
}