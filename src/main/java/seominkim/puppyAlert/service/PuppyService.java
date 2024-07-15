package seominkim.puppyAlert.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seominkim.puppyAlert.repository.PuppyRepository;

@Service
@RequiredArgsConstructor
public class PuppyService {
    private final PuppyRepository puppyRepository;
}
