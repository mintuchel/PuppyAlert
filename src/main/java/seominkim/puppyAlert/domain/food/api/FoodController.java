package seominkim.puppyAlert.domain.food.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.food.dto.FoodResponse;
import seominkim.puppyAlert.domain.food.service.FoodService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/food")
public class FoodController {

    private final FoodService foodService;

    @Operation(summary = "특정 집밥 정보 조회")
    @GetMapping()
    public FoodResponse findOne(@RequestParam long foodId){ return foodService.findById(foodId); }

    @Operation(summary = "전체 조회 (관리자용)")
    @GetMapping("/all")
    public List<FoodResponse> findAll(){ return foodService.findAll(); }
}