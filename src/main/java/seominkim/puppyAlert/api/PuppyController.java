package seominkim.puppyAlert.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import seominkim.puppyAlert.service.PuppyService;

@RestController
@RequiredArgsConstructor
public class PuppyController {
    private final PuppyService puppyService;

}
