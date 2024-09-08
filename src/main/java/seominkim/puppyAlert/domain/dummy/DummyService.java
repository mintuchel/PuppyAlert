package seominkim.puppyAlert.domain.dummy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.market.entity.Market;
import seominkim.puppyAlert.domain.menu.entity.Menu;
import seominkim.puppyAlert.domain.food.entity.FoodStatus;
import seominkim.puppyAlert.domain.shop.entity.ProductType;
import seominkim.puppyAlert.domain.shop.entity.Shop;
import seominkim.puppyAlert.domain.user.entity.User;
import seominkim.puppyAlert.global.entity.Location;
import seominkim.puppyAlert.global.entity.UserType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
class DummyService {
    @PersistenceContext
    private final EntityManager em;

    @Transactional
    public void initHost() {
        User host1 = new User();
        host1.setId("KwonOhSung");
        host1.setName("권오성");
        host1.setPassword("a12345678!");
        host1.setNickName("한입당호우");
        host1.setBirth(LocalDate.now());
        // 손수제치킨
        host1.setAddress("군자동 150-14");
        host1.setDetailAddress("엔샵빌라 16동 209호");
        host1.setLocation(new Location(37.55379265523353, 127.07382983231048));
        host1.setPhoneNumber("010-1582-3145");
        host1.setProfileImageURL("https://avatars.githubusercontent.com/u/79124461?v=4");
        host1.setUserType(UserType.HOST);

        User host2 = new User();
        host2.setId("KimSeHyun");
        host2.setName("김세현");
        host2.setNickName("맑은햇살");
        host2.setPassword("a12345678!");
        host2.setBirth(LocalDate.now());
        // 찬스불고기 부리또
        host2.setAddress("군자동 363-13");
        host2.setDetailAddress("한마음빌라 201동 103호");
        host2.setLocation(new Location(37.55007773494337, 127.07106493659647));
        host2.setPhoneNumber("010-3244-7988");
        host2.setProfileImageURL("https://avatars.githubusercontent.com/u/102282703?v=4");
        host2.setUserType(UserType.HOST);

        User host3 = new User();
        host3.setId("ChoSangJun");
        host3.setName("조상준");
        host3.setNickName("군자동음바페");
        host3.setPassword("a12345678!");
        host3.setBirth(LocalDate.now());
        // 스타벅스 어린이대공원점
        host3.setAddress("군자동 236");
        host3.setDetailAddress("금성빌라 302호");
        host3.setLocation(new Location(37.551907956849995, 127.07636576512374));
        host3.setPhoneNumber("010-5814-6568");
        host3.setProfileImageURL("https://avatars.githubusercontent.com/u/43038815?v=4");
        host3.setUserType(UserType.HOST);

        User host4 = new User();
        host4.setId("LimWooJin");
        host4.setName("임우진");
        host4.setPassword("a12345678!");
        host4.setNickName("화양동백종원");
        host4.setBirth(LocalDate.now());
        // 아람 보리밥국수
        host4.setAddress("화양동 18-21");
        host4.setDetailAddress("카카오텔 405호");
        host4.setLocation(new Location(37.5475940875437, 127.06962825715127));
        host4.setPhoneNumber("010-4198-1241");
        host4.setProfileImageURL("https://avatars.githubusercontent.com/u/25877253?v=4");
        host4.setUserType(UserType.HOST);

        User host5 = new User();
        host5.setId("KimHyunA");
        host5.setName("김현아");
        host5.setPassword("a12345678!");
        host5.setNickName("라면물조절장인");
        host5.setBirth(LocalDate.now());
        // 쥬씨
        host5.setAddress("화양동 495-10");
        host5.setDetailAddress("영앤리치빌라 506호");
        host5.setLocation(new Location(37.54593669782253, 127.07619842193493));
        host5.setPhoneNumber("010-8731-1245");
        host5.setProfileImageURL("https://avatars.githubusercontent.com/u/129165742?v=4");
        host5.setUserType(UserType.HOST);

        User host6 = new User();
        host6.setId("ParkDaeWon");
        host6.setName("박대원");
        host6.setPassword("a12345678!");
        host6.setNickName("호밀밭의요리사");
        host6.setBirth(LocalDate.now());
        // 진한방삼계탕
        host6.setAddress("군자동 230");
        host6.setDetailAddress("현대빌라 1203호");
        host6.setLocation(new Location(37.55262356990496, 127.07741047989005));
        host6.setPhoneNumber("010-1211-1000");
        host6.setProfileImageURL("https://avatars.githubusercontent.com/u/101173462?v=4");
        host6.setUserType(UserType.HOST);

        User host7 = new User();
        host7.setId("ChoHyungJun");
        host7.setName("조형준");
        host7.setPassword("a12345678!");
        host7.setNickName("비도오고그래서");
        host7.setBirth(LocalDate.now());
        // 옛날종로빈대떡
        host7.setAddress("화양동 217");
        host7.setDetailAddress("화양주택 2동 101호");
        host7.setLocation(new Location(37.547096349517936, 127.07324610787151));
        host7.setPhoneNumber("010-6587-8014");
        host7.setProfileImageURL("https://avatars.githubusercontent.com/u/1024025?v=4");
        host7.setUserType(UserType.HOST);

        User host8 = new User();
        host8.setId("ShinJiHun");
        host8.setName("신지훈");
        host8.setPassword("a12345678!");
        host8.setNickName("한없이맑은눈물");
        host8.setBirth(LocalDate.now());
        // 개카페
        host8.setAddress("화양동 131-3");
        host8.setDetailAddress("저스트뮤직빌라 402호");
        host8.setLocation(new Location(37.54588160784895, 127.0706083175839));
        host8.setPhoneNumber("010-3265-0541");
        host8.setProfileImageURL("https://avatars.githubusercontent.com/u/82876698?v=4");
        host8.setUserType(UserType.HOST);

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
        User puppy1 = new User();
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
        puppy1.setProfileImageURL("https://avatars.githubusercontent.com/u/126949574?v=4");
        puppy1.setUserType(UserType.PUPPY);

        User puppy2 = new User();
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
        puppy2.setProfileImageURL("https://avatars.githubusercontent.com/u/103743166?v=4");
        puppy2.setUserType(UserType.PUPPY);

        User puppy3 = new User();
        puppy3.setId("KimJiWon");
        puppy3.setName("김지원");
        puppy3.setPassword("a12345678!");
        puppy3.setNickName("민초러버");
        puppy3.setBirth(LocalDate.now());
        // 화양타워아파트
        puppy3.setAddress("화양동 110-37");
        puppy3.setDetailAddress("화양타워 103동 204호");
        puppy3.setLocation(new Location(37.547023288665116, 127.07116671038504));
        puppy3.setPhoneNumber("010-8221-7458");
        puppy3.setProfileImageURL("https://avatars.githubusercontent.com/u/15929412?v=4");
        puppy3.setUserType(UserType.PUPPY);

        em.persist(puppy1);
        em.persist(puppy2);
        em.persist(puppy3);
    }

    @Transactional
    public void initMenu() {
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
        menu10.setMenuName("스팸김치찌개");
        menu10.setImageURL("https://blog.kakaocdn.net/dn/Uouhp/btrAcaBjdTN/68U9LEuWwkZPpNIKtSYdl0/img.jpg");

        Menu menu11 = new Menu();
        menu11.setMenuName("회덮밥");
        menu11.setImageURL("https://static.wtable.co.kr/image/production/service/recipe/33/8f5c0ee2-35fe-4377-a065-07203f8cc7f7.jpg?size=800x800");

        Menu menu12 = new Menu();
        menu12.setMenuName("순대국");
        menu12.setImageURL("https://d12zq4w4guyljn.cloudfront.net/750_750_20210603124244841_photo_41af3676fc32.jpg");

        Menu menu13 = new Menu();
        menu13.setMenuName("진라면");
        menu13.setImageURL("https://powershop171020.cafe24.com/image/other_img/goods_1081071001001000/20180222_160259.png");

        Menu menu14 = new Menu();
        menu14.setMenuName("알리오올리오 파스타");
        menu14.setImageURL("https://static.wtable.co.kr/image/production/service/recipe/628/e4c99ef8-3207-4451-a0b2-cac2c9249b23.jpg");

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
        em.persist(menu11);
        em.persist(menu12);
        em.persist(menu13);
        em.persist(menu14);
    }

    @Transactional
    public void initFood() {
        // 서상혁-권오성
        Food food1 = new Food();
        food1.setHost(em.find(User.class, "KwonOhSung"));
        food1.setPuppy(em.find(User.class, "SeoSangHyeok"));
        food1.setMenu(em.find(Menu.class, "까르보나라"));
        food1.setTime(LocalDateTime.of(2024, 8, 14, 14, 30));
        food1.setStatus(FoodStatus.MATCHED);

        // 서상혁-김현아
        Food food5 = new Food();
        food5.setHost(em.find(User.class, "KimHyunA"));
        food5.setPuppy(em.find(User.class, "SeoSangHyeok"));
        food5.setMenu(em.find(Menu.class, "얼큰한 해물라면"));
        food5.setTime(LocalDateTime.of(2024, 8, 23, 18, 00));
        food5.setStatus(FoodStatus.MATCHED);

        // 서상혁-박대원 오늘의 집밥
        Food food7 = new Food();
        food7.setHost(em.find(User.class, "ParkDaeWon"));
        food7.setPuppy(em.find(User.class, "SeoSangHyeok"));
        food7.setMenu(em.find(Menu.class, "통등심 돈까스"));
        food7.setTime(LocalDateTime.of(2024, 9, 8, 10, 20));
        food7.setStatus(FoodStatus.MATCHED);

        // 민재홍-김세현
        Food food2 = new Food();
        food2.setHost(em.find(User.class, "KimSeHyun"));
        food2.setPuppy(em.find(User.class, "MinJaeHong"));
        food2.setMenu(em.find(Menu.class, "제육볶음"));
        food2.setTime(LocalDateTime.of(2024, 8, 5, 18, 00));
        food2.setStatus(FoodStatus.MATCHED);

        // 민재홍-박대원
        Food food6 = new Food();
        food6.setHost(em.find(User.class, "ParkDaeWon"));
        food6.setPuppy(em.find(User.class, "MinJaeHong"));
        food6.setMenu(em.find(Menu.class, "우거지 해장국"));
        food6.setTime(LocalDateTime.of(2024, 8, 21, 19, 00));
        food6.setStatus(FoodStatus.MATCHED);

        // 민재홍-조형준 오늘의 집밥
        Food food8 = new Food();
        food8.setHost(em.find(User.class, "ChoHyungJun"));
        food8.setPuppy(em.find(User.class, "MinJaeHong"));
        food8.setMenu(em.find(Menu.class, "녹두전"));
        food8.setTime(LocalDateTime.of(2024, 9, 8, 15, 35));
        food8.setStatus(FoodStatus.MATCHED);

        // 김지원-조상준
        Food food3 = new Food();
        food3.setHost(em.find(User.class, "ChoSangJun"));
        food3.setPuppy(em.find(User.class, "KimJiWon"));
        food3.setMenu(em.find(Menu.class, "삼계탕"));
        food3.setTime(LocalDateTime.of(2024, 8, 16, 11, 20));
        food3.setStatus(FoodStatus.MATCHED);

        // 김지원-임우진
        Food food4 = new Food();
        food4.setHost(em.find(User.class, "LimWooJin"));
        food4.setPuppy(em.find(User.class, "KimJiWon"));
        food4.setMenu(em.find(Menu.class, "순두부찌개"));
        food4.setTime(LocalDateTime.of(2024, 8, 21, 17, 40));
        food4.setStatus(FoodStatus.MATCHED);

        // 김지원-신지훈 집밥 오늘의 집밥
        Food food9 = new Food();
        food9.setHost(em.find(User.class, "ShinJiHun"));
        food9.setPuppy(em.find(User.class, "KimJiWon"));
        food9.setMenu(em.find(Menu.class, "불고기고구마 피자"));
        food9.setTime(LocalDateTime.of(2024, 9, 8, 21, 00));
        food9.setStatus(FoodStatus.MATCHED);

        // 아직 매칭안된 조상준 집밥
        Food food10 = new Food();
        food10.setHost(em.find(User.class, "ChoSangJun"));
        food10.setMenu(em.find(Menu.class, "스팸김치찌개"));
        food10.setTime(LocalDateTime.of(2024, 9, 8, 21, 00));
        food10.setStatus(FoodStatus.READY);

        // 아직 매칭안된 권오성 집밥
        Food food11 = new Food();
        food11.setHost(em.find(User.class, "KwonOhSung"));
        food11.setMenu(em.find(Menu.class, "회덮밥"));
        food11.setTime(LocalDateTime.of(2024, 9, 8, 19, 40));
        food11.setStatus(FoodStatus.READY);

        // 아직 매칭안된 임우진 집밥
        Food food12 = new Food();
        food12.setHost(em.find(User.class, "LimWooJin"));
        food12.setMenu(em.find(Menu.class, "순대국"));
        food12.setTime(LocalDateTime.of(2024, 9, 8, 17, 00));
        food12.setStatus(FoodStatus.READY);

        // 아직 매칭안된 김현아 집밥
        Food food13 = new Food();
        food13.setHost(em.find(User.class, "KimHyunA"));
        food13.setMenu(em.find(Menu.class, "진라면"));
        food13.setTime(LocalDateTime.of(2024, 9, 8, 21, 00));
        food13.setStatus(FoodStatus.READY);

        // 아직 매칭안된 김세현 집밥
        Food food14 = new Food();
        food14.setHost(em.find(User.class, "KimSeHyun"));
        food14.setMenu(em.find(Menu.class, "알리오올리오 파스타"));
        food14.setTime(LocalDateTime.of(2024, 9, 8, 21, 00));
        food14.setStatus(FoodStatus.READY);

        em.persist(food1);
        em.persist(food2);
        em.persist(food3);
        em.persist(food4);
        em.persist(food5);
        em.persist(food6);
        em.persist(food7);
        em.persist(food8);
        em.persist(food9);
        em.persist(food10);
        em.persist(food11);
        em.persist(food12);
        em.persist(food13);
        em.persist(food14);
    }

    @Transactional
    public void initFavoriteHost() {
        // 서상혁
        FavoriteHost favoriteHost1 = new FavoriteHost();
        favoriteHost1.setHost(em.find(User.class, "KwonOhSung"));
        favoriteHost1.setPuppy(em.find(User.class, "SeoSangHyeok"));

        FavoriteHost favoriteHost4 = new FavoriteHost();
        favoriteHost4.setHost(em.find(User.class, "KimHyunA"));
        favoriteHost4.setPuppy(em.find(User.class, "SeoSangHyeok"));

        FavoriteHost favoriteHost8 = new FavoriteHost();
        favoriteHost8.setHost(em.find(User.class, "ChoSangJun"));
        favoriteHost8.setPuppy(em.find(User.class, "SeoSangHyeok"));

        FavoriteHost favoriteHost9 = new FavoriteHost();
        favoriteHost9.setHost(em.find(User.class, "ChoHyungJun"));
        favoriteHost9.setPuppy(em.find(User.class, "SeoSangHyeok"));

        // 민재홍
        FavoriteHost favoriteHost2 = new FavoriteHost();
        favoriteHost2.setHost(em.find(User.class, "KimSeHyun"));
        favoriteHost2.setPuppy(em.find(User.class, "MinJaeHong"));

        FavoriteHost favoriteHost7 = new FavoriteHost();
        favoriteHost7.setHost(em.find(User.class, "ParkDaeWon"));
        favoriteHost7.setPuppy(em.find(User.class, "MinJaeHong"));

        FavoriteHost favoriteHost5 = new FavoriteHost();
        favoriteHost5.setHost(em.find(User.class, "ShinJiHun"));
        favoriteHost5.setPuppy(em.find(User.class, "MinJaeHong"));

        // 김지원
        FavoriteHost favoriteHost3 = new FavoriteHost();
        favoriteHost3.setHost(em.find(User.class, "ChoSangJun"));
        favoriteHost3.setPuppy(em.find(User.class, "KimJiWon"));

        FavoriteHost favoriteHost6 = new FavoriteHost();
        favoriteHost6.setHost(em.find(User.class, "LimWooJin"));
        favoriteHost6.setPuppy(em.find(User.class, "KimJiWon"));

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

    @Transactional
    public void initMarket1() {
        Market market1 = new Market();
        market1.setName("화양제일골목시장");
        market1.setAddress("서울 광진구");
        market1.setDetailAddress("아차산로29길 59");
        market1.setLocation(new Location(37.5434999708465, 127.069283286229));
        market1.setImageURL("https://www.gwangjin.com/imgdata/gwangjin_com/202206/2022061306069649.jpg");

        Shop shop1 = new Shop();
        shop1.setShopName("고기사랑");
        shop1.setDetailAddress("아차산로29길 57");
        shop1.setProductType(ProductType.MEAT);

        Shop shop2 = new Shop();
        shop2.setShopName("명희네젓갈");
        shop2.setDetailAddress("아차산로29길 55");
        shop2.setProductType(ProductType.SIDE_DISH);

        Shop shop3 = new Shop();
        shop3.setShopName("여수반찬");
        shop3.setDetailAddress("아차산로29길 52");
        shop3.setProductType(ProductType.SIDE_DISH);

        Shop shop4 = new Shop();
        shop4.setShopName("제일상회");
        shop4.setDetailAddress("아차산로29길 55");
        shop4.setProductType(ProductType.AGRICULTURAL);

        // 여기부터
        Shop shop5 = new Shop();
        shop5.setShopName("한아름과일");
        shop5.setDetailAddress("아차산로29길 55");
        shop5.setProductType(ProductType.FRUIT);

        Shop shop6 = new Shop();
        shop6.setShopName("황금축산");
        shop6.setDetailAddress("아차산로29길 71");
        shop6.setProductType(ProductType.MEAT);

        Shop shop7 = new Shop();
        shop7.setShopName("우리농산물");
        shop7.setDetailAddress("아차산로29길 69");
        shop7.setProductType(ProductType.VEGETABLES);

        Shop shop8 = new Shop();
        shop8.setShopName("영희네반찬");
        shop8.setDetailAddress("아차산로29길 69");
        shop8.setProductType(ProductType.SIDE_DISH);

        Shop shop9 = new Shop();
        shop9.setShopName("화양두부");
        shop9.setDetailAddress("아차산로29길 59");
        shop9.setProductType(ProductType.SIDE_DISH);

        Shop shop10 = new Shop();
        shop10.setShopName("광어2마리");
        shop10.setDetailAddress("아차산로29길 71");
        shop10.setProductType(ProductType.SEA_FOOD);

        market1.getShopList().add(shop1);
        market1.getShopList().add(shop2);
        market1.getShopList().add(shop3);
        market1.getShopList().add(shop4);
        market1.getShopList().add(shop5);
        market1.getShopList().add(shop6);
        market1.getShopList().add(shop7);
        market1.getShopList().add(shop8);
        market1.getShopList().add(shop9);
        market1.getShopList().add(shop10);

        em.persist(market1);
    }

    @Transactional
    public void initMarket2() {
        Market market2 = new Market();
        market2.setName("자양전통시장");
        market2.setAddress("서울 광진구");
        market2.setDetailAddress("뚝섬로49길 43");
        market2.setLocation(new Location(37.53486821102412, 127.07917411360432));
        market2.setImageURL("https://cdn.weeklyseoul.net/news/photo/201802/38135_15280_2712.jpg");

        Shop shop1 = new Shop();
        shop1.setShopName("맛있는 반찬");
        shop1.setDetailAddress("자양로15길 79");
        shop1.setProductType(ProductType.SIDE_DISH);

        Shop shop2 = new Shop();
        shop2.setShopName("스마일 청과");
        shop2.setDetailAddress("자양로13나길 32");
        shop2.setProductType(ProductType.FRUIT);

        Shop shop3 = new Shop();
        shop3.setShopName("영남떡");
        shop3.setDetailAddress("자양로13나길 11");
        shop3.setProductType(ProductType.RICE_CAKE);

        Shop shop4 = new Shop();
        shop4.setShopName("여수돌산갓김치");
        shop4.setDetailAddress("자양로15길 73");
        shop4.setProductType(ProductType.SIDE_DISH);

        Shop shop5 = new Shop();
        shop5.setShopName("스마일 청과");
        shop5.setDetailAddress("자양로13나길 32");
        shop5.setProductType(ProductType.FRUIT);

        Shop shop6 = new Shop();
        shop6.setShopName("으뜸채소");
        shop6.setDetailAddress("뚝섬로49길 50");
        shop6.setProductType(ProductType.VEGETABLES);

        Shop shop7 = new Shop();
        shop7.setShopName("즐겨찾는그린야채");
        shop7.setDetailAddress("자양로13나길 37");
        shop7.setProductType(ProductType.VEGETABLES);

        Shop shop8 = new Shop();
        shop8.setShopName("웰빙하우스");
        shop8.setDetailAddress("뚝섬로25길 16");
        shop8.setProductType(ProductType.AGRICULTURAL);

        Shop shop9 = new Shop();
        shop9.setShopName("풍년방앗간");
        shop9.setDetailAddress("동일로10길 13");
        shop9.setProductType(ProductType.RICE_CAKE);

        market2.getShopList().add(shop1);
        market2.getShopList().add(shop2);
        market2.getShopList().add(shop3);
        market2.getShopList().add(shop4);
        market2.getShopList().add(shop5);
        market2.getShopList().add(shop6);
        market2.getShopList().add(shop7);
        market2.getShopList().add(shop8);
        market2.getShopList().add(shop9);

        em.persist(market2);
    }

    @Transactional
    public void initMarket3() {
        Market market3 = new Market();
        market3.setName("신성종합시장");
        market3.setAddress("서울 광진구");
        market3.setDetailAddress("용마산로 50");
        market3.setLocation(new Location(37.557954199838896, 127.08848471638808));
        market3.setImageURL("https://cdn.welfarehello.com/naver-blog/production/gwangjin_b/2024-05/223446165879/gwangjin_b_223446165879_2.jpg");

        Shop shop1 = new Shop();
        shop1.setShopName("대우홈마트");
        shop1.setDetailAddress("영화사로 14");
        shop1.setProductType(ProductType.SUPER_MARKET);

        Shop shop2 = new Shop();
        shop2.setShopName("목포수산");
        shop2.setDetailAddress("양화사로 12");
        shop2.setProductType(ProductType.SEA_FOOD);

        Shop shop3 = new Shop();
        shop3.setShopName("서울방앗간");
        shop3.setDetailAddress("용마산로6길 19");
        shop3.setProductType(ProductType.RICE_CAKE);

        Shop shop4 = new Shop();
        shop4.setShopName("종로떡집");
        shop4.setDetailAddress("영화사로 7");
        shop4.setProductType(ProductType.RICE_CAKE);

        Shop shop5 = new Shop();
        shop5.setShopName("오빠네명품축산");
        shop5.setDetailAddress("영화사로 4");
        shop5.setProductType(ProductType.MEAT);

        Shop shop6 = new Shop();
        shop6.setShopName("윤미네즉석반찬");
        shop6.setDetailAddress("영화사로 18");
        shop6.setProductType(ProductType.SIDE_DISH);

        Shop shop7 = new Shop();
        shop7.setShopName("드림야채과일");
        shop7.setDetailAddress("양화사로 4");
        shop7.setProductType(ProductType.VEGETABLES);

        Shop shop8 = new Shop();
        shop8.setShopName("영일만수산시장");
        shop8.setDetailAddress("용마산로6길 9");
        shop8.setProductType(ProductType.SEA_FOOD);

        Shop shop9 = new Shop();
        shop9.setShopName("백운농산");
        shop9.setDetailAddress("양화사로 12");
        shop9.setProductType(ProductType.AGRICULTURAL);

        market3.getShopList().add(shop1);
        market3.getShopList().add(shop2);
        market3.getShopList().add(shop3);
        market3.getShopList().add(shop4);
        market3.getShopList().add(shop5);
        market3.getShopList().add(shop6);
        market3.getShopList().add(shop7);
        market3.getShopList().add(shop8);
        market3.getShopList().add(shop9);

        em.persist(market3);
    }

    @Transactional
    public void initMarket4() {
        Market market4 = new Market();
        market4.setName("중곡제일골목시장");
        market4.setAddress("서울 광진구");
        market4.setDetailAddress("능동로47길 30");
        market4.setLocation(new Location(37.563574942881296, 127.08113146885285));
        market4.setImageURL("https://fastly.4sqi.net/img/general/600x600/5080516_3N5Yo-jincoLGU6sffXY4neJhtrQiRk1KdN-UmKg8IY.jpg");

        Shop shop1 = new Shop();
        shop1.setShopName("광성축산");
        shop1.setDetailAddress("능동로47길 30");
        shop1.setProductType(ProductType.MEAT);

        Shop shop2 = new Shop();
        shop2.setShopName("남해수산");
        shop2.setDetailAddress("능동로47길 30");
        shop2.setProductType(ProductType.SEA_FOOD);

        Shop shop3 = new Shop();
        shop3.setShopName("독도횟포장전문");
        shop3.setDetailAddress("긴고랑로11길 11");
        shop3.setProductType(ProductType.SEA_FOOD);

        Shop shop4 = new Shop();
        shop4.setShopName("서울청과");
        shop4.setDetailAddress("능동로47길 37-1");
        shop4.setProductType(ProductType.FRUIT);

        Shop shop5 = new Shop();
        shop5.setShopName("웰빙야채");
        shop5.setDetailAddress("능동로47길 20");
        shop5.setProductType(ProductType.VEGETABLES);

        Shop shop6 = new Shop();
        shop6.setShopName("엄마손반찬");
        shop6.setDetailAddress("긴고랑로9길 16-1");
        shop6.setProductType(ProductType.SIDE_DISH);

        Shop shop7 = new Shop();
        shop7.setShopName("믿음을주는농산물");
        shop7.setDetailAddress("능동로47길 41");
        shop7.setProductType(ProductType.AGRICULTURAL);

        Shop shop8 = new Shop();
        shop8.setShopName("포도밭사나이");
        shop8.setDetailAddress("긴고랑로13길 15");
        shop8.setProductType(ProductType.FRUIT);

        market4.getShopList().add(shop1);
        market4.getShopList().add(shop2);
        market4.getShopList().add(shop3);
        market4.getShopList().add(shop4);
        market4.getShopList().add(shop5);
        market4.getShopList().add(shop6);
        market4.getShopList().add(shop7);
        market4.getShopList().add(shop8);

        em.persist(market4);
    }
}