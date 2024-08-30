package seominkim.puppyAlert.domain.puppy.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.food.entity.FoodStatus;
import seominkim.puppyAlert.domain.food.service.FoodService;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.puppy.dto.request.MatchRequest;
import seominkim.puppyAlert.domain.puppy.dto.response.MatchResponse;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.puppy.repository.PuppyRepository;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PuppyServiceTest {
    @InjectMocks
    private PuppyService puppyService;

    @Mock
    private FoodService foodService;
    @Mock
    private PuppyRepository puppyRepository;


    private Host host;
    private Puppy puppy;
    private Food food;
    private MatchRequest request;
    private MatchResponse response;

    @BeforeEach
    private void testSetUp(){
        host = new Host();
        host.setHostId("kane");
        host.setNickName("harrykane");

        puppy = new Puppy();
        puppy.setPuppyId("son");

        food = new Food();
        food.setFoodId(7L);
        food.setStatus(FoodStatus.READY);
        food.setHost(host);

        request = matchRequest();
        response = matchResponse();
    }

    @Test
    @DisplayName("집밥 신청")
    public void handleMatchRequest(){
        // given
        given(puppyRepository.findById("son")).willReturn(Optional.of(puppy));

        given(foodService.handleMatchRequest(7L, puppy)).willReturn(response);
        // foodService 에서 foodRepository를 호출하기 때문에
        // foodService에 stub를 이미 적용했기 때문에 foodRepository에게 stub를 해줄 필요가 없다
        //given(foodRepository.findById(7L)).willReturn(Optional.of(food));

        // when
        MatchResponse returnedResponse = puppyService.handleMatchRequest(request);

        // then
        Assertions.assertThat(returnedResponse.foodId()).isEqualTo(matchRequest().foodId());
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
}
