package seominkim.puppyAlert.domain;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.food.entity.FoodStatus;
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
        initDomainService.initFood();
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
            host1.setNickName("호날두");
            host1.setBirth(LocalDate.now());
            host1.setAddress("제주도");
            host1.setLocation(new Location(37.551833438845506, 127.0753138390134));
            host1.setPhoneNumber("010-1582-3145");
            em.persist(host1);

            Host host2 = new Host();
            host2.setHostId("kimSeHyeon");
            host2.setName("김세현");
            host2.setNickName("메시");
            host2.setPassword("abcd");
            host2.setBirth(LocalDate.now());
            host2.setAddress("서울");
            host2.setLocation(new Location(37.54965636279012, 127.0750237101941));
            host2.setPhoneNumber("010-3244-7988");
            em.persist(host2);

            Host host3 = new Host();
            host3.setHostId("ChoSangJun");
            host3.setName("조상준");
            host3.setNickName("발베르데");
            host3.setPassword("abcdefg");
            host3.setBirth(LocalDate.now());
            host3.setAddress("부산");
            host3.setLocation(new Location(37.551833438845506, 127.0753138390134));
            host3.setPhoneNumber("010-5814-6568");
            em.persist(host3);

            Host host4 = new Host();
            host4.setHostId("LimWooJin");
            host4.setName("임우진");
            host4.setPassword("xyzw");
            host4.setNickName("음바페");
            host4.setBirth(LocalDate.now());
            host4.setAddress("대구");
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
            puppy1.setNickName("아자르");
            puppy1.setBirth(LocalDate.now());
            puppy1.setAddress("광주");
            puppy1.setLocation(new Location(37.8123, 124.9854));
            puppy1.setPhoneNumber("010-1111-2222");

            em.persist(puppy1);

            Puppy puppy2 = new Puppy();
            puppy2.setPuppyId("MinJaeHong");
            puppy2.setName("민재홍");
            puppy2.setPassword("gkqpfmcm29");
            puppy2.setNickName("뤼디거");
            puppy2.setBirth(LocalDate.now());
            puppy2.setAddress("서울");
            puppy2.setLocation(new Location(37.59999, 125.45));
            puppy2.setPhoneNumber("010-6457-9554");

            em.persist(puppy2);

            Puppy puppy3 = new Puppy();
            puppy3.setPuppyId("KimJiWon");
            puppy3.setName("김지원");
            puppy3.setPassword("dmaqkpv9");
            puppy3.setNickName("카르바할");
            puppy3.setBirth(LocalDate.now());
            puppy3.setAddress("인천");
            puppy3.setLocation(new Location(37.6569, 125.321));
            puppy3.setPhoneNumber("010-8221-7458");

            em.persist(puppy3);
        }

        @Transactional
        public void initFood(){
            Food food1 = new Food();
            food1.setHost(em.find(Host.class,"KwonOhSung"));
            food1.setPuppy(em.find(Puppy.class, "SeoSangHyeok"));
            food1.setTime(LocalDateTime.of(2024,7,19,14,30));
            food1.setStatus(FoodStatus.MATCHED);
            food1.setMenu("크림파스타");

            em.persist(food1);

            Food food2 = new Food();
            food2.setHost(em.find(Host.class, "KimSeHyeon"));
            food2.setPuppy(em.find(Puppy.class, "MinJaeHong"));
            food2.setTime(LocalDateTime.of(2024,7,20,18,00));
            food2.setStatus(FoodStatus.MATCHED);
            food2.setMenu("제육볶음");

            em.persist(food2);

            Food food3 = new Food();
            food3.setHost(em.find(Host.class, "ChoSangJun"));
            food3.setPuppy(em.find(Puppy.class, "KimJiWon"));
            food3.setTime(LocalDateTime.of(2024,7,20,18,20));
            food3.setStatus(FoodStatus.MATCHED);
            food3.setMenu("버터오징어");

            em.persist(food3);

            Food food4 = new Food();
            food4.setHost(em.find(Host.class, "LimWooJin"));
            food4.setTime(LocalDateTime.of(2024,7,18,17,45));
            food4.setStatus(FoodStatus.READY);
            food4.setMenu("순두부찌개");

            em.persist(food4);
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
