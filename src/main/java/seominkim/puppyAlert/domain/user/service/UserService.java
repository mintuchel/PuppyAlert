package seominkim.puppyAlert.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.user.dto.request.LoginRequest;
import seominkim.puppyAlert.domain.user.dto.request.SignUpRequest;
import seominkim.puppyAlert.domain.user.dto.response.LoginResponse;
import seominkim.puppyAlert.domain.user.dto.response.SignUpResponse;
import seominkim.puppyAlert.domain.user.dto.response.UserInfoResponse;
import seominkim.puppyAlert.domain.user.entity.User;
import seominkim.puppyAlert.domain.user.repository.UserRepository;
import seominkim.puppyAlert.global.entity.UserType;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.CommonException;
import seominkim.puppyAlert.global.exception.exception.HostException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public SignUpResponse signUp(SignUpRequest signUpRequest){
        String id = signUpRequest.id();

        if(userRepository.existsById(id)){
            throw new CommonException(ErrorCode.EXISTING_ID);
        }

        UserType userType = signUpRequest.userType();

        User user = new User();
        user.setId(id);
        user.setPassword(signUpRequest.password());
        user.setNickName(signUpRequest.nickName());
        user.setName(signUpRequest.name());
        user.setBirth(signUpRequest.birth());
        user.setPhoneNumber(signUpRequest.phoneNumber());
        user.setAddress(signUpRequest.address());
        user.setDetailAddress(signUpRequest.detailAddress());
        user.setLocation(signUpRequest.location());

        if(userType.equals(UserType.HOST)) user.setUserType(UserType.HOST);
        else user.setUserType(UserType.PUPPY);

        return new SignUpResponse(id, userType);
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest loginRequest){
        String id = loginRequest.id();
        String password = loginRequest.password();

        // ID가 존재하지 않으면 로그인 실패임
        if(!checkIfIdExists(id)){
            throw new CommonException(ErrorCode.INVALID_ID);
        }

        // ID PW 모두 올바르면
        if(userRepository.existsByIdAndPassword(id, password)){
            User user = userRepository.findById(id).get();
            String nickName = user.getNickName();
            UserType userType = user.getUserType();
            return new LoginResponse(nickName, userType);
        }

        // 아니면 ID는 존재하는데 PW이 틀린거임
        throw new CommonException(ErrorCode.INVALID_PASSWORD);
    }

    @Transactional(readOnly = true)
    public boolean checkIfIdExists(String id){
        return userRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean checkIfNickNameExists(String nickName) {
        return userRepository.existsByNickName(nickName);
    }

    @Transactional(readOnly=true)
    public UserInfoResponse findOne(String id) {
        return userRepository.findById(id)
                .map(user -> {
                    UserInfoResponse dto = new UserInfoResponse(
                            user.getId(),
                            user.getName(),
                            user.getNickName(),
                            user.getBirth(),
                            user.getPhoneNumber(),
                            user.getAddress(),
                            user.getDetailAddress(),
                            user.getLocation(),
                            user.getProfileImageURL()
                    );
                    return dto;
                }).orElseThrow(() -> new HostException(ErrorCode.NON_EXISTING_USER));
    }
}
