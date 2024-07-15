package seominkim.puppyAlert.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seominkim.puppyAlert.repository.HostRepository;

@Service
@RequiredArgsConstructor
public class HostService {
    private final HostRepository hostRepository;
}
