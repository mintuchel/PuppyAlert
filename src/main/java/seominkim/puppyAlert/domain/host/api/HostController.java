package seominkim.puppyAlert.domain.host.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.food.dto.FoodRequest;
import seominkim.puppyAlert.domain.host.service.HostService;
import seominkim.puppyAlert.global.dto.UserInfoResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/host")
@Tag(name = "Host API", description = "정보 조회, 집밥 등록, 기록 조회")
public class HostController {

    private final HostService hostService;

    @Operation(summary = "단건 조회")
    @GetMapping()
    public UserInfoResponse findOne(@RequestParam String hostId){
        return hostService.findById(hostId);
    }

    @Operation(summary = "전체 조회 (관리자용)")
    @GetMapping("/all")
    public List<UserInfoResponse> findAll() {
        return hostService.findAll();
    }

    @Operation(summary = "집밥 등록")
    @PostMapping("/food")
    public ResponseEntity addFood(@RequestBody FoodRequest foodRequest){
        Long foodId = hostService.addFood(foodRequest);
        return ResponseEntity.ok(foodId);
    }

    @Operation(summary = "집밥 기록 조회")
    @GetMapping("/history")
    public ResponseEntity getHostHistory(@RequestParam String hostId){
        return ResponseEntity.ok(hostService.getHistory(hostId));
    }
}
