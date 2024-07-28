package seominkim.puppyAlert.domain.food.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.food.dto.FoodResponse;
import seominkim.puppyAlert.domain.food.service.FoodService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/food")
@Tag(name = "Food API")
public class FoodController {

    private final FoodService foodService;

    @Operation(summary = "단건 조회 (관리자용)")
    @GetMapping()
    public FoodResponse findOne(@RequestParam long foodId){ return foodService.findById(foodId); }

    @Operation(summary = "전체 조회 (관리자용)")
    @GetMapping("/all")
    public List<FoodResponse> findAll(){ return foodService.findAll(); }
}