package seominkim.puppyAlert.domain.common.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.common.dto.response.IdCheckResponse;
import seominkim.puppyAlert.domain.common.dto.response.NickNameCheckResponse;
import seominkim.puppyAlert.domain.common.service.CommonService;
import seominkim.puppyAlert.domain.common.dto.request.LoginRequestDTO;
import seominkim.puppyAlert.domain.common.dto.response.LoginResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/common")
public class CommonController {

    private final CommonService commonService;

    @Operation(summary="로그인")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequestDTO loginRequestDTO){
        return commonService.checkIfAccountExists(loginRequestDTO);
    }

    // 쿼리 파라미터로 보내줘야함
    @Operation(summary="아이디 중복확인")
    @GetMapping("/checkId")
    public IdCheckResponse checkId(@RequestParam String id){
        return new IdCheckResponse(commonService.checkIfIdExists(id));
    }

    // 쿼리 파라미터로 보내줘야함
    @Operation(summary="닉네임 중복확인")
    @GetMapping("/checkNickName")
    public NickNameCheckResponse checkNickName(@RequestParam String nickName){
        return new NickNameCheckResponse(commonService.checkIfNickNameExists(nickName));
    }
}
