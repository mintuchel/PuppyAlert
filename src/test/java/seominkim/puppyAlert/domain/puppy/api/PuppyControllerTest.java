package seominkim.puppyAlert.domain.puppy.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import seominkim.puppyAlert.domain.puppy.dto.request.MatchRequest;
import seominkim.puppyAlert.domain.puppy.dto.response.MatchResponse;
import seominkim.puppyAlert.domain.puppy.service.PuppyService;
import seominkim.puppyAlert.domain.user.dto.response.UserInfoResponse;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PuppyController.class)
//@ActiveProfiles("test")
public class PuppyControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    PuppyService puppyService;

//    @Test
//    @DisplayName("Puppy 단건 조회")
//    void FindOne() throws Exception {
//        // given
//        String puppyId = "modric";
//        UserInfoResponse response = userInfoResponse();
//
//        given(puppyService.findById(puppyId)).willReturn(response);
//
//        // when
//        ResultActions resultActions = mockMvc.perform(
//                MockMvcRequestBuilders.get("/api/v1/puppy")
//                        .param("puppyId","modric")
//        );
//
//        // then
//        // HTTP 응답 상태 코드가 200인지 확인
//        // userId 를 추출해서 value 함수를 통해 puppyId와 동일한지 검증
//        resultActions.andExpect(status().isOk())
//                .andExpect(jsonPath("userId").value(puppyId));
//    }

    @Test
    @DisplayName("집밥 신청")
    void matchFood() throws Exception {
        // given
        String puppyId = "modric";
        String request = matchRequest();
        MatchResponse response = matchResponse();

        // MatchRequest 클래스이기만 하면 response 반환하게 stub
        given(puppyService.handleMatchRequest(any(MatchRequest.class))).willReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/puppy/food")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("puppyId").value(puppyId));
    }

    private UserInfoResponse userInfoResponse() {
        return new UserInfoResponse(
                "modric",
                "luka",
                "maestro",
                LocalDate.parse("2024-08-30"),
                "010-3020-1111",
                "real madrid",
                "Santiago Bernabeu",
                new Location(100.5, 50.1),
                "www.abc.com"
        );
    }

    private String matchRequest() {
        return """
                {
                    "foodId": 1,
                    "puppyId": "modric"
                }
               """;
    }


    private MatchResponse matchResponse(){
        return new MatchResponse(
                1L,
                "vini",
                "modric"
        );
    }
}
