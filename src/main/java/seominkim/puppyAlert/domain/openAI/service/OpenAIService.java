package seominkim.puppyAlert.domain.openAI.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import seominkim.puppyAlert.domain.openAI.constant.Prompt;
import seominkim.puppyAlert.domain.openAI.dto.request.RecommendFoodRequest;
import seominkim.puppyAlert.domain.openAI.dto.response.RecommendFoodResponse;
import seominkim.puppyAlert.domain.openAI.utility.OpenAIContentParser;
import seominkim.puppyAlert.domain.openAI.utility.OpenAIRequestBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenAIService {
    private final RestTemplate restTemplate;
    private final OpenAIContentParser openAIContentParser;
    private final OpenAIRequestBuilder openAIRequestBuilder;

    String url = "https://api.openai.com/v1/chat/completions";

    private ResponseEntity<String> sendRequest(String url, HttpEntity<Map<String, Object>> request){
        try{
            return restTemplate.postForEntity(url, request, String.class);
        }catch(RestClientException e){
            throw new RestClientException("OpenAI API 호출 중 오류가 발생했습니다", e);
        }
    }

    // 이거 언제 쓰는거임???
    private Map<String, Object> getResponseFormat(){
        Map<String, Object> responseFormat = new HashMap<>();
        responseFormat.put("type","json_object");
        return responseFormat;
    }

    public String checkResponseSpec(){
        HttpEntity<Map<String, Object>> request = openAIRequestBuilder.getOpenAIRequest(Prompt.CHECK_RESPONSE_SPEC);

        ResponseEntity<String> response = sendRequest(url, request);

        return response.getBody();
    }

    public String checkIfFood(String menuName){
        HttpEntity<Map<String, Object>> request = openAIRequestBuilder.getOpenAIRequest(Prompt.CHECK_MENU);

        ResponseEntity<String> response = sendRequest(url, request);

        return openAIContentParser.parseContent(response.getBody());
    }

    public RecommendFoodResponse getRecommendedFoods(RecommendFoodRequest recommendFoodRequest){
        HttpEntity<Map<String, Object>> request = openAIRequestBuilder.getOpenAIRequest(Prompt.GET_AVAILABLE_ZIPBOB);

        ResponseEntity<String> response = sendRequest(url, request);

        String body = openAIContentParser.parseContent(response.getBody());

        // body parsing 해서 front 한테 보내주기
        String[] lines = body.split("\n");
        String[] recommendedFoods = new String[lines.length];
        for (int i = 0; i < lines.length; i++) {
            recommendedFoods[i] = lines[i].replaceAll("\\d+\\. ", "");
        }

        return new RecommendFoodResponse(recommendedFoods[0],recommendedFoods[1],recommendedFoods[2]);
    }
}
