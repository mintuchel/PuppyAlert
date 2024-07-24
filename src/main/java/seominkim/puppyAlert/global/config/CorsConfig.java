package seominkim.puppyAlert.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Configuration Annotation 을 통해 설정파일임을 명시
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry
                .addMapping("/**")
                .allowedHeaders("*")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("*");
    }
}