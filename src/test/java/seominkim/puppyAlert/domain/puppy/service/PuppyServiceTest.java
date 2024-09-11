package seominkim.puppyAlert.domain.puppy.service;

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
import seominkim.puppyAlert.domain.favoriteHost.dto.response.FavoriteHostResponse;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.food.entity.MatchStatus;
import seominkim.puppyAlert.domain.food.service.FoodService;
import seominkim.puppyAlert.domain.puppy.dto.request.MatchRequest;
import seominkim.puppyAlert.domain.puppy.dto.response.MatchResponse;
import seominkim.puppyAlert.domain.user.entity.User;
import seominkim.puppyAlert.domain.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

// test end
@ExtendWith(MockitoExtension.class)
public class PuppyServiceTest {
    @InjectMocks
    private PuppyService puppyService;

    @Mock
    private FoodService foodService;

    @Mock
    private UserRepository userRepository;

    private User host;
    private User puppy;
    private Food food;
    private FavoriteHost favoriteHost;
    private MatchRequest request;
    private MatchResponse response;
    private FavoriteHostResponse favoriteHostResponse;

    @BeforeEach
    private void testSetUp(){
        host = new User();
        host.setId("kane");
        host.setNickName("harrykane");

        puppy = new User();
        puppy.setId("son");

        food = new Food();
        food.setFoodId(7L);
        food.setMatchStatus(MatchStatus.READY);
        food.setHost(host);

        favoriteHost = new FavoriteHost();
        favoriteHost.setPuppy(puppy);
        favoriteHost.setHost(host);

        puppy.getFavoriteHostList().add(favoriteHost);

        request = matchRequest();
        response = matchResponse();
        favoriteHostResponse = favoriteHostResponse();
    }

    @Test
    @DisplayName("집밥 신청 성공")
    public void handleMatchRequestTest(){
        // given
        given(userRepository.findById("son")).willReturn(Optional.of(puppy));
        given(foodService.handleMatchRequest(7L, puppy)).willReturn(response);
        // foodService 에서 foodRepository를 호출하기 때문에
        // foodService에 stub를 이미 적용했기 때문에 foodRepository에게 stub를 해줄 필요가 없다
        //given(foodRepository.findById(7L)).willReturn(Optional.of(food));

        // when
        MatchResponse returnedResponse = puppyService.handleMatchRequest(request);

        // then
        Assertions.assertThat(returnedResponse.foodId()).isEqualTo(request.foodId());
    }

    @Test
    @DisplayName("관심 호스트 조회")
    public void getFavoriteHostTest(){
        // given
        given(userRepository.findById("son")).willReturn(Optional.of(puppy));
        given(foodService.getMostRecentFood("son","kane")).willReturn(food);

        // when
        List<FavoriteHostResponse> responseList = puppyService.getFavoriteHost("son");

        // then
        Assertions.assertThat(responseList).hasSize(0);
        Assertions.assertThat(responseList.contains(favoriteHostResponse));
    }

    private MatchRequest matchRequest(){
        return new MatchRequest(
          7L,
          "son"
        );
    }

    private MatchResponse matchResponse(){
        return new MatchResponse(
          7L,
          "harrykane",
                "son"
        );
    }

    private FavoriteHostResponse favoriteHostResponse(){
        return new FavoriteHostResponse(
                "kane",
                "harrykane",
                "www",
                LocalDateTime.now()
        );
    }
}
