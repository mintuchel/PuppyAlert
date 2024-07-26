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

    @Operation(summary = "모든 집밥 조회")
    @GetMapping("/all")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(foodService.findAll());
    }

    @Operation(summary ="특정 Puppy 가 식사가능한 집밥 조회")
    @GetMapping("/available/{puppyId}")
    public ResponseEntity findAvailable(@PathVariable String puppyId){
        return ResponseEntity.ok(foodService.findAvailable(puppyId));
    }

    @Operation(summary = "특정 집밥 정보 조회")
    @GetMapping("/{foodId}")
    public ResponseEntity findOne(@PathVariable long foodId){
        return ResponseEntity.ok(foodService.findById(foodId));
    }

    @Operation(summary = "Host의 집밥등록")
    @PostMapping()
    public ResponseEntity add(@RequestBody FoodRequest foodRequest){
        Long foodId = foodService.add(foodRequest);
        return ResponseEntity.ok(foodId);
    }
}