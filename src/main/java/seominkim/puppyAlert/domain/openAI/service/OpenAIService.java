package seominkim.puppyAlert.domain.openAI.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import seominkim.puppyAlert.domain.openAI.constant.Prompt;
import seominkim.puppyAlert.domain.openAI.dto.request.RecommendFoodRequest;
import seominkim.puppyAlert.domain.openAI.dto.response.RecipeResponse;
import seominkim.puppyAlert.domain.openAI.dto.response.RecommendFoodResponse;
import seominkim.puppyAlert.domain.openAI.utility.OpenAIContentParser;
import seominkim.puppyAlert.domain.openAI.utility.OpenAIRequestBuilder;
import seominkim.puppyAlert.global.utility.ImageCrawler;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class OpenAIService {
    private final RestTemplate restTemplate;
    private final OpenAIContentParser openAIContentParser;
    private final OpenAIRequestBuilder openAIRequestBuilder;
    private final ImageCrawler imageCrawler;

    String url = "https://api.openai.com/v1/chat/completions";

    private ResponseEntity<String> sendRequest(String url, HttpEntity<Map<String, Object>> request){
        try{
            return restTemplate.postForEntity(url, request, String.class);
        }catch(RestClientException e){
            throw new RestClientException("OpenAI API 호출 중 오류가 발생했습니다", e);
        }
    }

    public String checkResponseSpec(){
        HttpEntity<Map<String, Object>> request = openAIRequestBuilder.getOpenAIRequest(Prompt.CHECK_RESPONSE_SPEC.getPrompt());

        ResponseEntity<String> response = sendRequest(url, request);

        return response.getBody();
    }

    public String checkIfMenu(String menuName){
        String prompt = String.format(Prompt.CHECK_MENU.getPrompt(), menuName);

        HttpEntity<Map<String, Object>> request = openAIRequestBuilder.getOpenAIRequest(prompt);

        ResponseEntity<String> response = sendRequest(url, request);

        return openAIContentParser.parseContent(response.getBody());
    }

    public List<RecommendFoodResponse> getRecommendedFoods(RecommendFoodRequest recommendFoodRequest){
        String categoryType = recommendFoodRequest.categoryType();
        String ingredients = recommendFoodRequest.ingredients();

        String prompt = String.format(Prompt.GET_AVAILABLE_ZIPBOB.getPrompt(),categoryType, ingredients);

        HttpEntity<Map<String, Object>> request = openAIRequestBuilder.getOpenAIRequest(prompt);

        ResponseEntity<String> response = sendRequest(url, request);

        String body = openAIContentParser.parseContent(response.getBody());

        List<String> recommendedFoods = Arrays.stream(body.split("\n"))
                .map(line -> line.replaceAll("\\d+\\. ", "").trim())
                .collect(Collectors.toList());

        return IntStream.range(0, 3)
                .mapToObj(i -> {
                    String curMenu = recommendedFoods.get(i);
                    return new RecommendFoodResponse(
                            curMenu,
                            imageCrawler.getImageURLByKakaoAPI(curMenu),
                            getRecipeDifficulty(curMenu),
                            getMenuDescription(curMenu)
                    );
                })
                .collect(Collectors.toList());
    }

    private String getRecipeDifficulty(String menuName){
        Random random = new Random();
        int randomNumber = random.nextInt(5) + 1;
        return String.valueOf(randomNumber);
    }

    private String getMenuDescription(String menuName){
        String prompt = String.format(Prompt.GET_RECIPE_DESCRIPTION.getPrompt(), menuName);

        HttpEntity<Map<String, Object>> request = openAIRequestBuilder.getOpenAIRequest(prompt);

        ResponseEntity<String> response = sendRequest(url, request);

        return openAIContentParser.parseContent(response.getBody());
    }

    public RecipeResponse getRecipe(String menuName){
        String prompt = String.format(Prompt.GET_RECIPE.getPrompt(), menuName);

        HttpEntity<Map<String, Object>> request = openAIRequestBuilder.getOpenAIRequest(prompt);

        ResponseEntity<String> response = sendRequest(url, request);

        // OpenAI response body 파싱
        String body = openAIContentParser.parseContent(response.getBody());

        List<String> recipeSteps = Arrays.stream(body.split("\n"))
                .map(line -> line.replaceAll("\\d+\\. ", "").trim())
                .limit(5)
                .collect(Collectors.toList());

        return new RecipeResponse(
                recipeSteps.get(0),
                recipeSteps.get(1),
                recipeSteps.get(2),
                recipeSteps.get(3),
                recipeSteps.get(4)
        );
    }
}
