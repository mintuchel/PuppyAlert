package seominkim.puppyAlert.domain.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seominkim.puppyAlert.domain.common.dto.request.LoginRequest;
import seominkim.puppyAlert.domain.common.dto.request.SignUpRequest;
import seominkim.puppyAlert.domain.common.dto.response.LoginResponse;
import seominkim.puppyAlert.domain.common.dto.response.SignUpResponse;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.host.repository.HostRepository;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.puppy.repository.PuppyRepository;
import seominkim.puppyAlert.global.entity.UserType;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.CommonException;

@Service
public class CommonService {
    @Autowired HostRepository hostRepository;
    @Autowired PuppyRepository puppyRepository;

    public SignUpResponse signUp(SignUpRequest signUpRequest){
        String id = signUpRequest.id();
        UserType userType = signUpRequest.userType();

        if(userType.equals(UserType.HOST)){
            Host host = new Host();
            host.setHostId(signUpRequest.id());
            host.setPassword(signUpRequest.password());
            host.setNickName(signUpRequest.nickName());
            host.setName(signUpRequest.name());
            host.setBirth(signUpRequest.birth());
            host.setPhoneNumber(signUpRequest.phoneNumber());
            host.setAddress(signUpRequest.address());
            host.setDetailAddress(signUpRequest.detailAddress());
            host.setLocation(signUpRequest.location());

            hostRepository.save(host);

            return new SignUpResponse(id, userType);
        }

        if(userType.equals(UserType.PUPPY)){
            Puppy puppy = new Puppy();
            puppy.setPuppyId(signUpRequest.id());
            puppy.setPassword(signUpRequest.password());
            puppy.setNickName(signUpRequest.nickName());
            puppy.setName(signUpRequest.name());
            puppy.setPhoneNumber(signUpRequest.phoneNumber());
            puppy.setAddress(signUpRequest.address());
            puppy.setDetailAddress(signUpRequest.detailAddress());
            puppy.setBirth(signUpRequest.birth());
            puppy.setLocation(signUpRequest.location());

            puppyRepository.save(puppy);

            return new SignUpResponse(id, userType);
        }

        throw new CommonException(ErrorCode.INVALID_USERTYPE);
    }

    public LoginResponse checkIfAccountExists(LoginRequest loginRequest){
        String id = loginRequest.id();
        String password = loginRequest.password();
        String nickName;

        // ID 확인 먼저
        if(!checkIfIdExists(id)){
            throw new CommonException(ErrorCode.INVALID_ID);
        }

        if(hostRepository.existsByHostIdAndPassword(id,password)){
            nickName = hostRepository.findById(id).get().getNickName();
            return new LoginResponse(nickName, UserType.HOST);
        }

        if(puppyRepository.existsByPuppyIdAndPassword(id,password)){
            nickName = puppyRepository.findById(id).get().getNickName();
            return new LoginResponse(nickName, UserType.PUPPY);
        }

        throw new CommonException(ErrorCode.INVALID_PASSWORD);
    }

    public boolean checkIfIdExists(String id){
        if(hostRepository.existsById(id) || puppyRepository.existsById(id)){
            return true;
        }
        return false;
    }

    public boolean checkIfNickNameExists(String nickName){
        if(hostRepository.existsByNickName(nickName) || puppyRepository.existsByNickName(nickName)){
            return true;
        }
        return false;
    }
}
