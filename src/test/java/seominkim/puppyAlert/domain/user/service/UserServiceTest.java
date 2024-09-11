package seominkim.puppyAlert.domain.user.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.food.entity.MatchStatus;
import seominkim.puppyAlert.domain.menu.entity.Menu;
import seominkim.puppyAlert.domain.user.dto.response.DayFoodResponse;
import seominkim.puppyAlert.domain.user.entity.User;
import seominkim.puppyAlert.domain.user.repository.UserRepository;
import seominkim.puppyAlert.global.entity.UserType;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.UserException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

// test end
// 중요한게 오늘의 집밥 불러오는거 밖에 없음
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    private User puppy, host;
    private Menu menu;

    @BeforeEach
    private void testSetUp(){
        puppy = new User();
        host = new User();

        menu = new Menu();
        menu.setMenuName("순대국");
        menu.setImageURL("xyz");

        puppy.setId("eden");
        puppy.setNickName("hazard");
        puppy.setUserType(UserType.PUPPY);
        puppy.setPuppyFoods(getFoodList());

        host.setId("diego");
        host.setNickName("costa");
        host.setUserType(UserType.HOST);
        host.setHostFoods(getFoodList());
    }

    @Test
    @DisplayName("Puppy 오늘의 집밥 조회 성공")
    public void getDayFoodByPuppySuccess(){
        // given
        given(userRepository.findById(puppy.getId())).willReturn(Optional.of(puppy));

        // when
        DayFoodResponse response = userService.getDayFood(puppy.getId());

        // then
        // verify(puppy).getPuppyFoods(); 오로지 mock된 것들만 verify 가능
        Assertions.assertThat(response.partnerId()).isEqualTo(host.getId());
    }

    @Test
    @DisplayName("Host 오늘의 집밥 조회 성공")
    public void getDayFoodByHostSuccess(){
        // given
        given(userRepository.findById(host.getId())).willReturn(Optional.of(host));

        // when
        DayFoodResponse response = userService.getDayFood(host.getId());

        Assertions.assertThat(response.partnerId()).isEqualTo(puppy.getId());
    }

    @Test
    @DisplayName("오늘의 집밥 조회 실패")
    public void getDayFoodFailure(){
        // given
        puppy.setPuppyFoods(new ArrayList<>()); // 예외터지도록 빈 list 넣어주기
        given(userRepository.findById(puppy.getId())).willReturn(Optional.of(puppy));

        // when & then
        UserException exception = assertThrows(UserException.class, () -> {
            userService.getDayFood(puppy.getId());
        });

        // 에러코드 검증
        Assertions.assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.NO_TODAY_FOOD);
    }

    private List<Food> getFoodList(){

        List<Food> returnedList = new ArrayList<>();

        Food food = new Food();
        food.setFoodId(3L);
        food.setHost(host);
        food.setPuppy(puppy);
        food.setMenu(new Menu());
        food.setTime(LocalDateTime.now());
        food.setMatchStatus(MatchStatus.MATCHED);

        returnedList.add(food);

        return returnedList;
    }
}
