package seominkim.puppyAlert.api;

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

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody Zipbob zipbob){
        Long zipbobId = zipbobService.add(zipbob);
        return ResponseEntity.ok(zipbobId);
    }

    @GetMapping
    public ResponseEntity findAll(){
        List<Zipbob> zipbobList = zipbobService.findAll();
        return ResponseEntity.ok(zipbobList);
    }

    @GetMapping("/{id}")
    public ResponseEntity findOne(@RequestParam Long zipbobId){
        Zipbob zipbob = zipbobService.findOne(zipbobId);
        return ResponseEntity.ok(zipbob);
    }
}
