package seominkim.puppyAlert.domain.zipbob.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.zipbob.entity.Zipbob;
import seominkim.puppyAlert.domain.zipbob.service.ZipbobService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/zipbob")
public class ZipbobController {

    private final ZipbobService zipbobService;

    @GetMapping()
    public ResponseEntity findAll(){
        List<Zipbob> zipbobList = zipbobService.findAll();
        return ResponseEntity.ok(zipbobList);
    }

    @GetMapping("/{zipbobId}")
    public ResponseEntity findOne(@RequestParam long zipbobId){
        Zipbob zipbob = zipbobService.findOne(zipbobId);
        return ResponseEntity.ok(zipbob);
    }
}