package seominkim.puppyAlert.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seominkim.puppyAlert.entity.*;
import seominkim.puppyAlert.repository.ZipbobRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class ZipbobServiceTest {

    @Autowired ZipbobService zipbobService;
    @Autowired HostService hostService;
    @Autowired PuppyService puppyService;

    @Test
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

        Zipbob 제육덮밥 = Zipbob.builder()
                .menu("제육덮밥")
                .puppy(puppy)
                .host(host)
                .time(LocalDateTime.now())
                .status(ZipbobStatus.MATCHED)
                .build();

        hostService.join(host);
        puppyService.join(puppy);

        Long savedId = zipbobService.add(제육덮밥);
        Zipbob resultZipbob = zipbobService.findOne(savedId);

        Assertions.assertThat(resultZipbob.getZipbobId()).isEqualTo(제육덮밥.getZipbobId());
    }
}