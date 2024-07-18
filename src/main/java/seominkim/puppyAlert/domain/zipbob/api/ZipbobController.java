package seominkim.puppyAlert.domain.zipbob.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/all")
    public ResponseEntity findAll(){
        List<ZipbobResponseDTO> zipbobResponseDTOList = zipbobService.findAll().stream()
                .map(zipbob -> {
                    ZipbobResponseDTO dto = new ZipbobResponseDTO();
                    dto.setHostId(zipbob.getHost().getHostId());
                    dto.setMenu(zipbob.getMenu());
                    dto.setTime(zipbob.getTime());
                    dto.setStatus(zipbob.getStatus());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(zipbobResponseDTOList);
    }

    @GetMapping("/{zipbobId}")
    public ResponseEntity findOne(@PathVariable long zipbobId){
        Zipbob zipbob = zipbobService.findOne(zipbobId);
        return ResponseEntity.ok(zipbob);
    }
}