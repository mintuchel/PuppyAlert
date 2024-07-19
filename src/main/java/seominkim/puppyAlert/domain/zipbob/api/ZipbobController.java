package seominkim.puppyAlert.domain.zipbob.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.host.dto.HostMatchHistoryDTO;
import seominkim.puppyAlert.domain.puppy.dto.PuppyMatchHistoryDTO;
import seominkim.puppyAlert.domain.zipbob.dto.ZipbobRequestDTO;
import seominkim.puppyAlert.domain.zipbob.dto.ZipbobResponseDTO;
import seominkim.puppyAlert.domain.zipbob.entity.Zipbob;
import seominkim.puppyAlert.domain.zipbob.service.ZipbobService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/zipbob")
public class ZipbobController {

    private final ZipbobService zipbobService;

    // 현재 가능한 집밥 조회
    @GetMapping("/all")
    public ResponseEntity findAll(){
        List<ZipbobResponseDTO> zipbobResponseDTOList = zipbobService.findAll().stream()
                .map(zipbob -> {
                    ZipbobResponseDTO dto = new ZipbobResponseDTO();
                    dto.setHostId(zipbob.getHost().getHostId());
                    dto.setMenu(zipbob.getMenu());
                    dto.setTime(zipbob.getTime());
                    dto.setLocation(zipbob.getHost().getLocation());
                    dto.setStatus(zipbob.getStatus());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(zipbobResponseDTOList);
    }

    // 특정 집밥 조회
    @GetMapping("/{zipbobId}")
    public ResponseEntity findOne(@PathVariable long zipbobId){
        Zipbob zipbob = zipbobService.findOne(zipbobId);
        return ResponseEntity.ok(zipbob);
    }

    // Host 의 집밥 등록
    @PostMapping()
    public ResponseEntity add(@RequestBody ZipbobRequestDTO zipbobRequestDTO){
        Long zipbobId = zipbobService.add(zipbobRequestDTO);
        return ResponseEntity.ok(zipbobId);
    }

    // Host 집밥 기록 조회
    @GetMapping("/hostHistory")
    public ResponseEntity<List<HostMatchHistoryDTO>> getHostHistory(@RequestBody String hostId){

        List<HostMatchHistoryDTO> hostMatchHistoryDTOList = zipbobService.findHostHistory(hostId).stream()
                .map(zipbob -> {
                    HostMatchHistoryDTO dto = new HostMatchHistoryDTO();
                    dto.setPuppyId(zipbob.getPuppy().getPuppyId());
                    dto.setMenu(zipbob.getMenu());
                    dto.setLocalDateTime(zipbob.getTime());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(hostMatchHistoryDTOList);
    }

    // Puppy 집밥 기록 조회
    @GetMapping("/puppyHistory")
    public ResponseEntity<List<PuppyMatchHistoryDTO>> getPuppyHistory(@RequestBody String puppyId){

        List<PuppyMatchHistoryDTO> puppyMatchHistoryDTOList = zipbobService.findPuppyHistory(puppyId).stream()
                .map(zipbob -> {
                    PuppyMatchHistoryDTO dto = new PuppyMatchHistoryDTO();
                    dto.setHostId(zipbob.getHost().getHostId());
                    dto.setMenu(zipbob.getMenu());
                    dto.setLocalDateTime(zipbob.getTime());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(puppyMatchHistoryDTOList);
    }
}