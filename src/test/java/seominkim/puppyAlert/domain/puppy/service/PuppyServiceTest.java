package seominkim.puppyAlert.domain.puppy.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seominkim.puppyAlert.domain.favoriteHost.dto.response.FavoriteHostResponse;
import seominkim.puppyAlert.domain.favoriteHost.entity.FavoriteHost;
import seominkim.puppyAlert.domain.food.dto.response.FoodInfoResponse;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.food.entity.MatchStatus;
import seominkim.puppyAlert.domain.food.service.FoodService;
import seominkim.puppyAlert.domain.puppy.dto.request.MatchRequest;
import seominkim.puppyAlert.domain.puppy.dto.response.MatchResponse;
import seominkim.puppyAlert.domain.user.entity.User;
import seominkim.puppyAlert.domain.user.repository.UserRepository;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @DisplayName("신청 가능한 집밥 조회")
    public void getAvailableFoodSuccess(){
        // given
        given(userRepository.findById(puppy.getId())).willReturn(Optional.of(puppy));
        given(foodService.getAvailableFood(puppy)).willReturn(foodInfoResponseList());

        // when
        List<FoodInfoResponse> response = puppyService.getAvailableFood(puppy.getId());

        // then
        Assertions.assertThat(response).hasSize(2);
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
        Assertions.assertThat(responseList).hasSize(1);
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

    private List<FoodInfoResponse> foodInfoResponseList(){
        List<FoodInfoResponse> returnList = new ArrayList<>();

        returnList.add(new FoodInfoResponse(
                11L,
                "kane",
                "harrykane",
                true,
                "pizza",
                "abc",
                LocalDateTime.now(),
                "england",
                "london",
                new Location(1.3,3.5),
                MatchStatus.READY
        ));

        returnList.add(new FoodInfoResponse(
                12L,
                "phil",
                "foden",
                true,
                "hamburger",
                "abc",
                LocalDateTime.now(),
                "england",
                "mancity",
                new Location(2.5,1.7),
                MatchStatus.COMPLETE
        ));

        return returnList;
    }
}
