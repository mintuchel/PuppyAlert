package seominkim.puppyAlert.domain.host.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HostServiceTest {
//    @InjectMocks
//    private HostService hostService;
//
//    @Mock
//    private FoodService foodService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    private User host;
//    private AddFoodRequest request;
//    private AddFoodResponse response;
//
//    @BeforeEach
//    private void testSetUp(){
//        host = new User();
//        host.setId("mbappe");
//
//        request = addFoodRequest();
//        response = addFoodResponse();
//    }
//
//    @Test
//    @DisplayName("집밥 등록")
//    void addFood(){
//        // given
//        given(userRepository.findById("mbappe")).willReturn(Optional.of(host));
//        given(foodService.addNewFood(host, request)).willReturn(response);
//
//        // when
//        AddFoodResponse returnedResponse = hostService.addFood(request);
//
//        // then
//        Assertions.assertThat(returnedResponse).isNotNull();
//        Assertions.assertThat(returnedResponse.foodId()).isEqualTo(10L);
//    }
//
//    private AddFoodRequest addFoodRequest(){
//        return new AddFoodRequest(
//                "mbappe",
//                "김치볶음밥",
//                LocalDateTime.now()
//        );
//    }
//
//    private AddFoodResponse addFoodResponse(){
//        return new AddFoodResponse(
//          10L,
//          "www.abc.com"
//        );
//    }
}
