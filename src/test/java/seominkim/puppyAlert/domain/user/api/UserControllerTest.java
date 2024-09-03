package seominkim.puppyAlert.domain.user.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import seominkim.puppyAlert.domain.user.dto.request.LoginRequest;
import seominkim.puppyAlert.domain.user.dto.request.SignUpRequest;
import seominkim.puppyAlert.domain.user.dto.response.LoginResponse;
import seominkim.puppyAlert.domain.user.dto.response.SignUpResponse;
import seominkim.puppyAlert.domain.user.service.UserService;
import seominkim.puppyAlert.global.entity.UserType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// UserController 만 사용해서 WebMvcTest 진행
@WebMvcTest(UserController.class)
public class UserControllerTest {
    // 컨트롤러를 테스트하기 위해서는 HTTP 호출이 필요함
    // 일반적인 방법으로는 호출이 불가능하므로 스프링에서는 MockMVC를 제공함
    // MVC 를 테스트하기 위한 도구로 실제 HTTP 요청을 보내지 않고도 컨트롤러의 동작을 테스트할 수 있음
    @Autowired
    private MockMvc mockMvc;

    // @WebMvcTest 는 @Controller 빈들만 자동으로 로드함
    // 따라서 @Service @Repository 빈들은 로드하지 않으므로 모킹 객체를 주입해줘야함!
    // UserController 는 원래 UserService 의존성이 필요하기 때문에
    // @MockBean으로 가짜 객체 생성해서 주입해주기
    @MockBean
    private UserService userService;

    @Test
    @DisplayName("로그인 성공")
    void loginSuccessTest() throws Exception {
        // given
        String loginRequest = loginRequest();
        LoginResponse response = loginResponse();

        // stub
        when(userService.login(any(LoginRequest.class))).thenReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/user/login")
                        .content(loginRequest)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("nickName").value(response.nickName()));
    }

    @DisplayName("회원 가입 성공")
    @Test
    void signUpSuccess() throws Exception{
        // given
        String requestBody = signUpRequest();
        SignUpResponse response = signUpResponse();

        // stub
        when(userService.signUp(any(SignUpRequest.class))).thenReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/user/signup")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("id",response.id()).exists())
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

    private String signUpRequest() {
        return "{"
                + "\"id\":\"havertz\","
                + "\"password\":\"29\","
                + "\"nickName\":\"독일의차세대제로톱\","
                + "\"name\":\"Kai_Havertz\","
                + "\"birth\":\"2024-08-29\","
                + "\"address\":\"Germany\","
                + "\"detailAddress\":\"Berlin\","
                + "\"location\":{"
                + "\"latitude\":100.135135,"
                + "\"longitude\":135.12435"
                + "},"
                + "\"phoneNumber\":\"010-8457-1141\","
                + "\"userType\":\"HOST\""
                + "}";
    }


    private SignUpResponse signUpResponse(){
        return new SignUpResponse(
                "havertz",
                UserType.HOST
        );
    }
}
