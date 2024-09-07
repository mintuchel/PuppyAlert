package seominkim.puppyAlert.domain.host.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.food.dto.request.AddFoodRequest;
import seominkim.puppyAlert.domain.food.dto.response.AddFoodResponse;
import seominkim.puppyAlert.domain.host.service.HostService;
import seominkim.puppyAlert.domain.user.dto.response.UserInfoResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/host")
@Tag(name = "Host API", description = "정보 조회, 집밥 등록, 기록 조회")
public class HostController {

    private final HostService hostService;

    @Operation(summary = "전체 조회 (관리자용)")
    @GetMapping("/all")
    public List<UserInfoResponse> findAll() {
        return hostService.findAll();
    }

    @Operation(summary = "집밥 등록")
    @PostMapping("/food")
    public AddFoodResponse addFood(@RequestBody AddFoodRequest addFoodRequest){
        return hostService.addFood(addFoodRequest);
    }

    @Operation(summary = "집밥 취소")
    @DeleteMapping("/food")
    public void CancelFood(@RequestBody AddFoodRequest addFoodRequest){ hostService.deleteFood(addFoodRequest); }
}