package seominkim.puppyAlert.domain.host.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.host.dto.HostInfoResponseDTO;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.service.HostService;
import seominkim.puppyAlert.domain.zipbob.service.ZipbobService;
import seominkim.puppyAlert.global.dto.LoginRequestDTO;
import seominkim.puppyAlert.global.dto.SignUpRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/host")
public class HostController {

    private final HostService hostService;
    private final ZipbobService zipbobService;

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
    public ResponseEntity<List<HostInfoResponseDTO>> findAll() {
        List<HostInfoResponseDTO> hostInfoResponseDTOList = hostService.findAll().stream()
                .map(host -> {
                    HostInfoResponseDTO dto = new HostInfoResponseDTO();
                    dto.setHostId(host.getHostId());
                    dto.setName(host.getName());
                    dto.setBirth(host.getBirth());
                    dto.setLocation(host.getLocation());
                    dto.setPhoneNumber(host.getPhoneNumber());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(hostInfoResponseDTOList);
    }

    // 특정 호스트 조회
    @GetMapping("/{hostId}")
    public ResponseEntity findOne(@PathVariable String hostId){
        Host host = hostService.findById(hostId);

        HostInfoResponseDTO hostInfoResponseDTO = new HostInfoResponseDTO();
        hostInfoResponseDTO.setHostId(host.getHostId());
        hostInfoResponseDTO.setName(host.getName());
        hostInfoResponseDTO.setBirth(host.getBirth());
        hostInfoResponseDTO.setLocation(host.getLocation());
        hostInfoResponseDTO.setPhoneNumber(host.getPhoneNumber());

        return ResponseEntity.ok(hostInfoResponseDTO);
    }
}
