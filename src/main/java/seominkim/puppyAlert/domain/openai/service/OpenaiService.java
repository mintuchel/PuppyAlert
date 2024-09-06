package seominkim.puppyAlert.domain.openai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenaiService {
    // 1. HTTP METHOD 지원
    // 2. API에 REQUEST 보내고 RESPONSE 받을 수 있음
    // 3. MESSAGE CONVERTER로 HTTP MESSAGE를 JAVA 객체로 변환해줌

    // SPRING BOOT 어플리케이션이 외부 RESTFUL API랑 상호작용해야할때 사용
    // 다른 웹 서비스에서 데이터 가져와야할때 사용
    private final RestTemplate restTemplate;

    private String OPEN_AI_KEY = "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";

    String url = "https://api.openai.com/v1/chat/completions";

    //============================= OPENAI REQUEST RESPONSE METHODS =============================//

    // REQUEST HEADER 설정
    private HttpHeaders setHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(OPEN_AI_KEY);

        return headers;
    }

    // REQUEST BODY MESSAGE 설정
    private Map<String,Object> setRequestBody(String prompt){
        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("model","gpt-3.5-turbo");

        List<Map<String,String>> messages = setMessages(prompt);
        requestBody.put("messages",messages);

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

    private ResponseEntity<String> sendRequest(String url, HttpEntity<Map<String, Object>> request){
        try{
            return restTemplate.postForEntity(url, request, String.class);
        }catch(RestClientException e){
            throw new RestClientException("OpenAI API 호출 중 오류가 발생했습니다", e);
        }
    }

    private Map<String, Object> getResponseFormat(){
        Map<String, Object> responseFormat = new HashMap<>();
        responseFormat.put("type","json_object");
        return responseFormat;
    }

    //============================= OPENAI API 보내기 =============================//

    public String getRecommendedFoods(String prompt){
        // HTTP MESSAGE 보내기 전 구성 설정
        HttpHeaders headers = setHeader();
        Map<String, Object> requestBody = setRequestBody(prompt);

        // HTTP REQUEST 생성하기
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        // HTTP RESPONSE를 RESPONSEENTITY로 받기 <- HTTP REQUEST 보내고
        ResponseEntity<String> response = sendRequest(url, request);

        return response.getBody();
    }
}
