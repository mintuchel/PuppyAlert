package seominkim.puppyAlert.domain.puppy.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.puppy.dto.MatchRequest;
import seominkim.puppyAlert.domain.puppy.dto.MatchResponse;
import seominkim.puppyAlert.domain.puppy.service.PuppyService;
import seominkim.puppyAlert.global.dto.UserInfoResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/puppy")
public class PuppyController {

    private final PuppyService puppyService;

    @Operation(summary = "전체 Puppy 조회")
    @GetMapping("/all")
    public List<UserInfoResponse> findAll() {
        return puppyService.findAll();
    }

    @Operation(summary = "특정 Puppy 조회")
    @GetMapping("/{puppyId}")
    public UserInfoResponse findOne(@PathVariable String puppyId){
        return puppyService.findById(puppyId);
    }

    @Operation(summary = "Puppy 집밥신청")
    @PostMapping("/food")
    public MatchResponse matchFood(@RequestBody MatchRequest matchRequest){
        return puppyService.matchFood(matchRequest);
    }

    @Operation(summary = "특정 Puppy 집밥 기록 조회")
    @GetMapping("/history")
    public ResponseEntity getPuppyHistory(@RequestParam String puppyId){
        return ResponseEntity.ok(puppyService.getHistory(puppyId));
    }
}
