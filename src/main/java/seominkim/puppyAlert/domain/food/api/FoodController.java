package seominkim.puppyAlert.domain.food.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.food.dto.FoodRequest;
import seominkim.puppyAlert.domain.food.service.FoodService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/food")
public class FoodController {

    private final FoodService foodService;

    @Operation(summary = "특정 집밥 정보 조회")
    @GetMapping()
    public ResponseEntity findOne(@RequestParam long foodId){
        return ResponseEntity.ok(foodService.findById(foodId));
    }

    @Operation(summary = "모든 집밥 조회 (관리자용)")
    @GetMapping("/all")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(foodService.findAll());
    }
}