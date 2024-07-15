package seominkim.puppyAlert.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seominkim.puppyAlert.service.ZipbobService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/zipbob")
public class ZipbobController {
    private final ZipbobService zipbobService;


}
