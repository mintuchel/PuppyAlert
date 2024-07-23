package seominkim.puppyAlert.domain;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;
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
        initDomainService.initHost();
        initDomainService.initPuppy();
        initDomainService.initZipbob();
        initDomainService.initFavoriteHost();
    }

    @Component
    static class InitDomainService {
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void initHost() {
            Host host1 = new Host();
            host1.setHostId("KwonOhSung");
            host1.setName("권오성");
            host1.setPassword("qwer123");
            host1.setBirth(LocalDate.now());
            host1.setLocation(new Location(37.551833438845506, 127.0753138390134));
            host1.setPhoneNumber("010-1582-3145");
            em.persist(host1);

            Host host2 = new Host();
            host2.setHostId("kimSeHyeon");
            host2.setName("김세현");
            host2.setPassword("abcd");
            host2.setBirth(LocalDate.now());
            host2.setLocation(new Location(37.54965636279012, 127.0750237101941));
            host2.setPhoneNumber("010-3244-7988");
            em.persist(host2);

            Host host3 = new Host();
            host3.setHostId("ChoSangJun");
            host3.setName("조상준");
            host3.setPassword("abcdefg");
            host3.setBirth(LocalDate.now());
            host3.setLocation(new Location(37.551833438845506, 127.0753138390134));
            host3.setPhoneNumber("010-5814-6568");
            em.persist(host3);

            Host host4 = new Host();
            host4.setHostId("LimWooJin");
            host4.setName("임우진");
            host4.setPassword("xyzw");
            host4.setBirth(LocalDate.now());
            host4.setLocation(new Location(37.54965636279012, 127.0750237101941));
            host4.setPhoneNumber("010-4198-1241");
            em.persist(host4);
        }

        @Transactional
        public void initPuppy() {
            Puppy puppy1 = new Puppy();
            puppy1.setPuppyId("SeoSangHyeok");
            puppy1.setName("서상혁");
            puppy1.setPassword("asck112!");
            puppy1.setBirth(LocalDate.now());
            puppy1.setLocation(new Location(37.8123, 124.9854));
            puppy1.setPhoneNumber("010-1111-2222");

            em.persist(puppy1);

            Puppy puppy2 = new Puppy();
            puppy2.setPuppyId("MinJaeHong");
            puppy2.setName("민재홍");
            puppy2.setPassword("gkqpfmcm29");
            puppy2.setBirth(LocalDate.now());
            puppy2.setLocation(new Location(37.59999, 125.45));
            puppy2.setPhoneNumber("010-6457-9554");

            em.persist(puppy2);

            Puppy puppy3 = new Puppy();
            puppy3.setPuppyId("KimJiWon");
            puppy3.setName("김지원");
            puppy3.setPassword("dmaqkpv9");
            puppy3.setBirth(LocalDate.now());
            puppy3.setLocation(new Location(37.6569, 125.321));
            puppy3.setPhoneNumber("010-8221-7458");

            em.persist(puppy3);
        }

        @Transactional
        public void initZipbob(){
            Zipbob zipbob1 = new Zipbob();
            zipbob1.setHost(em.find(Host.class,"KwonOhSung"));
            zipbob1.setPuppy(em.find(Puppy.class, "SeoSangHyeok"));
            zipbob1.setTime(LocalDateTime.of(2024,7,19,14,30));
            zipbob1.setStatus(ZipbobStatus.MATCHED);
            zipbob1.setMenu("크림파스타");

            em.persist(zipbob1);

            Zipbob zipbob2 = new Zipbob();
            zipbob2.setHost(em.find(Host.class, "KimSeHyeon"));
            zipbob2.setPuppy(em.find(Puppy.class, "MinJaeHong"));
            zipbob2.setTime(LocalDateTime.of(2024,7,20,18,00));
            zipbob2.setStatus(ZipbobStatus.MATCHED);
            zipbob2.setMenu("제육볶음");

            em.persist(zipbob2);

            Zipbob zipbob3 = new Zipbob();
            zipbob3.setHost(em.find(Host.class, "ChoSangJun"));
            zipbob3.setPuppy(em.find(Puppy.class, "KimJiWon"));
            zipbob3.setTime(LocalDateTime.of(2024,7,20,18,20));
            zipbob3.setStatus(ZipbobStatus.MATCHED);
            zipbob3.setMenu("버터오징어");

            em.persist(zipbob3);

            Zipbob zipbob4 = new Zipbob();
            zipbob4.setHost(em.find(Host.class, "LimWooJin"));
            zipbob4.setTime(LocalDateTime.of(2024,7,18,17,45));
            zipbob4.setStatus(ZipbobStatus.READY);
            zipbob4.setMenu("순두부찌개");

            em.persist(zipbob4);
        }

        @Transactional
        public void initFavoriteHost(){
            FavoriteHost favoriteHost1 = new FavoriteHost();
            favoriteHost1.setHost(em.find(Host.class,"KwonOhSung"));
            favoriteHost1.setPuppy(em.find(Puppy.class, "MinJaeHong"));

            FavoriteHost favoriteHost2 = new FavoriteHost();
            favoriteHost2.setHost(em.find(Host.class,"KimSeHyeon"));
            favoriteHost2.setPuppy(em.find(Puppy.class, "MinJaeHong"));

            FavoriteHost favoriteHost3 = new FavoriteHost();
            favoriteHost3.setHost(em.find(Host.class,"ChoSangJun"));
            favoriteHost3.setPuppy(em.find(Puppy.class, "KimJiWon"));

            FavoriteHost favoriteHost4 = new FavoriteHost();
            favoriteHost4.setHost(em.find(Host.class,"LimWooJin"));
            favoriteHost4.setPuppy(em.find(Puppy.class, "SeoSangHyeok"));

            em.persist(favoriteHost1);
            em.persist(favoriteHost2);
            em.persist(favoriteHost3);
            em.persist(favoriteHost4);
        }
    }
}
