package seominkim.puppyAlert.domain.menu.service;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seominkim.puppyAlert.domain.menu.entity.Menu;
import seominkim.puppyAlert.domain.menu.repository.MenuRepository;
import seominkim.puppyAlert.global.utility.ImageCrawler;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

// test end
@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {
    @InjectMocks
    private MenuService menuService;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private ImageCrawler imageCrawler;

    private Menu menu;
    private String menuName = "콩국수";
    private String menuUrl = "setUpURL";

    @BeforeEach
    private void testSetUp(){
        menu = new Menu();
        menu.setMenuName(menuName);
        menu.setImageURL(menuUrl);
    }

    @Test
    @DisplayName("메뉴 기존꺼 가져오기 성공")
    void getMenuByFindByIdTest(){
        // given
        given(menuRepository.existsById(menuName)).willReturn(true);
        given(menuRepository.findById(menuName)).willReturn(Optional.of(menu));

        // when
        Menu savedMenu = menuService.getMenu(menuName);

        // then
        Assertions.assertThat(savedMenu.getMenuName()).isEqualTo(menuName);
    }

    @Test
    @DisplayName("메뉴 크롤링해서 가져오기 성공")
    void getMenuByAddNewMenuTest(){
        // given
        given(menuRepository.existsById(menuName)).willReturn(false);
        given(imageCrawler.getImageURLByKakaoAPI(menuName)).willReturn("testSuccessUrl");

        // when
        Menu newMenu = menuService.getMenu(menuName);

        // then
        Assertions.assertThat(newMenu.getMenuName()).isEqualTo(menuName);
        Assertions.assertThat(newMenu.getImageURL()).isEqualTo("testSuccessUrl");
    }
}