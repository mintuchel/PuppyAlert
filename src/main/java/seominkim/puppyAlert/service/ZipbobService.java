package seominkim.puppyAlert.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seominkim.puppyAlert.repository.ZipbobRepository;

@Service
@RequiredArgsConstructor
public class ZipbobService {
    private final ZipbobRepository zipbobRepository;
}
