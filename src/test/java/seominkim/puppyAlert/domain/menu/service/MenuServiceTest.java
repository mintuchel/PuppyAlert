package seominkim.puppyAlert.domain.menu.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MenuServiceTest {

    @Autowired MenuService menuService;

    @Test
    @Transactional
    @Rollback
    public void menuServiceTest(){

        String keyword = "피자";
        System.out.println(menuService.findOne(keyword).getImageURL());

        keyword = "햄버거";
        System.out.println(menuService.findOne(keyword).getImageURL());

        keyword = "삼계탕";
        System.out.println(menuService.findOne(keyword).getImageURL());

        keyword = "제육볶음";
        System.out.println(menuService.findOne(keyword).getImageURL());

        keyword = "콩국수";
        System.out.println(menuService.findOne(keyword).getImageURL());

        keyword = "비빔밥";
        System.out.println(menuService.findOne(keyword).getImageURL());

        // 중복된 키워드 들어왔을때 URL DB에서 끌어오는지 확인
        String protoURL = menuService.findOne("콩국수").getImageURL();
        Assertions.assertThat(protoURL).isEqualTo(menuService.findOne("콩국수").getImageURL());
    }
}