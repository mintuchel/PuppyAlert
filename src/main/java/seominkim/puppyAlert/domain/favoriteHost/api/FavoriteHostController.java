package seominkim.puppyAlert.domain.favoriteHost.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.favoriteHost.dto.FavoriteHostRequestDTO;
import seominkim.puppyAlert.domain.favoriteHost.service.FavoriteHostService;
import seominkim.puppyAlert.domain.zipbob.service.ZipbobService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/favoriteHost")
public class FavoriteHostController {

    private final FavoriteHostService favoriteHostService;
    private final ZipbobService zipbobService;

//    // puppyId 는 front 에서 로그인한 puppyId 보관하고 있다가 보내주면 됨
//    @GetMapping("/{puppyId}")
//    public ResponseEntity findAll(@PathVariable String puppyId){
//        List<FavoriteHost> favoriteHostList = favoriteHostService.findAll(puppyId).stream()
//                .map(favoriteHost -> {
//                    FavoriteHostResponseDTO dto = new FavoriteHostResponseDTO();
//                    dto.setHostId(favoriteHost.getHost().getHostId());
//                    return dto;
//                })
//                .collect(Collectors.toList());
//
//    }

    @PostMapping("/add")
    public void addFavoriteHost(@RequestBody FavoriteHostRequestDTO favoriteHostRequestDTO){
        favoriteHostService.addFavoriteHost(favoriteHostRequestDTO);
    }

    @PostMapping("/delete")
    public void deleteFavoriteHost(@RequestBody FavoriteHostRequestDTO favoriteHostRequestDTO){
        favoriteHostService.deleteFavoriteHost(favoriteHostRequestDTO);
    }
}