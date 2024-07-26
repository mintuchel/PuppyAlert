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
            host1.setPassword("a12345678!");
            host1.setNickName("호날두");
            host1.setBirth(LocalDate.now());
            host1.setAddress("서울특별시 광진구 군자동 군자로 129");
            host1.setDetailAddress("손수제치킨 본점");
            // 손수제치킨
            host1.setLocation(new Location(37.55379265523353, 127.07382983231048));
            host1.setPhoneNumber("010-1582-3145");
            em.persist(host1);

            Host host2 = new Host();
            host2.setHostId("KimSeHyun");
            host2.setName("김세현");
            host2.setNickName("메시");
            host2.setPassword("a12345678!");
            host2.setBirth(LocalDate.now());
            host2.setAddress("서울특별시 광진구 군자동 군자로 76");
            host2.setDetailAddress("찬스불고기 부리또 군자점");
            // 찬스불고기 부리또
            host2.setLocation(new Location(37.55007773494337, 127.07106493659647));
            host2.setPhoneNumber("010-3244-7988");
            em.persist(host2);

            Host host3 = new Host();
            host3.setHostId("ChoSangJun");
            host3.setName("조상준");
            host3.setNickName("우원재");
            host3.setPassword("a12345678!");
            host3.setBirth(LocalDate.now());
            host3.setAddress("서울시 광진구 군자동 능동로 243");
            host3.setDetailAddress("스타벅스 어린이대공원점");
            // 스타벅스 어린이대공원점
            host3.setLocation(new Location(37.551907956849995, 127.07636576512374));
            host3.setPhoneNumber("010-5814-6568");
            em.persist(host3);

            Host host4 = new Host();
            host4.setHostId("LimWooJin");
            host4.setName("임우진");
            host4.setPassword("a12345678!");
            host4.setNickName("킬리안음바페요");
            host4.setBirth(LocalDate.now());
            host4.setAddress("서울특별시 광진구 군자동 동일로30길 41");
            host4.setDetailAddress("아람 보리밥국수");
            // 아람 보리밥국수
            host4.setLocation(new Location(37.5475940875437, 127.06962825715127));
            host4.setPhoneNumber("010-4198-1241");
            em.persist(host4);

            Host host5 = new Host();
            host5.setHostId("KimHyunA");
            host5.setName("김현아");
            host5.setPassword("a12345678!");
            host5.setNickName("팔로얄토");
            host5.setBirth(LocalDate.now());
            host5.setAddress("서울특별시 광진구 광나루로 418");
            host5.setDetailAddress("가츠시");
            // 가츠시
            host5.setLocation(new Location(37.546394232959564, 127.07576605200492));
            host5.setPhoneNumber("010-8731-1245");
            em.persist(host5);

            Host host6 = new Host();
            host6.setHostId("ParkDaeWon");
            host6.setName("박대원");
            host6.setPassword("a12345678!");
            host6.setNickName("슈퍼비");
            host6.setBirth(LocalDate.now());
            host6.setAddress("서울특별시 광진구 군자동 능동로 289");
            host6.setDetailAddress("메가박스 군자점");
            // 메가박스 군자점
            host6.setLocation(new Location(37.55566382462311, 127.07833881933574));
            host6.setPhoneNumber("010-1211-1000");
            em.persist(host6);
        }

        @Transactional
        public void initPuppy() {
            Puppy puppy1 = new Puppy();
            puppy1.setPuppyId("SeoSangHyeok");
            puppy1.setName("서상혁");
            puppy1.setPassword("a12345678!");
            puppy1.setNickName("후식없으면안감요");
            puppy1.setBirth(LocalDate.now());
            puppy1.setAddress("어린이대공원");
            puppy1.setDetailAddress("생태연못");
            puppy1.setLocation(new Location(37.54810878705189, 127.07859401423333));
            puppy1.setPhoneNumber("010-1111-2222");

            em.persist(puppy1);

            Puppy puppy2 = new Puppy();
            puppy2.setPuppyId("MinJaeHong");
            puppy2.setName("민재홍");
            puppy2.setPassword("a12345678!");
            puppy2.setNickName("제육헌터");
            puppy2.setBirth(LocalDate.now());
            puppy2.setAddress("세종대학교");
            puppy2.setDetailAddress("학생회관 530호");
            puppy2.setLocation(new Location(37.549611195346664, 127.07514407425086));
            puppy2.setPhoneNumber("010-6457-9554");

            em.persist(puppy2);

            Puppy puppy3 = new Puppy();
            puppy3.setPuppyId("KimJiWon");
            puppy3.setName("김지원");
            puppy3.setPassword("a12345678!");
            puppy3.setNickName("니가가라하와이");
            puppy3.setBirth(LocalDate.now());
            puppy3.setAddress("서울장안초등학교");
            puppy3.setDetailAddress("운동장");
            puppy3.setLocation(new Location(37.55003674505109, 127.07180047118544));
            puppy3.setPhoneNumber("010-8221-7458");

            em.persist(puppy3);
        }

        @Transactional
        public void initFood(){
            // 서상혁-권오성
            Food food1 = new Food();
            food1.setHost(em.find(Host.class,"KwonOhSung"));
            food1.setPuppy(em.find(Puppy.class, "SeoSangHyeok"));
            food1.setTime(LocalDateTime.of(2024,7,19,14,30));
            food1.setStatus(FoodStatus.MATCHED);
            food1.setMenu("까르보나라");
            food1.setImageURL("https://blog.kakaocdn.net/dn/cDOn0y/btruVLrLi88/aXkUtkaXRvdWVqaJv89Jn0/img.jpg");

            em.persist(food1);

            // 민재홍-김세현
            Food food2 = new Food();
            food2.setHost(em.find(Host.class, "KimSeHyun"));
            food2.setPuppy(em.find(Puppy.class, "MinJaeHong"));
            food2.setTime(LocalDateTime.of(2024,7,20,18,00));
            food2.setStatus(FoodStatus.MATCHED);
            food2.setMenu("제육볶음");
            food2.setImageURL("https://godomall.speedycdn.net/0f1081f2872a5b54f665bd623a5689cb/goods/1159/image/detail/1159_detail_097.jpg");

            em.persist(food2);

            // 김지원-조상준
            Food food3 = new Food();
            food3.setHost(em.find(Host.class, "ChoSangJun"));
            food3.setPuppy(em.find(Puppy.class, "KimJiWon"));
            food3.setTime(LocalDateTime.of(2024,7,21,15,20));
            food3.setStatus(FoodStatus.MATCHED);
            food3.setMenu("버터오징어");
            food3.setImageURL("https://sitem.ssgcdn.com/97/28/68/item/1000541682897_i4_750.jpg");

            em.persist(food3);

            // 김지원-임우진
            Food food4 = new Food();
            food4.setHost(em.find(Host.class, "LimWooJin"));
            food4.setPuppy(em.find(Puppy.class, "KimJiWon"));
            food4.setTime(LocalDateTime.of(2024,7,12,17,45));
            food4.setStatus(FoodStatus.MATCHED);
            food4.setMenu("순두부찌개");
            food4.setImageURL("https://honestkfood.co.kr/wp-content/uploads/2014/08/16.%EC%88%9C%EB%91%90%EB%B6%80%EC%B0%8C%EA%B0%9C1.png");

            em.persist(food4);

            // 서상혁-김현아
            Food food5 = new Food();
            food5.setHost(em.find(Host.class, "KimHyunA"));
            food5.setPuppy(em.find(Puppy.class, "SeoSangHyeok"));
            food5.setTime(LocalDateTime.of(2024,7,23,18,00));
            food5.setStatus(FoodStatus.MATCHED);
            food5.setMenu("맛있는 라면!");
            food5.setImageURL("https://sitem.ssgcdn.com/53/50/93/item/1000018935053_i2_750.jpg");

            em.persist(food5);

            // 민재홍-박대원
            Food food6 = new Food();
            food6.setHost(em.find(Host.class, "ParkDaeWon"));
            food6.setPuppy(em.find(Puppy.class, "MinJaeHong"));
            food6.setTime(LocalDateTime.of(2024,7,24,19,00));
            food6.setStatus(FoodStatus.MATCHED);
            food6.setMenu("우거지 해장국");
            food6.setImageURL("https://gdimg1.gmarket.co.kr/goods_image2/shop_moreimg/213/691/2136917571/2136917571_01.jpg?ver=1682053775");

            em.persist(food6);

            // 아직 매칭안된 박대원 집밥
            Food food7 = new Food();
            food7.setHost(em.find(Host.class, "ParkDaeWon"));
            food7.setTime(LocalDateTime.of(2024,7,25,18,00));
            food7.setStatus(FoodStatus.READY);
            food7.setMenu("카라멜팝콘");
            food7.setImageURL("https://media.bunjang.co.kr/product/255156439_1_1709252513_w360.jpg");

            em.persist(food7);
        }

        @Transactional
        public void initFavoriteHost(){
            FavoriteHost favoriteHost1 = new FavoriteHost();
            favoriteHost1.setHost(em.find(Host.class,"KwonOhSung"));
            favoriteHost1.setPuppy(em.find(Puppy.class, "SeoSangHyeok"));

            FavoriteHost favoriteHost4 = new FavoriteHost();
            favoriteHost4.setHost(em.find(Host.class,"KimHyunA"));
            favoriteHost4.setPuppy(em.find(Puppy.class, "SeoSangHyeok"));

            FavoriteHost favoriteHost2 = new FavoriteHost();
            favoriteHost2.setHost(em.find(Host.class,"KimSeHyun"));
            favoriteHost2.setPuppy(em.find(Puppy.class, "MinJaeHong"));

            FavoriteHost favoriteHost7 = new FavoriteHost();
            favoriteHost7.setHost(em.find(Host.class,"ParkDaeWon"));
            favoriteHost7.setPuppy(em.find(Puppy.class, "MinJaeHong"));

            FavoriteHost favoriteHost5 = new FavoriteHost();
            favoriteHost5.setHost(em.find(Host.class,"ChoSangJun"));
            favoriteHost5.setPuppy(em.find(Puppy.class, "MinJaeHong"));

            FavoriteHost favoriteHost3 = new FavoriteHost();
            favoriteHost3.setHost(em.find(Host.class,"ChoSangJun"));
            favoriteHost3.setPuppy(em.find(Puppy.class, "KimJiWon"));

            FavoriteHost favoriteHost6 = new FavoriteHost();
            favoriteHost6.setHost(em.find(Host.class,"LimWooJin"));
            favoriteHost6.setPuppy(em.find(Puppy.class, "KimJiWon"));

            em.persist(favoriteHost1);
            em.persist(favoriteHost2);
            em.persist(favoriteHost3);
            em.persist(favoriteHost4);
            em.persist(favoriteHost5);
            em.persist(favoriteHost6);
            em.persist(favoriteHost7);
        }
    }
}
