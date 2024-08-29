package seominkim.puppyAlert.domain.common.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import seominkim.puppyAlert.domain.common.service.CommonService;

@WebMvcTest(CommonController.class)
public class LoginTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CommonService commonService;

    @Test
    @DisplayName("로그인 성공")
    void loginSuccess(){
        String requestBody = loginRequest();
    }

    private String loginRequest(){
        return "{"
                + "\"id\":\"havertz\","
                + "\"password\":\"29\","
                + "}";
    }
}
