package seominkim.puppyAlert.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.entity.Host;
import seominkim.puppyAlert.service.HostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/host")
public class HostController {

    private final HostService hostService;

    @PostMapping("/join")
    public ResponseEntity saveUser(@RequestBody Host host){
        String id = hostService.join(host);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{hostId}")
    public ResponseEntity findUser(@RequestParam String hostId){
        Host host = hostService.findById(hostId);
        return ResponseEntity.ok(host);
    }
}
