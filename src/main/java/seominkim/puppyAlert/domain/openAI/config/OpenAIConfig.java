package seominkim.puppyAlert.domain.openAI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// @Component 는 내가 제어할 수 있는 클래스들. 내가 만든 커스텀 클래스들
// @Bean 은 외부 라이브러리를 사용해야할때. 아래와 같은 경우
@Configuration
public class OpenAIConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
