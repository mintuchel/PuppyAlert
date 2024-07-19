package seominkim.puppyAlert.domain.puppy.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void initTestDummy(){
        Host host = new Host();
        host.setHostId("Ronaldo");
        host.setName("호날두");
        host.setPassword("777");
        host.setBirth(LocalDate.now());
        host.setLocation(new Location(100L, 100L));
        host.setPhoneNumber("010-4822-3636");

        Puppy puppy = new Puppy();
        puppy.setPuppyId("Messi");
        puppy.setName("메시");
        puppy.setPassword("10");
        puppy.setBirth(LocalDate.now());
        puppy.setLocation(new Location(200L, 200L));
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
        ZipbobRequestDTO zipbobRequestDTO = new ZipbobRequestDTO();
        zipbobRequestDTO.setHostId(host.getHostId());
        zipbobRequestDTO.setMenu("제육덮밥");
        zipbobRequestDTO.setTime(LocalDateTime.now());
        zipbobRequestDTO.setStatus(ZipbobStatus.READY);

        Long savedId = zipbobService.add(zipbobRequestDTO);

        System.out.println("Host ID : " + em.find(Zipbob.class, savedId).getHost().getHostId());
        // when
        System.out.println("==========");
        System.out.println("Before Matched");
        Zipbob findZipbob = em.find(Zipbob.class, savedId);
        System.out.println("zipbobId : " + findZipbob.getZipbobId());
        System.out.println("hostId   : " + findZipbob.getHost().getHostId());
        if(findZipbob.getPuppy()==null){
            System.out.println("puppyId  : null");
        }else{
            System.out.println("puppyId  : " + findZipbob.getPuppy().getPuppyId());
        }
        System.out.println("menu     : " + findZipbob.getMenu());
        System.out.println("time     : " + findZipbob.getTime());
        System.out.println("status   : " + findZipbob.getStatus().toString());


        MatchRequestDTO matchRequestDTO = new MatchRequestDTO();
        matchRequestDTO.setZipbobId(savedId);
        matchRequestDTO.setPuppyId(puppy.getPuppyId());

        Zipbob matchedZipbob = puppyService.matchZipbob(matchRequestDTO);

        // then
        System.out.println("==========");
        System.out.println("After Matched");
        findZipbob = em.find(Zipbob.class, savedId);
        System.out.println("zipbobId : " + findZipbob.getZipbobId());
        System.out.println("hostId   : " + findZipbob.getHost().getHostId());
        System.out.println("puppyId  : " + findZipbob.getPuppy().getPuppyId());
        System.out.println("menu     : " + findZipbob.getMenu());
        System.out.println("time     : " + findZipbob.getTime());
        System.out.println("status   : " + findZipbob.getStatus().toString());

        Assertions.assertThat(savedId).isEqualTo(matchedZipbob.getZipbobId());
        // 이거 왜 null 뜨지??
        Assertions.assertThat(host.getHostId()).isEqualTo(matchedZipbob.getHost().getHostId());
        Assertions.assertThat(puppy.getPuppyId()).isEqualTo(matchedZipbob.getPuppy().getPuppyId());
    }
}