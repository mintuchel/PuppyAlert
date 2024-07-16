package seominkim.puppyAlert.domain.host.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.service.HostService;
import seominkim.puppyAlert.global.dto.LoginDTO;
import seominkim.puppyAlert.global.dto.SignUpDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/host")
public class HostController {

    private final HostService hostService;

    // Host 회원가입
    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody SignUpDTO signUpDTO){
        String hostId = hostService.signUp(signUpDTO);
        return ResponseEntity.ok(hostId);
    }

    // Host 로그인
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO){
        String hostId = loginDTO.getId();
        boolean isValidLogin = hostService.checkLogin(loginDTO);
        if(isValidLogin){
            return ResponseEntity.ok(hostId);
        }else{
            return ResponseEntity.badRequest().body(hostId);
        }
    }

    @GetMapping("/{hostId}")
    public ResponseEntity findHost(@RequestParam String hostId){
        Host host = hostService.findById(hostId);
        return ResponseEntity.ok(host);
    }
}
