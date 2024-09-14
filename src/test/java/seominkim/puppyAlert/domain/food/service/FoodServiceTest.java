package seominkim.puppyAlert.domain.food.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.food.entity.MatchStatus;
import seominkim.puppyAlert.domain.food.repository.FoodRepository;
import seominkim.puppyAlert.domain.host.dto.request.AddFoodRequest;
import seominkim.puppyAlert.domain.host.dto.response.AddFoodResponse;
import seominkim.puppyAlert.domain.menu.entity.Menu;
import seominkim.puppyAlert.domain.menu.service.MenuService;
import seominkim.puppyAlert.domain.puppy.dto.response.MatchResponse;
import seominkim.puppyAlert.domain.user.dto.request.CancelFoodRequest;
import seominkim.puppyAlert.domain.user.dto.response.CancelFoodResponse;
import seominkim.puppyAlert.domain.user.entity.User;
import seominkim.puppyAlert.global.entity.UserType;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.FoodException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertThrows;

// test end
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {
    @InjectMocks
    FoodService foodService;

    @Mock
    MenuService menuService;

    @Mock
    FoodRepository foodRepository;

    private Food food;
    private User host, puppy;
    private Menu menu;
    private String menuName = "치즈돈까스";

    private Long foodId = 7L;

    @BeforeEach
    private void testSetUp(){
        host = User.builder()
                .nickName("mbappe")
                .userType(UserType.HOST)
                .build();

        puppy = User.builder()
                .userType(UserType.PUPPY)
                .id("neymar")
                .build();

        food = Food.builder()
                .foodId(foodId)
                .host(host)
                .puppy(puppy)
                .build();

        menu = Menu.builder()
                .menuName(menuName)
                .build();
    }

    @Test
    @Disabled
    @DisplayName("Host 집밥 추가 성공")
    public void addNewFoodSuccess(){
        // given
        food.changePuppy(null);

        AddFoodRequest request = addFoodRequest();

        given(menuService.getMenu(menuName)).willReturn(menu);
        given(foodRepository.save(any(Food.class))).willReturn(food);
        // foodRepository.save()할때 return 값이 없고 동적으로 id 생성해주는 역할이라 given으로 안됨!
        // foodRepository가 save할때 자동으로 id를 만들어주는 것을 mockito에서는 아래와 같이 구현함
//        given(foodRepository.save(any(Food.class))).willAnswer(invocation -> {
//            Food savedFood = invocation.getArgument(0); // save()에 전달된 Food 객체를 가져옴
//            // Mock 생성 로직으로 ID를 직접 설정하거나, ID를 동적으로 생성하는 방식
//            if (savedFood.getFoodId() == null) {
//                savedFood = Food.builder()
//                        .foodId(foodId) // Mock ID 설정
//                        .host(savedFood.getHost())
//                        .puppy(savedFood.getPuppy())
//                        .menu(savedFood.getMenu())
//                        .build();
//            }
//            //savedFood.setFoodId(7L);  // 원래 여기서 ID를 동적으로 설정되게 mock해줬는데 setter가 없어서 이제 이게 안됨
//            return savedFood;  // 수정된 객체를 반환
//        });

        // when
        AddFoodResponse returnedResponse = foodService.handleAddFoodRequest(host, request);

        // then
        Assertions.assertThat(returnedResponse.foodId()).isEqualTo(7L);
    }

    @Test
    @DisplayName("Host 집밥 취소 성공")
    public void cancelFoodByHostSuccess(){
        // given
        CancelFoodRequest request = cancelFoodRequest();

        given(foodRepository.findById(7L)).willReturn(Optional.of(food));

        // when
        CancelFoodResponse response = foodService.handleCancelFoodRequest(host, cancelFoodRequest());

        // then
        verify(foodRepository).delete(food);
        Assertions.assertThat(response.foodId()).isEqualTo(food.getFoodId());
    }

    @Test
    @DisplayName("Puppy 집밥 취소 성공")
    public void cancelFoodByPuppySuccess(){
        // given
        food.changeMatchStatus(MatchStatus.MATCHED);

        CancelFoodRequest request = cancelFoodRequest();

        given(foodRepository.findById(7L)).willReturn(Optional.of(food));

        // when
        CancelFoodResponse response = foodService.handleCancelFoodRequest(puppy,cancelFoodRequest());

        // then
        Assertions.assertThat(food.getPuppy()).isNull();
        Assertions.assertThat(food.getMatchStatus()).isEqualTo(MatchStatus.READY);
        Assertions.assertThat(response.foodId()).isEqualTo(foodId);
    }

    @Test
    @DisplayName("Puppy 집밥 매칭 성공")
    public void handleMatchRequestSuccess(){
        // given
        food.changeMatchStatus(MatchStatus.READY);

        given(foodRepository.findById(foodId)).willReturn(Optional.of(food));

        // when
        MatchResponse response = foodService.handleMatchRequest(foodId, puppy);

        // then
        Assertions.assertThat(food.getPuppy().getId()).isEqualTo(puppy.getId());
        Assertions.assertThat(food.getMatchStatus()).isEqualTo(MatchStatus.MATCHED);
        Assertions.assertThat(response.foodId()).isEqualTo(foodId);
    }

    @Test
    @DisplayName("Puppy 집밥 완료 성공")
    public void handleEndMatchRequestSuccess(){
        // given
        food.changeMatchStatus(MatchStatus.MATCHED);

        given(foodRepository.findById(foodId)).willReturn(Optional.of(food));

        // when
        Long returnedId = foodService.handleEndMatchRequest(foodId);

        // then
        verify(foodRepository, never()).delete(any(Food.class)); // delete 호출안되는거 확인
        Assertions.assertThat(food.getMatchStatus()).isEqualTo(MatchStatus.COMPLETE);
        Assertions.assertThat(returnedId).isEqualTo(foodId);
    }

    @Test
    @DisplayName("Puppy 집밥 완료 실패")
    public void handleEndMatchRequestFail(){
        // given
        food.changeMatchStatus(MatchStatus.READY);
        // food.setMatchStatus(MatchStatus.COMPLETE); 이미 완료된 집밥이어도 에러터져야함

        given(foodRepository.findById(foodId)).willReturn(Optional.of(food));

        // when & then
        FoodException exception = assertThrows(FoodException.class, () -> {
            foodService.handleEndMatchRequest(foodId);
        });

        // 에러코드 검증
        Assertions.assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.NOT_MATCHED_FOOD);
    }

    private AddFoodRequest addFoodRequest(){
        return new AddFoodRequest(
          "ronaldo",
                menuName,
                LocalDateTime.now()
        );
    }

    private CancelFoodRequest cancelFoodRequest(){
        return new CancelFoodRequest(
                foodId,
                "vini"
        );
    }

}