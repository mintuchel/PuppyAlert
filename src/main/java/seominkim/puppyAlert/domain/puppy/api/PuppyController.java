package seominkim.puppyAlert.domain.puppy.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.favoriteHost.dto.FavoriteHostRequest;
import seominkim.puppyAlert.domain.favoriteHost.dto.FavoriteHostResponse;
import seominkim.puppyAlert.domain.food.dto.FoodResponse;
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

    @Operation(summary = "단건 조회")
    @GetMapping()
    public UserInfoResponse findOne(@RequestParam String puppyId){
        return puppyService.findById(puppyId);
    }

    @Operation(summary = "전체 조회 (관리자용)")
    @GetMapping("/all")
    public List<UserInfoResponse> findAll() {
        return puppyService.findAll();
    }

    @Operation(summary = "신청 가능한 집밥 조회")
    @GetMapping("/food")
    public List<FoodResponse> getAvailableFood(@RequestParam String puppyId){
        return puppyService.getAvailableFood(puppyId);
    }

    @Operation(summary = "집밥 신청")
    @PostMapping("/food")
    public MatchResponse matchFood(@RequestBody MatchRequest matchRequest){
        return puppyService.handleMatchRequest(matchRequest);
    }

    @Operation(summary = "집밥 기록 조회")
    @GetMapping("/history")
    public ResponseEntity getHistory(@RequestParam String puppyId){
        return ResponseEntity.ok(puppyService.getHistory(puppyId));
    }

    @Operation(summary = "관심 호스트 조회")
    @GetMapping("/favoriteHost")
    public List<FavoriteHostResponse> getFavoriteHost(@RequestParam String puppyId){
        return puppyService.getFavoriteHost(puppyId);
    }

    @Operation(summary = "관심 호스트 추가")
    @PostMapping("/favoriteHost")
    public void addFavoriteHost(@RequestBody FavoriteHostRequest favoriteHostRequest){
        puppyService.addFavoriteHost(favoriteHostRequest);
    }

    @Operation(summary = "관심 호스트 삭제")
    @DeleteMapping("/favoriteHost")
    public void deleteFavoriteHost(@RequestBody FavoriteHostRequest favoriteHostRequest){
        puppyService.deleteFavoriteHost(favoriteHostRequest);
    }
}