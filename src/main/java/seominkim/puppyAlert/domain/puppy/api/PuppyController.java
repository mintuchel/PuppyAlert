package seominkim.puppyAlert.domain.puppy.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.puppy.dto.MatchRequestDTO;
import seominkim.puppyAlert.domain.puppy.dto.MatchResponseDTO;
import seominkim.puppyAlert.domain.puppy.service.PuppyService;
import seominkim.puppyAlert.global.dto.LoginRequestDTO;
import seominkim.puppyAlert.global.dto.SignUpRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/puppy")
public class PuppyController {

    private final PuppyService puppyService;

    // Puppy 회원가입
    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody SignUpRequestDTO signUpRequestDTO){
        String puppyId = puppyService.signUp(signUpRequestDTO);
        return ResponseEntity.ok(puppyId);
    }

    // Puppy 로그인
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestDTO){
        String puppyId = loginRequestDTO.getId();
        puppyService.checkLogin(loginRequestDTO);
        return ResponseEntity.ok(puppyId);
    }

    // Puppy 전체 조회
    @GetMapping("/all")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(puppyService.findAll());
    }

    // Puppy 단건 조회
    @GetMapping("/{puppyId}")
    public ResponseEntity findOne(@PathVariable String puppyId){
        return ResponseEntity.ok(puppyService.findById(puppyId));
    }

    // Puppy 집밥 신청
    @PostMapping("/zipbob")
    public MatchResponseDTO matchZipbob(@RequestParam MatchRequestDTO matchRequestDTO){
        return puppyService.matchZipbob(matchRequestDTO);
    }
}
