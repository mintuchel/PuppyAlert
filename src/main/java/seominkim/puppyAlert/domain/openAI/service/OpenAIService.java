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

import java.util.Map;
import java.util.Random;

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

    public String checkResponseSpec(){
        HttpEntity<Map<String, Object>> request = openAIRequestBuilder.getOpenAIRequest(Prompt.CHECK_RESPONSE_SPEC.getPrompt());

        ResponseEntity<String> response = sendRequest(url, request);

        return response.getBody();
    }

    public String checkIfFood(String menuName){
        String prompt = String.format(Prompt.CHECK_MENU.getPrompt(), menuName);

        HttpEntity<Map<String, Object>> request = openAIRequestBuilder.getOpenAIRequest(prompt);

        ResponseEntity<String> response = sendRequest(url, request);

        return openAIContentParser.parseContent(response.getBody());
    }

    public RecommendFoodResponse getRecommendedFoods(RecommendFoodRequest recommendFoodRequest){
        String categoryType = recommendFoodRequest.categoryType();
        String ingredients = recommendFoodRequest.ingredients();

        String prompt = String.format(Prompt.GET_AVAILABLE_ZIPBOB.getPrompt(),categoryType, ingredients);

        HttpEntity<Map<String, Object>> request = openAIRequestBuilder.getOpenAIRequest(prompt);

        ResponseEntity<String> response = sendRequest(url, request);

        String body = openAIContentParser.parseContent(response.getBody());

        // body parsing 해서 front 한테 보내주기
        String[] lines = body.split("\n");
        String[] recommendedFoods = new String[lines.length];
        for (int i = 0; i < lines.length; i++) {
            recommendedFoods[i] = lines[i].replaceAll("\\d+\\. ", "");
        }

        return new RecommendFoodResponse(
                recommendedFoods[0],
                recommendedFoods[1],
                recommendedFoods[2]
        );
    }

    public String getRecipeDifficulty(String menuName){
//        String prompt = String.format(Prompt.GET_RECIPE_DIFFICULTY.getPrompt(), menuName);
//
//        HttpEntity<Map<String, Object>> request = openAIRequestBuilder.getOpenAIRequest(prompt);
//
//        ResponseEntity<String> response = sendRequest(url, request);
//
//        return openAIContentParser.parseContent(response.getBody());

        Random random = new Random();
        int randomNumber = random.nextInt(5) + 1;
        return String.valueOf(randomNumber);
    }

    public RecipeResponse getRecipe(String menuName){
        String prompt = String.format(Prompt.GET_RECIPE.getPrompt(), menuName);

        HttpEntity<Map<String, Object>> request = openAIRequestBuilder.getOpenAIRequest(prompt);

        ResponseEntity<String> response = sendRequest(url, request);

        String body = openAIContentParser.parseContent(response.getBody());

        // body parsing 해서 front 한테 보내주기
        String[] lines = body.split("\n");
        String[] recipeSteps = new String[lines.length];
        for (int i = 0; i < lines.length; i++) {
            recipeSteps[i] = lines[i].replaceAll("\\d+\\. ", "");
        }

        return new RecipeResponse(recipeSteps[0], recipeSteps[1], recipeSteps[2], recipeSteps[3], recipeSteps[4]);
    }
}
