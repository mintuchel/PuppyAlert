package seominkim.puppyAlert.domain.host.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.host.service.HostService;
import seominkim.puppyAlert.global.dto.LoginRequestDTO;
import seominkim.puppyAlert.global.dto.SignUpRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/host")
public class HostController {

    private final HostService hostService;

    // Host 회원가입
    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody SignUpRequestDTO signUpRequestDTO){
        String hostId = hostService.signUp(signUpRequestDTO);
        return ResponseEntity.ok(hostId);
    }

    // Host 로그인
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestDTO){
        String hostId = loginRequestDTO.getId();
        hostService.checkLogin(loginRequestDTO);
        return ResponseEntity.ok(hostId);
    }

    // Host 전체 조회
    @GetMapping("/all")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(hostService.findAll());
    }

    // HOST 단건 조회
    @GetMapping("/{hostId}")
    public ResponseEntity findOne(@PathVariable String hostId){
        return ResponseEntity.ok(hostService.findById(hostId));
    }
}
