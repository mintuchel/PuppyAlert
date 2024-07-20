package seominkim.puppyAlert.domain.favoriteHost.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.favoriteHost.dto.FavoriteHostRequestDTO;
import seominkim.puppyAlert.domain.favoriteHost.dto.FavoriteHostResponseDTO;
import seominkim.puppyAlert.domain.favoriteHost.service.FavoriteHostService;
import seominkim.puppyAlert.domain.zipbob.service.ZipbobService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/favoriteHost")
public class FavoriteHostController {

    private final FavoriteHostService favoriteHostService;
    private final ZipbobService zipbobService;

    // puppyId 는 front 에서 로그인한 puppyId 보관하고 있다가 보내주면 됨
    // zipbobService 통해서 가장 최근시간 JPQL 로 확인
    @GetMapping("/{puppyId}")
    public ResponseEntity findAll(@PathVariable String puppyId){
        List<FavoriteHostResponseDTO> favoriteHostResponseDTOList = favoriteHostService.findAll(puppyId).stream()
                .map(favoriteHost -> {
                    FavoriteHostResponseDTO dto = new FavoriteHostResponseDTO();
                    dto.setHostId(favoriteHost.getHost().getHostId());
                    dto.setRecentZipbobTime(zipbobService.getMostRecentZipbob(puppyId, dto.getHostId()).getTime());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(favoriteHostResponseDTOList);
    }

    @PostMapping("/add")
    public Long addFavoriteHost(@RequestBody FavoriteHostRequestDTO favoriteHostRequestDTO){
        return favoriteHostService.addFavoriteHost(favoriteHostRequestDTO);
    }

    @PostMapping("/delete")
    public void deleteFavoriteHost(@RequestBody FavoriteHostRequestDTO favoriteHostRequestDTO){
        favoriteHostService.deleteFavoriteHost(favoriteHostRequestDTO);
    }
}