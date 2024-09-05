package seominkim.puppyAlert.domain.host.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.food.dto.request.FoodRequest;
import seominkim.puppyAlert.domain.food.dto.response.AddFoodResponse;
import seominkim.puppyAlert.domain.host.service.HostService;
import seominkim.puppyAlert.domain.openai.service.OpenaiService;
import seominkim.puppyAlert.global.dto.response.MatchHistoryResponse;
import seominkim.puppyAlert.domain.user.dto.response.UserInfoResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/host")
@Tag(name = "Host API", description = "정보 조회, 집밥 등록, 기록 조회")
public class HostController {

    private final HostService hostService;
    private final OpenaiService openaiService;

    @Operation(summary = "전체 조회 (관리자용)")
    @GetMapping("/all")
    public List<UserInfoResponse> findAll() {
        return hostService.findAll();
    }

    @Operation(summary = "집밥 등록")
    @PostMapping("/food")
    public AddFoodResponse addFood(@RequestBody FoodRequest foodRequest){
        return hostService.addFood(foodRequest);
    }

    @Operation(summary = "집밥 취소")
    @DeleteMapping("/food")
    public void CancelFood(@RequestBody FoodRequest foodRequest){ hostService.deleteFood(foodRequest); }

    @Operation(summary = "집밥 기록 조회")
    @GetMapping("/history")
    public List<MatchHistoryResponse> getHostHistory(@RequestParam String hostId){ return hostService.getHistory(hostId); }

    @Operation(summary = "집밥 추천받기")
    @PostMapping("/recommend")
    public String getRecommend(@RequestParam String prompt){
        return openaiService.getRecommendedFoods(prompt);
    }
}