package seominkim.puppyAlert.domain.dummy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.food.entity.MatchStatus;
import seominkim.puppyAlert.domain.market.entity.Market;
import seominkim.puppyAlert.domain.menu.entity.Menu;
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
        User host1 = User.builder()
                .id("KwonOhSung")
                .name("권오성")
                .password("a12345678!")
                .nickName("한입당호우")
                .birth(LocalDate.now())
                .address("군자동 150-14")
                .detailAddress("엔샵빌라 16동 209호")
                .location(new Location(37.55379265523353, 127.07382983231048))
                .phoneNumber("010-1582-3145")
                .profileImageURL("https://avatars.githubusercontent.com/u/79124461?v=4")
                .userType(UserType.HOST)
                .build();

        User host2 = User.builder()
                .id("KimSeHyun")
                .name("김세현")
                .nickName("맑은햇살")
                .password("a12345678!")
                .birth(LocalDate.now())
                .address("군자동 363-13")
                .detailAddress("한마음빌라 201동 103호")
                .location(new Location(37.55007773494337, 127.07106493659647))
                .phoneNumber("010-3244-7988")
                .profileImageURL("https://avatars.githubusercontent.com/u/102282703?v=4")
                .userType(UserType.HOST)
                .build();

        User host3 = User.builder()
                .id("ChoSangJun")
                .name("조상준")
                .nickName("군자동음바페")
                .password("a12345678!")
                .birth(LocalDate.now())
                .address("군자동 236")
                .detailAddress("금성빌라 302호")
                .location(new Location(37.551907956849995, 127.07636576512374))
                .phoneNumber("010-5814-6568")
                .profileImageURL("https://i.namu.wiki/i/HMdru7SjwjVOqewZu1e-IlD0Cy0sqjQ9RDoFYCg-3qUDW6l2hOuBbvKbrjjPfE75lCgAM9P8nW0fOzV8q0A8ij11OLMAgjFoj5JefZGG90D9B6R_rA81rQ_78AURHmjkBSkxOuD59KDx29B3DBIUTQ.webp")
                .userType(UserType.HOST)
                .build();

        User host4 = User.builder()
                .id("LimWooJin")
                .name("임우진")
                .password("a12345678!")
                .nickName("화양동백종원")
                .birth(LocalDate.now())
                .address("화양동 18-21")
                .detailAddress("카카오텔 405호")
                .location(new Location(37.5475940875437, 127.06962825715127))
                .phoneNumber("010-4198-1241")
                .profileImageURL("https://avatars.githubusercontent.com/u/25877253?v=4")
                .userType(UserType.HOST)
                .build();

        User host5 = User.builder()
                .id("KimHyunA")
                .name("김현아")
                .password("a12345678!")
                .nickName("라면물조절장인")
                .birth(LocalDate.now())
                .address("화양동 495-10")
                .detailAddress("영앤리치빌라 506호")
                .location(new Location(37.54593669782253, 127.07619842193493))
                .phoneNumber("010-8731-1245")
                .profileImageURL("https://avatars.githubusercontent.com/u/129165742?v=4")
                .userType(UserType.HOST)
                .build();

        User host6 = User.builder()
                .id("ParkDaeWon")
                .name("박대원")
                .password("a12345678!")
                .nickName("호밀밭의요리사")
                .birth(LocalDate.now())
                .address("군자동 230")
                .detailAddress("현대빌라 1203호")
                .location(new Location(37.55262356990496, 127.07741047989005))
                .phoneNumber("010-1211-1000")
                .profileImageURL("https://avatars.githubusercontent.com/u/101173462?v=4")
                .userType(UserType.HOST)
                .build();

        User host7 = User.builder()
                .id("ChoHyungJun")
                .name("조형준")
                .password("a12345678!")
                .nickName("비도오고그래서")
                .birth(LocalDate.now())
                .address("화양동 217")
                .detailAddress("화양주택 2동 101호")
                .location(new Location(37.547096349517936, 127.07324610787151))
                .phoneNumber("010-6587-8014")
                .profileImageURL("https://avatars.githubusercontent.com/u/1024025?v=4")
                .userType(UserType.HOST)
                .build();

        User host8 = User.builder()
                .id("ShinJiHun")
                .name("신지훈")
                .password("a12345678!")
                .nickName("한없이맑은눈물")
                .birth(LocalDate.now())
                .address("화양동 131-3")
                .detailAddress("저스트뮤직빌라 402호")
                .location(new Location(37.54588160784895, 127.0706083175839))
                .phoneNumber("010-3265-0541")
                .profileImageURL("https://avatars.githubusercontent.com/u/82876698?v=4")
                .userType(UserType.HOST)
                .build();

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
        User puppy1 = User.builder()
                .id("SeoSangHyeok")
                .name("서상혁")
                .password("a12345678!")
                .nickName("후식없으면안감요")
                .birth(LocalDate.now())
                .address("군자동 100-2")
                .detailAddress("현대아파트 103동 801호")
                .location(new Location(37.55191738446834, 127.0721191456786))
                .phoneNumber("010-1111-2222")
                .profileImageURL("https://avatars.githubusercontent.com/u/126949574?v=4")
                .userType(UserType.PUPPY)
                .build();

        User puppy2 = User.builder()
                .id("MinJaeHong")
                .name("민재홍")
                .password("a12345678!")
                .nickName("제육헌터")
                .birth(LocalDate.now())
                .address("군자동 98")
                .detailAddress("세종대학교 학생회관 530호")
                .location(new Location(37.549611195346664, 127.07514407425086))
                .phoneNumber("010-6457-9554")
                .profileImageURL("https://avatars.githubusercontent.com/u/103743166?v=4")
                .userType(UserType.PUPPY)
                .build();

        User puppy3 = User.builder()
                .id("KimJiWon")
                .name("김지원")
                .password("a12345678!")
                .nickName("민초러버")
                .birth(LocalDate.now())
                .address("화양동 110-37")
                .detailAddress("화양타워 103동 204호")
                .location(new Location(37.547023288665116, 127.07116671038504))
                .phoneNumber("010-8221-7458")
                .profileImageURL("https://avatars.githubusercontent.com/u/15929412?v=4")
                .userType(UserType.PUPPY)
                .build();

        em.persist(puppy1);
        em.persist(puppy2);
        em.persist(puppy3);
    }


    @Transactional
    public void initMenu() {
        Menu menu1 = Menu.builder()
                .menuName("까르보나라")
                .imageURL("https://blog.kakaocdn.net/dn/cDOn0y/btruVLrLi88/aXkUtkaXRvdWVqaJv89Jn0/img.jpg")
                .build();

        Menu menu2 = Menu.builder()
                .menuName("제육볶음")
                .imageURL("https://godomall.speedycdn.net/0f1081f2872a5b54f665bd623a5689cb/goods/1159/image/detail/1159_detail_097.jpg")
                .build();

        Menu menu3 = Menu.builder()
                .menuName("삼계탕")
                .imageURL("https://sitem.ssgcdn.com/43/44/21/item/1000412214443_i1_750.jpg")
                .build();

        Menu menu4 = Menu.builder()
                .menuName("순두부찌개")
                .imageURL("https://honestkfood.co.kr/wp-content/uploads/2014/08/16.%EC%88%9C%EB%91%90%EB%B6%80%EC%B0%8C%EA%B0%9C1.png")
                .build();

        Menu menu5 = Menu.builder()
                .menuName("얼큰한 해물라면")
                .imageURL("https://sitem.ssgcdn.com/53/50/93/item/1000018935053_i2_750.jpg")
                .build();

        Menu menu6 = Menu.builder()
                .menuName("우거지 해장국")
                .imageURL("https://gdimg1.gmarket.co.kr/goods_image2/shop_moreimg/213/691/2136917571/2136917571_01.jpg?ver=1682053775")
                .build();

        Menu menu7 = Menu.builder()
                .menuName("통등심 돈까스")
                .imageURL("https://cdn.oasis.co.kr:48581/product/81639/thumb/3dc6cd2f-09cd-47aa-a425-3772887d03bf.jpg")
                .build();

        Menu menu8 = Menu.builder()
                .menuName("녹두전")
                .imageURL("https://thenaum.cdn-nhncommerce.com/data/goods/20/07/31/1000005027/1000005027_detail_093.jpg")
                .build();

        Menu menu9 = Menu.builder()
                .menuName("불고기고구마 피자")
                .imageURL("https://cdn.dominos.co.kr/admin/upload/goods/20230619_F33836Pn.jpg")
                .build();

        Menu menu10 = Menu.builder()
                .menuName("스팸김치찌개")
                .imageURL("https://blog.kakaocdn.net/dn/Uouhp/btrAcaBjdTN/68U9LEuWwkZPpNIKtSYdl0/img.jpg")
                .build();

        Menu menu11 = Menu.builder()
                .menuName("회덮밥")
                .imageURL("https://static.wtable.co.kr/image/production/service/recipe/33/8f5c0ee2-35fe-4377-a065-07203f8cc7f7.jpg?size=800x800")
                .build();

        Menu menu12 = Menu.builder()
                .menuName("순대국")
                .imageURL("https://d12zq4w4guyljn.cloudfront.net/750_750_20210603124244841_photo_41af3676fc32.jpg")
                .build();

        Menu menu13 = Menu.builder()
                .menuName("진라면")
                .imageURL("https://powershop171020.cafe24.com/image/other_img/goods_1081071001001000/20180222_160259.png")
                .build();

        Menu menu14 = Menu.builder()
                .menuName("알리오올리오 파스타")
                .imageURL("https://static.wtable.co.kr/image/production/service/recipe/628/e4c99ef8-3207-4451-a0b2-cac2c9249b23.jpg")
                .build();

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
        Food food1 = Food.builder()
                .host(em.find(User.class, "KwonOhSung"))
                .puppy(em.find(User.class, "SeoSangHyeok"))
                .menu(em.find(Menu.class, "까르보나라"))
                .time(LocalDateTime.of(2024, 8, 14, 14, 30))
                .matchStatus(MatchStatus.COMPLETE)
                .build();

        // 서상혁-김현아
        Food food5 = Food.builder()
                .host(em.find(User.class, "KimHyunA"))
                .puppy(em.find(User.class, "SeoSangHyeok"))
                .menu(em.find(Menu.class, "얼큰한 해물라면"))
                .time(LocalDateTime.of(2024, 8, 23, 18, 00))
                .matchStatus(MatchStatus.COMPLETE)
                .build();

        // 서상혁-박대원 오늘의 집밥
        Food food7 = Food.builder()
                .host(em.find(User.class, "ParkDaeWon"))
                .puppy(em.find(User.class, "SeoSangHyeok"))
                .menu(em.find(Menu.class, "통등심 돈까스"))
                .time(LocalDateTime.of(2024, 9, 12, 10, 20))
                .matchStatus(MatchStatus.MATCHED)
                .build();

        // 민재홍-김세현
        Food food2 = Food.builder()
                .host(em.find(User.class, "KimSeHyun"))
                .puppy(em.find(User.class, "MinJaeHong"))
                .menu(em.find(Menu.class, "제육볶음"))
                .time(LocalDateTime.of(2024, 8, 5, 18, 00))
                .matchStatus(MatchStatus.COMPLETE)
                .build();

        // 민재홍-박대원
        Food food6 = Food.builder()
                .host(em.find(User.class, "ParkDaeWon"))
                .puppy(em.find(User.class, "MinJaeHong"))
                .menu(em.find(Menu.class, "우거지 해장국"))
                .time(LocalDateTime.of(2024, 8, 21, 19, 00))
                .matchStatus(MatchStatus.COMPLETE)
                .build();

        // 민재홍-조형준 오늘의 집밥
        Food food8 = Food.builder()
                .host(em.find(User.class, "ChoHyungJun"))
                .puppy(em.find(User.class, "MinJaeHong"))
                .menu(em.find(Menu.class, "녹두전"))
                .time(LocalDateTime.of(2024, 9, 12, 15, 35))
                .matchStatus(MatchStatus.MATCHED)
                .build();

        // 김지원-조상준
        Food food3 = Food.builder()
                .host(em.find(User.class, "ChoSangJun"))
                .puppy(em.find(User.class, "KimJiWon"))
                .menu(em.find(Menu.class, "삼계탕"))
                .time(LocalDateTime.of(2024, 8, 16, 11, 20))
                .matchStatus(MatchStatus.COMPLETE)
                .build();

        // 김지원-임우진
        Food food4 = Food.builder()
                .host(em.find(User.class, "LimWooJin"))
                .puppy(em.find(User.class, "KimJiWon"))
                .menu(em.find(Menu.class, "순두부찌개"))
                .time(LocalDateTime.of(2024, 8, 21, 17, 40))
                .matchStatus(MatchStatus.COMPLETE)
                .build();

        // 김지원-신지훈 집밥 오늘의 집밥
        Food food9 = Food.builder()
                .host(em.find(User.class, "ShinJiHun"))
                .puppy(em.find(User.class, "KimJiWon"))
                .menu(em.find(Menu.class, "불고기고구마 피자"))
                .time(LocalDateTime.of(2024, 9, 12, 21, 00))
                .matchStatus(MatchStatus.MATCHED)
                .build();

        // 아직 매칭안된 조상준 집밥
        Food food10 = Food.builder()
                .host(em.find(User.class, "ChoSangJun"))
                .menu(em.find(Menu.class, "스팸김치찌개"))
                .time(LocalDateTime.of(2024, 9, 12, 21, 30))
                .matchStatus(MatchStatus.READY)
                .build();

        // 아직 매칭안된 권오성 집밥
        Food food11 = Food.builder()
                .host(em.find(User.class, "KwonOhSung"))
                .menu(em.find(Menu.class, "회덮밥"))
                .time(LocalDateTime.of(2024, 9, 12, 19, 40))
                .matchStatus(MatchStatus.READY)
                .build();

        // 아직 매칭안된 임우진 집밥
        Food food12 = Food.builder()
                .host(em.find(User.class, "LimWooJin"))
                .menu(em.find(Menu.class, "순대국"))
                .time(LocalDateTime.of(2024, 9, 12, 17, 00))
                .matchStatus(MatchStatus.READY)
                .build();

        // 아직 매칭안된 김현아 집밥
        Food food13 = Food.builder()
                .host(em.find(User.class, "KimHyunA"))
                .menu(em.find(Menu.class, "진라면"))
                .time(LocalDateTime.of(2024, 9, 12, 21, 00))
                .matchStatus(MatchStatus.READY)
                .build();

        // 아직 매칭안된 김세현 집밥
        Food food14 = Food.builder()
                .host(em.find(User.class, "KimSeHyun"))
                .menu(em.find(Menu.class, "알리오올리오 파스타"))
                .time(LocalDateTime.of(2024, 9, 12, 21, 00))
                .matchStatus(MatchStatus.READY)
                .build();

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
        FavoriteHost favoriteHost1 = FavoriteHost.builder()
                .host(em.find(User.class, "KwonOhSung"))
                .puppy(em.find(User.class, "SeoSangHyeok"))
                .build();

        FavoriteHost favoriteHost4 = FavoriteHost.builder()
                .host(em.find(User.class, "KimHyunA"))
                .puppy(em.find(User.class, "SeoSangHyeok"))
                .build();

        FavoriteHost favoriteHost8 = FavoriteHost.builder()
                .host(em.find(User.class, "ChoSangJun"))
                .puppy(em.find(User.class, "SeoSangHyeok"))
                .build();

        FavoriteHost favoriteHost9 = FavoriteHost.builder()
                .host(em.find(User.class, "ChoHyungJun"))
                .puppy(em.find(User.class, "SeoSangHyeok"))
                .build();

        FavoriteHost favoriteHost2 = FavoriteHost.builder()
                .host(em.find(User.class, "KimSeHyun"))
                .puppy(em.find(User.class, "MinJaeHong"))
                .build();

        FavoriteHost favoriteHost7 = FavoriteHost.builder()
                .host(em.find(User.class, "ParkDaeWon"))
                .puppy(em.find(User.class, "MinJaeHong"))
                .build();

        FavoriteHost favoriteHost5 = FavoriteHost.builder()
                .host(em.find(User.class, "ShinJiHun"))
                .puppy(em.find(User.class, "MinJaeHong"))
                .build();

        FavoriteHost favoriteHost3 = FavoriteHost.builder()
                .host(em.find(User.class, "ChoSangJun"))
                .puppy(em.find(User.class, "KimJiWon"))
                .build();

        FavoriteHost favoriteHost6 = FavoriteHost.builder()
                .host(em.find(User.class, "LimWooJin"))
                .puppy(em.find(User.class, "KimJiWon"))
                .build();

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
        Market market1 = Market.builder()
                .name("화양제일골목시장")
                .address("서울 광진구")
                .detailAddress("아차산로29길 59")
                .location(new Location(37.5434999708465, 127.069283286229))
                .imageURL("https://www.gwangjin.com/imgdata/gwangjin_com/202206/2022061306069649.jpg")
                .build();

        Shop shop1 = Shop.builder()
                .shopName("고기사랑")
                .detailAddress("아차산로29길 57")
                .productType(ProductType.MEAT)
                .build();

        Shop shop2 = Shop.builder()
                .shopName("명희네젓갈")
                .detailAddress("아차산로29길 55")
                .productType(ProductType.SIDE_DISH)
                .build();

        Shop shop3 = Shop.builder()
                .shopName("여수반찬")
                .detailAddress("아차산로29길 52")
                .productType(ProductType.SIDE_DISH)
                .build();

        Shop shop4 = Shop.builder()
                .shopName("제일상회")
                .detailAddress("아차산로29길 55")
                .productType(ProductType.AGRICULTURAL)
                .build();

        Shop shop5 = Shop.builder()
                .shopName("한아름과일")
                .detailAddress("아차산로29길 55")
                .productType(ProductType.FRUIT)
                .build();

        Shop shop6 = Shop.builder()
                .shopName("황금축산")
                .detailAddress("아차산로29길 71")
                .productType(ProductType.MEAT)
                .build();

        Shop shop7 = Shop.builder()
                .shopName("우리농산물")
                .detailAddress("아차산로29길 69")
                .productType(ProductType.VEGETABLES)
                .build();

        Shop shop8 = Shop.builder()
                .shopName("영희네반찬")
                .detailAddress("아차산로29길 69")
                .productType(ProductType.SIDE_DISH)
                .build();

        Shop shop9 = Shop.builder()
                .shopName("화양두부")
                .detailAddress("아차산로29길 59")
                .productType(ProductType.SIDE_DISH)
                .build();

        Shop shop10 = Shop.builder()
                .shopName("광어2마리")
                .detailAddress("아차산로29길 71")
                .productType(ProductType.SEA_FOOD)
                .build();

        // Add shops to market
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
        Market market2 = Market.builder()
                .name("자양전통시장")
                .address("서울 광진구")
                .detailAddress("뚝섬로49길 43")
                .location(new Location(37.53486821102412, 127.07917411360432))
                .imageURL("https://cdn.weeklyseoul.net/news/photo/201802/38135_15280_2712.jpg")
                .build();

        Shop shop1 = Shop.builder()
                .shopName("맛있는 반찬")
                .detailAddress("자양로15길 79")
                .productType(ProductType.SIDE_DISH)
                .build();

        Shop shop2 = Shop.builder()
                .shopName("스마일 청과")
                .detailAddress("자양로13나길 32")
                .productType(ProductType.FRUIT)
                .build();

        Shop shop3 = Shop.builder()
                .shopName("영남떡")
                .detailAddress("자양로13나길 11")
                .productType(ProductType.RICE_CAKE)
                .build();

        Shop shop4 = Shop.builder()
                .shopName("여수돌산갓김치")
                .detailAddress("자양로15길 73")
                .productType(ProductType.SIDE_DISH)
                .build();

        Shop shop5 = Shop.builder()
                .shopName("스마일 청과")
                .detailAddress("자양로13나길 32")
                .productType(ProductType.FRUIT)
                .build();

        Shop shop6 = Shop.builder()
                .shopName("으뜸채소")
                .detailAddress("뚝섬로49길 50")
                .productType(ProductType.VEGETABLES)
                .build();

        Shop shop7 = Shop.builder()
                .shopName("즐겨찾는그린야채")
                .detailAddress("자양로13나길 37")
                .productType(ProductType.VEGETABLES)
                .build();

        Shop shop8 = Shop.builder()
                .shopName("웰빙하우스")
                .detailAddress("뚝섬로25길 16")
                .productType(ProductType.AGRICULTURAL)
                .build();

        Shop shop9 = Shop.builder()
                .shopName("풍년방앗간")
                .detailAddress("동일로10길 13")
                .productType(ProductType.RICE_CAKE)
                .build();

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
        Market market3 = Market.builder()
                .name("신성종합시장")
                .address("서울 광진구")
                .detailAddress("용마산로 50")
                .location(new Location(37.557954199838896, 127.08848471638808))
                .imageURL("https://cdn.welfarehello.com/naver-blog/production/gwangjin_b/2024-05/223446165879/gwangjin_b_223446165879_2.jpg")
                .build();

        Shop shop1 = Shop.builder()
                .shopName("대우홈마트")
                .detailAddress("영화사로 14")
                .productType(ProductType.SUPER_MARKET)
                .build();

        Shop shop2 = Shop.builder()
                .shopName("목포수산")
                .detailAddress("양화사로 12")
                .productType(ProductType.SEA_FOOD)
                .build();

        Shop shop3 = Shop.builder()
                .shopName("서울방앗간")
                .detailAddress("용마산로6길 19")
                .productType(ProductType.RICE_CAKE)
                .build();

        Shop shop4 = Shop.builder()
                .shopName("종로떡집")
                .detailAddress("영화사로 7")
                .productType(ProductType.RICE_CAKE)
                .build();

        Shop shop5 = Shop.builder()
                .shopName("오빠네명품축산")
                .detailAddress("영화사로 4")
                .productType(ProductType.MEAT)
                .build();

        Shop shop6 = Shop.builder()
                .shopName("윤미네즉석반찬")
                .detailAddress("영화사로 18")
                .productType(ProductType.SIDE_DISH)
                .build();

        Shop shop7 = Shop.builder()
                .shopName("드림야채과일")
                .detailAddress("양화사로 4")
                .productType(ProductType.VEGETABLES)
                .build();

        Shop shop8 = Shop.builder()
                .shopName("영일만수산시장")
                .detailAddress("용마산로6길 9")
                .productType(ProductType.SEA_FOOD)
                .build();

        Shop shop9 = Shop.builder()
                .shopName("백운농산")
                .detailAddress("양화사로 12")
                .productType(ProductType.AGRICULTURAL)
                .build();

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
        Market market4 = Market.builder()
                .name("중곡제일골목시장")
                .address("서울 광진구")
                .detailAddress("능동로47길 30")
                .location(new Location(37.563574942881296, 127.08113146885285))
                .imageURL("https://fastly.4sqi.net/img/general/600x600/5080516_3N5Yo-jincoLGU6sffXY4neJhtrQiRk1KdN-UmKg8IY.jpg")
                .build();

        Shop shop1 = Shop.builder()
                .shopName("광성축산")
                .detailAddress("능동로47길 30")
                .productType(ProductType.MEAT)
                .build();

        Shop shop2 = Shop.builder()
                .shopName("남해수산")
                .detailAddress("능동로47길 30")
                .productType(ProductType.SEA_FOOD)
                .build();

        Shop shop3 = Shop.builder()
                .shopName("독도횟포장전문")
                .detailAddress("긴고랑로11길 11")
                .productType(ProductType.SEA_FOOD)
                .build();

        Shop shop4 = Shop.builder()
                .shopName("서울청과")
                .detailAddress("능동로47길 37-1")
                .productType(ProductType.FRUIT)
                .build();

        Shop shop5 = Shop.builder()
                .shopName("웰빙야채")
                .detailAddress("능동로47길 20")
                .productType(ProductType.VEGETABLES)
                .build();

        Shop shop6 = Shop.builder()
                .shopName("엄마손반찬")
                .detailAddress("긴고랑로9길 16-1")
                .productType(ProductType.SIDE_DISH)
                .build();

        Shop shop7 = Shop.builder()
                .shopName("믿음을주는농산물")
                .detailAddress("능동로47길 41")
                .productType(ProductType.AGRICULTURAL)
                .build();

        Shop shop8 = Shop.builder()
                .shopName("포도밭사나이")
                .detailAddress("긴고랑로13길 15")
                .productType(ProductType.FRUIT)
                .build();

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