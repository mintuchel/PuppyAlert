package seominkim.puppyAlert.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import seominkim.puppyAlert.service.HostService;

@RestController
@RequiredArgsConstructor
public class HostController {
    private final HostService hostService;
}
