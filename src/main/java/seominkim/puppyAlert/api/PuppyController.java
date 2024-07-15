package seominkim.puppyAlert.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.entity.Host;
import seominkim.puppyAlert.entity.Puppy;
import seominkim.puppyAlert.service.PuppyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/puppy")
public class PuppyController {

    private final PuppyService puppyService;

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity savePuppy(@RequestBody Puppy puppy){
        String id = puppyService.join(puppy);
        return ResponseEntity.ok(id);
    }

    // Puppy
    @GetMapping("/{hostId}")
    public ResponseEntity findUser(@RequestParam String hostId){
        Puppy puppy = puppyService.findById(hostId);
        return ResponseEntity.ok(puppy);
    }
}
