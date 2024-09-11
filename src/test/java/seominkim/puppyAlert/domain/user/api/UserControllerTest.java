package seominkim.puppyAlert.domain.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import seominkim.puppyAlert.config.WebMvcTestConfig;
import seominkim.puppyAlert.domain.user.dto.request.LoginRequest;
import seominkim.puppyAlert.domain.user.dto.request.SignUpRequest;
import seominkim.puppyAlert.domain.user.dto.response.LoginResponse;
import seominkim.puppyAlert.domain.user.dto.response.SignUpResponse;
import seominkim.puppyAlert.domain.user.service.UserService;
import seominkim.puppyAlert.global.entity.Location;
import seominkim.puppyAlert.global.entity.UserType;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Ignore
@Disabled
// test end
@WebMvcTest(UserController.class) // UserController 만 사용해서 WebMvcTest 진행
@Import(WebMvcTestConfig.class)
public class UserControllerTest {
    // 컨트롤러를 테스트하기 위해서는 HTTP 호출이 필요함
    // 일반적인 방법으로는 호출이 불가능하므로 스프링에서는 MockMvc를 제공함
    // MVC 를 테스트하기 위한 도구로 실제 HTTP 요청을 보내지 않고도 컨트롤러의 동작을 테스트할 수 있음
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // @WebMvcTest 는 @Controller 빈들만 자동으로 로드함
    // 따라서 @Service @Repository 빈들은 로드하지 않으므로 모킹 객체를 주입해줘야함!
    // UserController 는 원래 UserService 의존성이 필요하기 때문에 @MockBean으로 가짜 객체 생성해서 주입해주기
    @MockBean
    private UserService userService;

    @Test
    @DisplayName("로그인 성공")
    void loginSuccessTest() throws Exception {
        // given
        LoginResponse response = loginResponse();

        // stub
        when(userService.login(any(LoginRequest.class))).thenReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/user/login")
                        .content(asJsonString(new LoginRequest("werner","1234")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("nickName").exists());
                //.andExpect(jsonPath("nickName").value("werner"));
    }

//    @Test
//    @DisplayName("회원 가입 성공")
//    void signUpSuccess() throws Exception{
//        // given
//        String requestBody = signUpRequest();
//        SignUpResponse response = signUpResponse();
//
//        // stub
//        when(userService.signUp(any(SignUpRequest.class))).thenReturn(response);
//
//        // when
//        ResultActions resultActions = mockMvc.perform(
//                MockMvcRequestBuilders.post("/api/v1/user/signup")
//                        .content(requestBody)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("id").exists());
//    }

    private LoginResponse loginResponse(){
        return new LoginResponse(
                "string",
                UserType.PUPPY
        );
    }

    private String signUpRequest() {
        return "{"
                + "\"id\":\"havertz\","
                + "\"password\":\"29\","
                + "\"nickName\":\"하베르츠\","
                + "\"name\":\"kai\","
                + "\"birth\":\"2024-08-29\","
                + "\"address\":\"Germany\","
                + "\"detailAddress\":\"Berlin\","
                + "\"location\":{"
                + "\"latitude\":10L,"
                + "\"longitude\":13L"
                + "},"
                + "\"phoneNumber\":\"0\","
                + "\"userType\":\"HOST\""
                + "}";
    }

    private SignUpResponse signUpResponse(){
        return new SignUpResponse(
                "string",
                UserType.HOST
        );
    }

    private String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
