package seominkim.puppyAlert.domain.puppy.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.puppy.dto.MatchRequestDTO;
import seominkim.puppyAlert.domain.puppy.dto.MatchResponseDTO;
import seominkim.puppyAlert.domain.puppy.service.PuppyService;
import seominkim.puppyAlert.global.dto.SignUpRequestDTO;
import seominkim.puppyAlert.global.dto.UserInfoResponseDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/puppy")
public class PuppyController {

    private final PuppyService puppyService;

    @Operation(summary = "Puppy 회원가입")
    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequestDTO signUpRequestDTO){
        return puppyService.signUp(signUpRequestDTO);
    }

    @Operation(summary = "전체 Puppy 조회")
    @GetMapping("/all")
    public List<UserInfoResponseDTO> findAll() {
        return puppyService.findAll();
    }

    @Operation(summary = "특정 Puppy 조회")
    @GetMapping("/{puppyId}")
    public UserInfoResponseDTO findOne(@PathVariable String puppyId){
        return puppyService.findById(puppyId);
    }

    @Operation(summary = "Puppy 집밥신청")
    @PostMapping("/food")
    public MatchResponseDTO matchFood(@RequestParam MatchRequestDTO matchRequestDTO){
        return puppyService.matchFood(matchRequestDTO);
    }
}
