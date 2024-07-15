package seominkim.puppyAlert.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import seominkim.puppyAlert.service.ZipbobService;

@RestController
@RequiredArgsConstructor
public class ZipbobController {
    private final ZipbobService zipbobService;
}
