package seominkim.puppyAlert.domain.host.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.service.HostService;
import seominkim.puppyAlert.domain.zipbob.dto.ZipbobDTO;
import seominkim.puppyAlert.domain.zipbob.service.ZipbobService;
import seominkim.puppyAlert.global.dto.LoginDTO;
import seominkim.puppyAlert.global.dto.SignUpDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/host")
public class HostController {

    private final HostService hostService;
    private final ZipbobService zipbobService;

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

    // Host 모두 조회
    @GetMapping("/all")
    public ResponseEntity findAll(){
        return ResponseEntity.ok("123");
    }

    // 특정 호스트 조회
    @GetMapping("/{hostId}")
    public ResponseEntity findOne(@RequestParam String hostId){
        Host host = hostService.findById(hostId);
        return ResponseEntity.ok(host);
    }

    // Host 의 집밥 등록
    @PostMapping("/addZipbob")
    public ResponseEntity add(@RequestBody ZipbobDTO zipbobDTO){
        Long zipbobId = zipbobService.add(zipbobDTO);
        return ResponseEntity.ok(zipbobId);
    }
}
