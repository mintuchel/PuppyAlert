package seominkim.puppyAlert.domain.food.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.food.dto.FoodRequestDTO;
import seominkim.puppyAlert.domain.food.service.FoodService;
import seominkim.puppyAlert.global.dto.MatchHistoryResponseDTO;

import java.util.List;

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

    /*
    @Operation(summary ="Puppy 가 식사가능한 집밥 조회")
    @GetMapping("/available/{puppyId}")
    public ResponseEntity findAvailable(@PathVariable String puppyId){
        return ResponseEntity.ok(foodService.findAvailable(puppyId));
    }
    */

    @Operation(summary = "특정 집밥 정보 조회")
    @GetMapping("/{foodId}")
    public ResponseEntity findOne(@PathVariable long foodId){
        return ResponseEntity.ok(foodService.findById(foodId));
    }

    @Operation(summary = "Host의 집밥등록")
    @PostMapping()
    public ResponseEntity add(@RequestBody FoodRequestDTO foodRequestDTO){
        Long foodId = foodService.add(foodRequestDTO);
        return ResponseEntity.ok(foodId);
    }

    @Operation(summary = "Host 집밥 기록 조회")
    @GetMapping("/hostHistory")
    public ResponseEntity getHostHistory(@RequestBody String hostId){
        return ResponseEntity.ok(foodService.findHostHistory(hostId));
    }

    @Operation(summary = "Puppy 집밥 기록 조회")
    @GetMapping("/puppyHistory")
    public ResponseEntity<List<MatchHistoryResponseDTO>> getPuppyHistory(@RequestBody String puppyId){
        return ResponseEntity.ok(foodService.findPuppyHistory(puppyId));
    }
}