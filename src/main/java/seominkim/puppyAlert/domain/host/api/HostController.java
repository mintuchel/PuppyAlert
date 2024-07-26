package seominkim.puppyAlert.domain.host.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.host.service.HostService;
import seominkim.puppyAlert.global.dto.UserInfoResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/host")
public class HostController {

    private final HostService hostService;

    @Operation(summary = "전체 Host 조회")
    @GetMapping("/all")
    public List<UserInfoResponse> findAll() {
        return hostService.findAll();
    }

    @Operation(summary = "특정 Host 조회")
    @GetMapping("/{hostId}")
    public UserInfoResponse findOne(@PathVariable String hostId){
        return hostService.findById(hostId);
    }

    @Operation(summary = "특정 Host 집밥 기록 조회")
    @GetMapping("/history")
    public ResponseEntity getHostHistory(@RequestParam String hostId){
        return ResponseEntity.ok(hostService.getHistory(hostId));
    }
}
