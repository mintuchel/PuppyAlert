package seominkim.puppyAlert.domain.openAI.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OpenAIRequestBuilder {

    // properties에서 API 키를 주입
    @Value("${OPENAI_KEY}")
    private String OPENAI_KEY;

    // OpenAI API 로 쏠 HTTP REQUEST HEADER BODY 세팅해서 만들어줌
    public HttpEntity<Map<String, Object>> getOpenAIRequest(String prompt){
        HttpHeaders headers = setHeader();
        Map<String, Object> requestBody = setRequestBody(prompt);
        return new HttpEntity<>(requestBody, headers);
    }

    // REQUEST HEADER 설정
    private HttpHeaders setHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(OPENAI_KEY);

        return headers;
    }

    // REQUEST BODY MESSAGE 설정
    private Map<String,Object> setRequestBody(String prompt){
        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("model","gpt-4o-mini");

        List<Map<String,String>> messages = setMessages(prompt);
        requestBody.put("messages",messages);

        requestBody.put("temperature", 0.3);

        return requestBody;
    }

    private List<Map<String,String>> setMessages(String prompt){
        List<Map<String,String>> messages = new ArrayList<>();

        Map<String, String> systemMessage = new HashMap<>();
        systemMessage.put("role","system");
        systemMessage.put("content","You are helpful assistant");
        messages.add(systemMessage);

        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role","user");
        userMessage.put("content", prompt);
        messages.add(userMessage);

        return messages;
    }

}
