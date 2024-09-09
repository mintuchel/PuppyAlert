package seominkim.puppyAlert.domain.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import seominkim.puppyAlert.domain.user.dto.request.LoginRequest;
import seominkim.puppyAlert.domain.user.dto.response.*;
import seominkim.puppyAlert.domain.user.service.UserService;
import seominkim.puppyAlert.domain.user.dto.request.SignUpRequest;
import seominkim.puppyAlert.domain.user.dto.response.MatchHistoryResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "User API", description = "로그인, 회원가입, 중복확인")
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 단건 조회")
    @GetMapping()
    public UserInfoResponse findOne(@RequestParam String id){
        return userService.findOne(id);
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest){
        return userService.signUp(signUpRequest);
    }

    // 쿼리 파라미터로 보내줘야함
    @Operation(summary = "아이디 중복확인")
    @GetMapping("/checkId")
    public IdCheckResponse checkId(@RequestParam String id){
        return new IdCheckResponse(userService.checkIfIdExists(id));
    }

    // 쿼리 파라미터로 보내줘야함
    @Operation(summary = "닉네임 중복확인")
    @GetMapping("/checkNickName")
    public NickNameCheckResponse checkNickName(@RequestParam String nickName){
        return new NickNameCheckResponse(userService.checkIfNickNameExists(nickName));
    }

    @Operation(summary = "집밥 기록 조회")
    @GetMapping("/history")
    public List<MatchHistoryResponse> getHistory(@RequestParam String userId){
        return userService.getHistory(userId);
    }

    @Operation(summary = "오늘의 집밥 조회")
    @GetMapping("/day")
    public DayFoodResponse getDayFoodInfo(@RequestParam String userId){
        return userService.getDayFood(userId);
    }
}
