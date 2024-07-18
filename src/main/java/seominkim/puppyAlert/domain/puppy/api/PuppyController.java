package seominkim.puppyAlert.domain.puppy.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.host.dto.HostInfoResponseDTO;
import seominkim.puppyAlert.domain.puppy.dto.MatchRequestDTO;
import seominkim.puppyAlert.domain.puppy.dto.MatchResponseDTO;
import seominkim.puppyAlert.domain.puppy.dto.PuppyInfoResponseDTO;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.puppy.service.PuppyService;
import seominkim.puppyAlert.domain.zipbob.entity.Zipbob;
import seominkim.puppyAlert.global.dto.LoginRequestDTO;
import seominkim.puppyAlert.global.dto.SignUpRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

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
        boolean isValidLogin = puppyService.checkLogin(loginRequestDTO);
        if(isValidLogin){
            return ResponseEntity.ok(puppyId);
        }else{
            return ResponseEntity.badRequest().body(puppyId);
        }
    }

    // Puppy 전체 조회
    @GetMapping("/all")
    public ResponseEntity<List<PuppyInfoResponseDTO>> findAll() {
        List<PuppyInfoResponseDTO> puppyInfoResponseDTOList = puppyService.findAll().stream()
                .map(puppy -> {
                    PuppyInfoResponseDTO dto = new PuppyInfoResponseDTO();
                    dto.setPuppyId(puppy.getPuppyId());
                    dto.setName(puppy.getName());
                    dto.setBirth(puppy.getBirth());
                    dto.setLocation(puppy.getLocation());
                    dto.setPhoneNumber(puppy.getPhoneNumber());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(puppyInfoResponseDTOList);
    }

    // Puppy 단건 조회
    @GetMapping("/{puppyId}")
    public ResponseEntity findOne(@PathVariable String puppyId){
        Puppy puppy = puppyService.findById(puppyId);

        PuppyInfoResponseDTO puppyInfoResponseDTO = new PuppyInfoResponseDTO();
        puppyInfoResponseDTO.setPuppyId(puppy.getPuppyId());
        puppyInfoResponseDTO.setName(puppy.getName());
        puppyInfoResponseDTO.setBirth(puppy.getBirth());
        puppyInfoResponseDTO.setPhoneNumber(puppy.getPhoneNumber());
        puppyInfoResponseDTO.setLocation(puppy.getLocation());

        return ResponseEntity.ok(puppyInfoResponseDTO);
    }

    // 집밥 신청
    @GetMapping("/{zipbobId}/match")
    public MatchResponseDTO matchZipbob(@RequestParam MatchRequestDTO matchRequestDTO){
        Zipbob matchedZipbob = puppyService.matchZipbob(matchRequestDTO);

        MatchResponseDTO matchResponseDTO = new MatchResponseDTO();
        matchResponseDTO.setZipbobId(matchedZipbob.getZipbobId());
        matchResponseDTO.setHostId(matchedZipbob.getHost().getHostId());
        matchResponseDTO.setPuppyId(matchedZipbob.getPuppy().getPuppyId());

        return matchResponseDTO;
    }
}
