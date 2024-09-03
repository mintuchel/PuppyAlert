package seominkim.puppyAlert.domain.dummy;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DummyGenerator {
    private final DummyService dummyService;

    @PostConstruct
    private void generateDummy() {
        dummyService.initHost();
        dummyService.initPuppy();
        dummyService.initMenu();
        dummyService.initFood();
        dummyService.initFavoriteHost();
        dummyService.initMarket1();
        dummyService.initMarket2();
        dummyService.initMarket3();
        dummyService.initMarket4();
    }
}
