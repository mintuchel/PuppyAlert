package seominkim.puppyAlert.domain.puppy.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.puppy.service.PuppyService;
import seominkim.puppyAlert.global.dto.LoginDTO;
import seominkim.puppyAlert.global.dto.SignUpDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/puppy")
public class PuppyController {

    private final PuppyService puppyService;

    // Puppy 회원가입
    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody SignUpDTO signUpDTO){
        String puppyId = puppyService.signUp(signUpDTO);
        return ResponseEntity.ok(puppyId);
    }

    // Puppy 로그인
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO){
        String puppyId = loginDTO.getId();
        boolean isValidLogin = puppyService.checkLogin(loginDTO);
        if(isValidLogin){
            return ResponseEntity.ok(puppyId);
        }else{
            return ResponseEntity.badRequest().body(puppyId);
        }
    }

    // Puppy 조회
    @GetMapping("/{hostId}")
    public ResponseEntity findPuppy(@RequestParam String hostId){
        Puppy puppy = puppyService.findById(hostId);
        return ResponseEntity.ok(puppy);
    }
}
