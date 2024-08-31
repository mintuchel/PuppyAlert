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
import seominkim.puppyAlert.domain.menu.entity.Menu;
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
        initDomainService.initMenu();
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
            host1.setId("KwonOhSung");
            host1.setName("권오성");
            host1.setPassword("a12345678!");
            host1.setNickName("호날두");
            host1.setBirth(LocalDate.now());
            // 손수제치킨
            host1.setAddress("군자동 150-14");
            host1.setDetailAddress("엔샵빌라 16동 209호");
            host1.setLocation(new Location(37.55379265523353, 127.07382983231048));
            host1.setPhoneNumber("010-1582-3145");

            Host host2 = new Host();
            host2.setId("KimSeHyun");
            host2.setName("김세현");
            host2.setNickName("메시");
            host2.setPassword("a12345678!");
            host2.setBirth(LocalDate.now());
            // 찬스불고기 부리또
            host2.setAddress("군자동 363-13");
            host2.setDetailAddress("한마음빌라 201동 103호");
            host2.setLocation(new Location(37.55007773494337, 127.07106493659647));
            host2.setPhoneNumber("010-3244-7988");

            Host host3 = new Host();
            host3.setId("ChoSangJun");
            host3.setName("조상준");
            host3.setNickName("우원재");
            host3.setPassword("a12345678!");
            host3.setBirth(LocalDate.now());
            // 스타벅스 어린이대공원점
            host3.setAddress("군자동 236");
            host3.setDetailAddress("금성빌라 302호");
            host3.setLocation(new Location(37.551907956849995, 127.07636576512374));
            host3.setPhoneNumber("010-5814-6568");

            Host host4 = new Host();
            host4.setId("LimWooJin");
            host4.setName("임우진");
            host4.setPassword("a12345678!");
            host4.setNickName("킬리안음바페요");
            host4.setBirth(LocalDate.now());
            // 아람 보리밥국수
            host4.setAddress("화양동 18-21");
            host4.setDetailAddress("카카오텔 405호");
            host4.setLocation(new Location(37.5475940875437, 127.06962825715127));
            host4.setPhoneNumber("010-4198-1241");

            Host host5 = new Host();
            host5.setId("KimHyunA");
            host5.setName("김현아");
            host5.setPassword("a12345678!");
            host5.setNickName("팔로얄토");
            host5.setBirth(LocalDate.now());
            // 쥬씨
            host5.setAddress("화양동 495-10");
            host5.setDetailAddress("영앤리치빌라 506호");
            host5.setLocation(new Location(37.54593669782253, 127.07619842193493));
            host5.setPhoneNumber("010-8731-1245");

            Host host6 = new Host();
            host6.setId("ParkDaeWon");
            host6.setName("박대원");
            host6.setPassword("a12345678!");
            host6.setNickName("슈퍼비");
            host6.setBirth(LocalDate.now());
            // 진한방삼계탕
            host6.setAddress("군자동 230");
            host6.setDetailAddress("현대빌라 1203호");
            host6.setLocation(new Location(37.55262356990496, 127.07741047989005));
            host6.setPhoneNumber("010-1211-1000");

            Host host7 = new Host();
            host7.setId("ChoHyungJun");
            host7.setName("조형준");
            host7.setPassword("a12345678!");
            host7.setNickName("키드밀리");
            host7.setBirth(LocalDate.now());
            // 옛날종로빈대떡
            host7.setAddress("화양동 217");
            host7.setDetailAddress("화양주택 2동 101호");
            host7.setLocation(new Location(37.547096349517936, 127.07324610787151));
            host7.setPhoneNumber("010-6587-8014");

            Host host8 = new Host();
            host8.setId("ShinJiHun");
            host8.setName("신지훈");
            host8.setPassword("a12345678!");
            host8.setNickName("오왼 오바도즈");
            host8.setBirth(LocalDate.now());
            // 개카페
            host8.setAddress("화양동 131-3");
            host8.setDetailAddress("저스트뮤직빌라 402호");
            host8.setLocation(new Location(37.54588160784895, 127.0706083175839));
            host8.setPhoneNumber("010-3265-0541");

            em.persist(host1);
            em.persist(host2);
            em.persist(host3);
            em.persist(host4);
            em.persist(host5);
            em.persist(host6);
            em.persist(host7);
            em.persist(host8);
        }

        @Transactional
        public void initPuppy() {
            Puppy puppy1 = new Puppy();
            puppy1.setId("SeoSangHyeok");
            puppy1.setName("서상혁");
            puppy1.setPassword("a12345678!");
            puppy1.setNickName("후식없으면안감요");
            puppy1.setBirth(LocalDate.now());
            // 현대아파트
            puppy1.setAddress("군자동 100-2");
            puppy1.setDetailAddress("현대아파트 103동 801호");
            puppy1.setLocation(new Location(37.55191738446834, 127.0721191456786));
            puppy1.setPhoneNumber("010-1111-2222");

            Puppy puppy2 = new Puppy();
            puppy2.setId("MinJaeHong");
            puppy2.setName("민재홍");
            puppy2.setPassword("a12345678!");
            puppy2.setNickName("제육헌터");
            puppy2.setBirth(LocalDate.now());
            // 세종대학교 학생회관
            puppy2.setAddress("군자동 98");
            puppy2.setDetailAddress("세종대학교 학생회관 530호");
            puppy2.setLocation(new Location(37.549611195346664, 127.07514407425086));
            puppy2.setPhoneNumber("010-6457-9554");

            Puppy puppy3 = new Puppy();
            puppy3.setId("KimJiWon");
            puppy3.setName("김지원");
            puppy3.setPassword("a12345678!");
            puppy3.setNickName("니가가라하와이");
            puppy3.setBirth(LocalDate.now());
            // 화양타워아파트
            puppy3.setAddress("화양동 110-37");
            puppy3.setDetailAddress("화양타워 103동 204호");
            puppy3.setLocation(new Location(37.547023288665116, 127.07116671038504));
            puppy3.setPhoneNumber("010-8221-7458");

            em.persist(puppy1);
            em.persist(puppy2);
            em.persist(puppy3);
        }

        @Transactional
        public void initMenu(){
            Menu menu1 = new Menu();
            menu1.setMenuName("까르보나라");
            menu1.setImageURL("https://blog.kakaocdn.net/dn/cDOn0y/btruVLrLi88/aXkUtkaXRvdWVqaJv89Jn0/img.jpg");

            Menu menu2 = new Menu();
            menu2.setMenuName("제육볶음");
            menu2.setImageURL("https://godomall.speedycdn.net/0f1081f2872a5b54f665bd623a5689cb/goods/1159/image/detail/1159_detail_097.jpg");

            Menu menu3 = new Menu();
            menu3.setMenuName("삼계탕");
            menu3.setImageURL("https://sitem.ssgcdn.com/43/44/21/item/1000412214443_i1_750.jpg");

            Menu menu4 = new Menu();
            menu4.setMenuName("순두부찌개");
            menu4.setImageURL("https://honestkfood.co.kr/wp-content/uploads/2014/08/16.%EC%88%9C%EB%91%90%EB%B6%80%EC%B0%8C%EA%B0%9C1.png");

            Menu menu5 = new Menu();
            menu5.setMenuName("얼큰한 해물라면");
            menu5.setImageURL("https://sitem.ssgcdn.com/53/50/93/item/1000018935053_i2_750.jpg");

            Menu menu6 = new Menu();
            menu6.setMenuName("우거지 해장국");
            menu6.setImageURL("https://gdimg1.gmarket.co.kr/goods_image2/shop_moreimg/213/691/2136917571/2136917571_01.jpg?ver=1682053775");

            Menu menu7 = new Menu();
            menu7.setMenuName("통등심 돈까스");
            menu7.setImageURL("https://cdn.oasis.co.kr:48581/product/81639/thumb/3dc6cd2f-09cd-47aa-a425-3772887d03bf.jpg");

            Menu menu8 = new Menu();
            menu8.setMenuName("녹두전");
            menu8.setImageURL("https://thenaum.cdn-nhncommerce.com/data/goods/20/07/31/1000005027/1000005027_detail_093.jpg");

            Menu menu9 = new Menu();
            menu9.setMenuName("불고기고구마 피자");
            menu9.setImageURL("https://cdn.dominos.co.kr/admin/upload/goods/20230619_F33836Pn.jpg");

            Menu menu10 = new Menu();
            menu10.setMenuName("순대국");
            menu10.setImageURL("https://d12zq4w4guyljn.cloudfront.net/750_750_20210603124244841_photo_41af3676fc32.jpg");

            em.persist(menu1);
            em.persist(menu2);
            em.persist(menu3);
            em.persist(menu4);
            em.persist(menu5);
            em.persist(menu6);
            em.persist(menu7);
            em.persist(menu8);
            em.persist(menu9);
            em.persist(menu10);
        }

        @Transactional
        public void initFood(){
            // 서상혁-권오성
            Food food1 = new Food();
            food1.setHost(em.find(Host.class,"KwonOhSung"));
            food1.setPuppy(em.find(Puppy.class, "SeoSangHyeok"));
            food1.setMenu(em.find(Menu.class, "까르보나라"));
            food1.setTime(LocalDateTime.of(2024,8,1,14,30));
            food1.setStatus(FoodStatus.MATCHED);

            // 민재홍-김세현
            Food food2 = new Food();
            food2.setHost(em.find(Host.class, "KimSeHyun"));
            food2.setPuppy(em.find(Puppy.class, "MinJaeHong"));
            food2.setMenu(em.find(Menu.class, "제육볶음"));
            food2.setTime(LocalDateTime.of(2024,8,1,18,00));
            food2.setStatus(FoodStatus.MATCHED);

            // 김지원-조상준
            Food food3 = new Food();
            food3.setHost(em.find(Host.class, "ChoSangJun"));
            food3.setPuppy(em.find(Puppy.class, "KimJiWon"));
            food3.setMenu(em.find(Menu.class, "삼계탕"));
            food3.setTime(LocalDateTime.of(2024,8,1,9,20));
            food3.setStatus(FoodStatus.MATCHED);

            // 김지원-임우진
            Food food4 = new Food();
            food4.setHost(em.find(Host.class, "LimWooJin"));
            food4.setPuppy(em.find(Puppy.class, "KimJiWon"));
            food4.setMenu(em.find(Menu.class, "순두부찌개"));
            food4.setTime(LocalDateTime.of(2024,8,1,17,45));
            food4.setStatus(FoodStatus.MATCHED);

            // 서상혁-김현아
            Food food5 = new Food();
            food5.setHost(em.find(Host.class, "KimHyunA"));
            food5.setPuppy(em.find(Puppy.class, "SeoSangHyeok"));
            food5.setMenu(em.find(Menu.class, "얼큰한 해물라면"));
            food5.setTime(LocalDateTime.of(2024,8,1,18,00));
            food5.setStatus(FoodStatus.MATCHED);

            // 민재홍-박대원
            Food food6 = new Food();
            food6.setHost(em.find(Host.class, "ParkDaeWon"));
            food6.setPuppy(em.find(Puppy.class, "MinJaeHong"));
            food6.setMenu(em.find(Menu.class, "우거지 해장국"));
            food6.setTime(LocalDateTime.of(2024,8,1,19,00));
            food6.setStatus(FoodStatus.MATCHED);

            // 아직 매칭안된 박대원 집밥
            Food food7 = new Food();
            food7.setHost(em.find(Host.class, "ParkDaeWon"));
            food7.setMenu(em.find(Menu.class, "통등심 돈까스"));
            food7.setTime(LocalDateTime.of(2024,8,1,10,20));
            food7.setStatus(FoodStatus.READY);

            // 아직 매칭안된 조형준 집밥
            Food food8 = new Food();
            food8.setHost(em.find(Host.class, "ChoHyungJun"));
            food8.setMenu(em.find(Menu.class, "녹두전"));
            food8.setTime(LocalDateTime.of(2024,8,1,15,35));
            food8.setStatus(FoodStatus.READY);

            // 아직 매칭안된 신지훈 집밥
            Food food9 = new Food();
            food9.setHost(em.find(Host.class, "ShinJiHun"));
            food9.setMenu(em.find(Menu.class, "불고기고구마 피자"));
            food9.setTime(LocalDateTime.of(2024,8,1,21,00));
            food9.setStatus(FoodStatus.READY);

            em.persist(food1);
            em.persist(food2);
            em.persist(food3);
            em.persist(food4);
            em.persist(food5);
            em.persist(food6);
            em.persist(food7);
            em.persist(food8);
            em.persist(food9);
        }

        @Transactional
        public void initFavoriteHost(){
            // 서상혁
            FavoriteHost favoriteHost1 = new FavoriteHost();
            favoriteHost1.setHost(em.find(Host.class,"KwonOhSung"));
            favoriteHost1.setPuppy(em.find(Puppy.class, "SeoSangHyeok"));

            FavoriteHost favoriteHost4 = new FavoriteHost();
            favoriteHost4.setHost(em.find(Host.class,"KimHyunA"));
            favoriteHost4.setPuppy(em.find(Puppy.class, "SeoSangHyeok"));

            FavoriteHost favoriteHost8 = new FavoriteHost();
            favoriteHost8.setHost(em.find(Host.class, "ChoSangJun"));
            favoriteHost8.setPuppy(em.find(Puppy.class, "SeoSangHyeok"));

            FavoriteHost favoriteHost9 = new FavoriteHost();
            favoriteHost9.setHost(em.find(Host.class, "ChoHyungJun"));
            favoriteHost9.setPuppy(em.find(Puppy.class, "SeoSangHyeok"));

            // 민재홍
            FavoriteHost favoriteHost2 = new FavoriteHost();
            favoriteHost2.setHost(em.find(Host.class,"KimSeHyun"));
            favoriteHost2.setPuppy(em.find(Puppy.class, "MinJaeHong"));

            FavoriteHost favoriteHost7 = new FavoriteHost();
            favoriteHost7.setHost(em.find(Host.class,"ParkDaeWon"));
            favoriteHost7.setPuppy(em.find(Puppy.class, "MinJaeHong"));

            FavoriteHost favoriteHost5 = new FavoriteHost();
            favoriteHost5.setHost(em.find(Host.class,"ShinJiHun"));
            favoriteHost5.setPuppy(em.find(Puppy.class, "MinJaeHong"));

            // 김지원
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
            em.persist(favoriteHost8);
            em.persist(favoriteHost9);
        }
    }
}
