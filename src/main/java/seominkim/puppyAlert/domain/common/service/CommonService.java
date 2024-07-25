package seominkim.puppyAlert.domain.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seominkim.puppyAlert.domain.common.dto.request.LoginRequestDTO;
import seominkim.puppyAlert.domain.common.dto.response.LoginResponse;
import seominkim.puppyAlert.domain.host.repository.HostRepository;
import seominkim.puppyAlert.domain.puppy.repository.PuppyRepository;
import seominkim.puppyAlert.global.entity.UserType;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.CommonException;

@Service
public class CommonService {
    @Autowired HostRepository hostRepository;
    @Autowired PuppyRepository puppyRepository;

    public LoginResponse checkIfAccountExists(LoginRequestDTO loginRequestDTO){
        String id = loginRequestDTO.getId();
        String password = loginRequestDTO.getPassword();

        if(hostRepository.existsByHostIdAndPassword(id,password)){
            return new LoginResponse(id, UserType.HOST);
        }

        if(puppyRepository.existsByPuppyIdAndPassword(id,password)){
            return new LoginResponse(id, UserType.PUPPY);
        }

        throw new CommonException(ErrorCode.NON_EXISTING_USER);
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
