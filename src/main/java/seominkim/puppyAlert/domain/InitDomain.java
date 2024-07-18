package seominkim.puppyAlert.domain;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.zipbob.entity.Zipbob;
import seominkim.puppyAlert.domain.zipbob.entity.ZipbobStatus;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDomain {
    private final InitDomainService initDomainService;

    @PostConstruct
    private void init(){
        initDomainService.init();
    }

    @Component
    static class InitDomainService {
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init(){

            //========== DUMMY HOST ==========//

            Host host1 = new Host();
            host1.setHostId("seo");
            host1.setName("서상혁");
            host1.setPassword("qwer123");
            host1.setBirth(LocalDate.now());
            host1.setLocation(new Location(318L, 133L));
            host1.setPhoneNumber("010-1582-3145");
            em.persist(host1);

            Host host2 = new Host();
            host2.setHostId("min");
            host2.setName("민재홍");
            host2.setPassword("abcd");
            host2.setBirth(LocalDate.now());
            host2.setLocation(new Location(235L, 175L));
            host2.setPhoneNumber("010-4822-3636");
            em.persist(host2);

            //========== DUMMY PUPPY ==========//

            Puppy puppy1 = new Puppy();
            puppy1.setPuppyId("kim");
            puppy1.setName("김지원");
            puppy1.setPassword("asck112!");
            puppy1.setBirth(LocalDate.now());
            puppy1.setLocation(new Location(207L, 147L));
            puppy1.setPhoneNumber("010-1111-2222");

            em.persist(puppy1);

            Puppy puppy2 = new Puppy();
            puppy2.setPuppyId("park");
            puppy2.setName("박지성");
            puppy2.setPassword("13");
            puppy2.setBirth(LocalDate.now());
            puppy2.setLocation(new Location(188L, 201L));
            puppy2.setPhoneNumber("010-8754-0321");
            em.persist(puppy2);

            //========== DUMMY ZIPBOB ==========//

            Zipbob zipbob1 = new Zipbob();
            zipbob1.setHost(host1);
            zipbob1.setTime(LocalDateTime.now());
            zipbob1.setStatus(ZipbobStatus.READY);
            zipbob1.setMenu("크림파스타");

            em.persist(zipbob1);

            Zipbob zipbob2 = new Zipbob();
            zipbob2.setHost(host2);
            zipbob2.setPuppy(puppy1);
            zipbob2.setTime(LocalDateTime.now());
            zipbob2.setStatus(ZipbobStatus.MATCHED);
            zipbob2.setMenu("제육제육제육");

            em.persist(zipbob2);

            Zipbob zipbob3 = new Zipbob();
            zipbob3.setHost(host2);
            zipbob3.setPuppy(puppy2);
            zipbob3.setTime(LocalDateTime.now());
            zipbob3.setStatus(ZipbobStatus.MATCHED);
            zipbob3.setMenu("버터오징어");

            em.persist(zipbob3);
        }
    }
}
