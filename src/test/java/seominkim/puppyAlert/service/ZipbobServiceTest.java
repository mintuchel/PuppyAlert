package seominkim.puppyAlert.service;

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
import seominkim.puppyAlert.domain.zipbob.entity.Zipbob;
import seominkim.puppyAlert.domain.zipbob.entity.ZipbobStatus;
import seominkim.puppyAlert.domain.zipbob.service.ZipbobService;
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

        Host host = Host.builder()
                .hostId("Havertz")
                .name("하베르츠")
                .password("123")
                .birth(LocalDate.now())
                .location(new Location(100L, 100L))
                .phoneNumber("010-4822-3636")
                .build();

        Puppy puppy = Puppy.builder()
                .puppyId("Werner")
                .name("베르너")
                .password("456")
                .birth(LocalDate.now())
                .location(new Location(200L, 200L))
                .phoneNumber("010-1111-2222")
                .build();

        em.persist(host);
        em.persist(puppy);

        Zipbob 제육덮밥 = Zipbob.builder()
                .menu("제육덮밥")
                .puppy(puppy)
                .host(host)
                .time(LocalDateTime.now())
                .status(ZipbobStatus.MATCHED)
                .build();

        Long savedId = zipbobService.add(제육덮밥);
        Zipbob resultZipbob = zipbobService.findOne(savedId);

        Assertions.assertThat(resultZipbob.getZipbobId()).isEqualTo(제육덮밥.getZipbobId());
    }
}