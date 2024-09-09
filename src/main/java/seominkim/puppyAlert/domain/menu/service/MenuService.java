package seominkim.puppyAlert.domain.menu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.menu.entity.Menu;
import seominkim.puppyAlert.domain.menu.repository.MenuRepository;
import seominkim.puppyAlert.global.utility.ImageCrawler;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    private final ImageCrawler imageCrawler;

    // findOne 이라는 메서드 자체가 트랜잭션을 관리하게 해야함
    // addNewMenu 에 대한 트랜잭션을 책임져야하므로 얘도 Transactional 이 되어야함
    @Transactional(readOnly = true)
    public Menu getMenu(String menuName){
        return menuRepository.findById(menuName).orElseThrow();
    }

    @Transactional(readOnly = true)
    public boolean checkIfMenuExists(String menuName){
        return menuRepository.existsById(menuName);
    }

    @Transactional
    public Menu addNewMenu(String menuName){
        Menu menu = new Menu();
        menu.setMenuName(menuName);
        menu.setImageURL(imageCrawler.getImageURLByKakaoAPI(menuName));

        menuRepository.save(menu);

        return menu;
    }
}
