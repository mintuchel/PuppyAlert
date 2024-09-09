package seominkim.puppyAlert.domain.menu.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.menu.entity.Menu;
import seominkim.puppyAlert.domain.menu.repository.MenuRepository;
import seominkim.puppyAlert.global.utility.ImageCrawler;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {
//    @InjectMocks
//    private MenuService menuService;
//
//    @Mock
//    private MenuRepository menuRepository;
//
//    @Mock
//    private ImageCrawler imageCrawler;
//
//    private Menu menu;
//    private String menuName = "콩국수";
//
//    @BeforeEach
//    private void testSetUp(){
//        menu = new Menu();
//        menu.setMenuName(menuName);
//    }
//
//    @Test
//    @DisplayName("메뉴 조회")
//    void findMenuTest(){
//        // given
//        given(menuRepository.existsById(menuName)).willReturn(true);
//        given(menuRepository.findById(menuName)).willReturn(Optional.of(menu));
//
//        // when
//        Menu savedMenu = menuService.getMenu(menuName);
//
//        Assertions.assertThat(savedMenu.getMenuName()).isEqualTo(menu.getMenuName());
//    }
//
//
//    @Autowired MenuService menuService;
//
//    @Test
//    @Transactional
//    @Rollback
//    public void menuServiceTest(){
//
//        String keyword = "피자";
//        System.out.println(menuService.findOne(keyword).getImageURL());
//
//        keyword = "햄버거";
//        System.out.println(menuService.findOne(keyword).getImageURL());
//
//        keyword = "삼계탕";
//        System.out.println(menuService.findOne(keyword).getImageURL());
//
//        keyword = "제육볶음";
//        System.out.println(menuService.findOne(keyword).getImageURL());
//
//        keyword = "콩국수";
//        System.out.println(menuService.findOne(keyword).getImageURL());
//
//        keyword = "비빔밥";
//        System.out.println(menuService.findOne(keyword).getImageURL());
//
//        // 중복된 키워드 들어왔을때 URL DB에서 끌어오는지 확인
//        String protoURL = menuService.findOne("콩국수").getImageURL();
//        Assertions.assertThat(protoURL).isEqualTo(menuService.findOne("콩국수").getImageURL());
//    }
}