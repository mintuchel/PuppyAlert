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
import seominkim.puppyAlert.domain.openai.constant.Prompt;
import seominkim.puppyAlert.domain.openai.dto.request.RecommendFoodRequest;
import seominkim.puppyAlert.domain.openai.dto.response.RecommendFoodResponse;
import seominkim.puppyAlert.domain.openai.utility.Parser;

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

    // properties에서 API 키를 주입
    @Value("${OPENAI_KEY}")
    private String OPENAI_KEY;

    String url = "https://api.openai.com/v1/chat/completions";

    //============================= OPENAI REQUEST RESPONSE METHODS =============================//

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

    public String checkResponseSpec(){
        // HTTP MESSAGE 보내기 전 구성 설정
        HttpHeaders headers = setHeader();
        Map<String, Object> requestBody = setRequestBody("한식 중 돼지고기,양파,파 재료들로 만들 수 있는 메뉴 3가지만 추천해줘. 메뉴 이름만 알려줘. 딱 메뉴이름 3개만 답변으로 주는거야");

        // HTTP REQUEST 생성하기
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        // HTTP RESPONSE를 RESPONSEENTITY로 받기 <- HTTP REQUEST 보내고
        ResponseEntity<String> response = sendRequest(url, request);

        return response.getBody();
    }

    public String checkIfFood(String menuName){
        // HTTP MESSAGE 보내기 전 구성 설정
        HttpHeaders headers = setHeader();
        String prompt = String.format(Prompt.CHECK_MENU.getPrompt(), menuName);
        Map<String, Object> requestBody = setRequestBody(prompt);

        // HTTP REQUEST 생성하기
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        // HTTP RESPONSE를 RESPONSEENTITY로 받기 <- HTTP REQUEST 보내고
        ResponseEntity<String> response = sendRequest(url, request);

        return Parser.parseContent(response.getBody());
    }

    public RecommendFoodResponse getRecommendedFoods(RecommendFoodRequest recommendFoodRequest){
        // HTTP MESSAGE 보내기 전 구성 설정
        HttpHeaders headers = setHeader();

        String prompt;
        prompt = String.format(Prompt.GET_AVAILABLE_ZIPBOB.getPrompt(), recommendFoodRequest.categoryType(), recommendFoodRequest.ingredients());
        Map<String, Object> requestBody = setRequestBody(prompt);

        // HTTP REQUEST 생성하기
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        // HTTP RESPONSE를 RESPONSEENTITY로 받기 <- HTTP REQUEST 보내고
        ResponseEntity<String> response = sendRequest(url, request);

        String body = Parser.parseContent(response.getBody());

        String[] lines = body.split("\n");

        String[] foodItems = new String[lines.length];
        for (int i = 0; i < lines.length; i++) {
            foodItems[i] = lines[i].replaceAll("\\d+\\. ", "");
        }

        return new RecommendFoodResponse(foodItems[0],foodItems[1],foodItems[2]);
    }
}
