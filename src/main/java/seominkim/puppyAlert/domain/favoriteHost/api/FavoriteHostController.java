package seominkim.puppyAlert.domain.favoriteHost.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.favoriteHost.dto.FavoriteHostRequestDTO;
import seominkim.puppyAlert.domain.favoriteHost.service.FavoriteHostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/favoriteHost")
public class FavoriteHostController {

    private final FavoriteHostService favoriteHostService;

    // 관심 Host 추가
    @PostMapping()
    public Long addFavoriteHost(@RequestBody FavoriteHostRequestDTO favoriteHostRequestDTO){
        return favoriteHostService.addFavoriteHost(favoriteHostRequestDTO);
    }

    // 관심 Host 삭제
    @DeleteMapping()
    public Long deleteFavoriteHost(@RequestBody FavoriteHostRequestDTO favoriteHostRequestDTO){
        return favoriteHostService.deleteFavoriteHost(favoriteHostRequestDTO);
    }

    // puppyId 는 front 에서 로그인한 puppyId 보관하고 있다가 보내주면 됨
    @GetMapping("/{puppyId}")
    public ResponseEntity findAll(@PathVariable String puppyId){
        return ResponseEntity.ok(favoriteHostService.findAll(puppyId));
    }
}