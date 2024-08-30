package seominkim.puppyAlert.domain.food.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.food.entity.FoodStatus;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.menu.entity.Menu;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class FoodRepositoryTest {
    @Autowired
    private FoodRepository foodRepository;

    @Test
    @DisplayName("집밥 추가")
    void addFood(){
        // given
        //Food food = food();

        // when

        // then
    }

    @Test
    @DisplayName("집밥 단건 조회")
    void findOne(){

    }

    @Test
    @DisplayName("식사 가능한 집밥 조회")
    void findAvailableFood(){

    }
}