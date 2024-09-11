package seominkim.puppyAlert.domain.host.service;

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
import seominkim.puppyAlert.domain.food.service.FoodService;
import seominkim.puppyAlert.domain.host.dto.request.AddFoodRequest;
import seominkim.puppyAlert.domain.host.dto.response.AddFoodResponse;
import seominkim.puppyAlert.domain.user.entity.User;
import seominkim.puppyAlert.domain.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

// test할 중요한게 없음
@Ignore
@Disabled
@ExtendWith(MockitoExtension.class)
public class HostServiceTest {
    @InjectMocks
    private HostService hostService;

    @Mock
    private FoodService foodService;

    @Mock
    private UserRepository userRepository;

    private User host;
    private AddFoodRequest request;
    private AddFoodResponse response;

    @BeforeEach
    private void testSetUp(){
        host = new User();
        host.setId("mbappe");

        request = addFoodRequest();
        response = addFoodResponse();
    }

    @Test
    @DisplayName("집밥 등록")
    void addFood(){
        // given
        given(userRepository.findById("mbappe")).willReturn(Optional.of(host));
        given(foodService.addNewFood(host, request)).willReturn(response);

        // when
        AddFoodResponse returnedResponse = hostService.handleAddFoodRequest(request);

        // then
        Assertions.assertThat(returnedResponse).isNotNull();
        Assertions.assertThat(returnedResponse.foodId()).isEqualTo(10L);
    }

    private AddFoodRequest addFoodRequest(){
        return new AddFoodRequest(
                "mbappe",
                "김치볶음밥",
                LocalDateTime.now()
        );
    }

    private AddFoodResponse addFoodResponse(){
        return new AddFoodResponse(
          10L,
          "www.abc.com"
        );
    }
}
