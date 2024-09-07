package seominkim.puppyAlert.domain.openAI.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.openAI.dto.request.RecommendFoodRequest;
import seominkim.puppyAlert.domain.openAI.dto.response.RecipeResponse;
import seominkim.puppyAlert.domain.openAI.dto.response.RecommendFoodResponse;
import seominkim.puppyAlert.domain.openAI.service.OpenAIService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/openai")
@Tag(name = "OpenAI API", description = "음식 유무 확인, 집밥 추천 받기, 집밥 레시피 받기")
public class OpenAIController {
    private final OpenAIService openaiService;

    @Operation(summary = "API 스펙 확인용")
    @GetMapping("")
    public String checkSpec(){
        return openaiService.checkResponseSpec();
    }

    @Operation(summary = "메뉴 존재 확인")
    @GetMapping("/checkMenu")
    public String checkMenu(@RequestParam String menuName){
        return openaiService.checkIfFood(menuName);
    }


    @Operation(summary = "추천 집밥 조회")
    @PostMapping("/recommend")
    public List<RecommendFoodResponse> getRecommend(@RequestBody RecommendFoodRequest recommendFoodRequest){
        return openaiService.getRecommendedFoods(recommendFoodRequest);
    }

    @Operation(summary="레시피 조회")
    @GetMapping("/recipe")
    public RecipeResponse getRecipe(@RequestParam String menuName){
        return openaiService.getRecipe(menuName);
    }
}
