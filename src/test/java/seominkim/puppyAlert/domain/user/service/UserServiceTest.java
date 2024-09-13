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

        // FoodList에서 host puppy객체가 제대로 생성되어있어야함
        // 그래서 미리 생성 -> 살짝 예외적인 BeforeEach 흐름
        puppy = User.builder().id("eden").build();
        host = User.builder().id("diego").build();

        menu = Menu.builder()
                .menuName("순대국")
                .imageURL("xyz")
                .build();

        puppy = User.builder()
                .id("eden")
                .nickName("hazard")
                .userType(UserType.PUPPY)
                .puppyFoods(getFoodList())
                .build();

        host = User.builder()
                .id("diego")
                .nickName("costa")
                .userType(UserType.HOST)
                .hostFoods(getFoodList())
                .build();
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
        puppy = User.builder()
                .id("eden")
                .nickName("hazard")
                .userType(UserType.PUPPY)
                .puppyFoods(new ArrayList<>()) // 예외터지도록 빈 list 넣어주기
                .build();

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

        Food food = Food.builder()
                .foodId(3L)
                .host(host)
                .puppy(puppy)
                .menu(Menu.builder().build())
                .time(LocalDateTime.now())
                .matchStatus(MatchStatus.MATCHED)
                .build();

        returnedList.add(food);

        return returnedList;
    }
}
