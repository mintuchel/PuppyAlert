package seominkim.puppyAlert.domain.common.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import seominkim.puppyAlert.domain.common.dto.request.LoginRequest;
import seominkim.puppyAlert.domain.common.dto.response.LoginResponse;
import seominkim.puppyAlert.domain.common.service.CommonService;
import seominkim.puppyAlert.global.entity.UserType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommonController.class)
public class LoginTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CommonService commonService;

    @BeforeEach
    void testSetUp(){
        when(commonService.checkIfAccountExists(new LoginRequest("werner","11"))).thenReturn(new LoginResponse("turbotimo", UserType.PUPPY));
    }

    @Test
    @DisplayName("로그인 성공")
    void loginSuccess() throws Exception {
        String loginRequest = loginRequest();
        LoginResponse response = loginResponse();

        // stub
        doReturn(response).when(commonService)
                .checkIfAccountExists(any(LoginRequest.class));

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/common/login")
                        .content(loginRequest)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("nickName",response.nickName()).exists())
                .andExpect(jsonPath("userType",response.userType()).exists());
    }

    private String loginRequest() {
        return """
                {
                    "id": "werner",
                    "password": "11"
                }
               """;
    }

    private LoginResponse loginResponse(){
        return new LoginResponse(
                "turbotimo",
                UserType.PUPPY
        );
    }
}
