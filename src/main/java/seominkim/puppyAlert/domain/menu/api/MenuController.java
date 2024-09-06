package seominkim.puppyAlert.domain.menu.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seominkim.puppyAlert.domain.menu.service.MenuService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu")
@Tag(name = "Menu API", description = "메뉴 확인")
public class MenuController {
    private final MenuService menuService;

//    @Operation(summary = "API 스펙 확인용")
//    @GetMapping("")
//    public String checkSpec(){
//        return menuService.checkResponseSpec();
//    }

    @Operation(summary = "메뉴 존재 확인")
    @GetMapping("/check")
    public String checkMenu(@RequestParam String menuName){
        return menuService.checkMenu(menuName);
    }
}
