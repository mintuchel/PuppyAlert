package seominkim.puppyAlert.domain.host.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.host.service.HostService;
import seominkim.puppyAlert.global.dto.SignUpRequestDTO;
import seominkim.puppyAlert.global.dto.UserInfoResponseDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/host")
public class HostController {

    private final HostService hostService;

    @Operation(summary = "Host 회원가입")
    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequestDTO signUpRequestDTO){
        return hostService.signUp(signUpRequestDTO);
    }

    @Operation(summary = "전체 Host 조회")
    @GetMapping("/all")
    public List<UserInfoResponseDTO> findAll() {
        return hostService.findAll();
    }

    @Operation(summary = "특정 Host 조회")
    @GetMapping("/{hostId}")
    public UserInfoResponseDTO findOne(@PathVariable String hostId){
        return hostService.findById(hostId);
    }
}
