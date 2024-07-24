package seominkim.puppyAlert.domain.puppy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.puppy.dto.MatchRequestDTO;
import seominkim.puppyAlert.domain.puppy.dto.MatchResponseDTO;
import seominkim.puppyAlert.global.dto.UserInfoResponseDTO;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.domain.puppy.repository.PuppyRepository;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.food.entity.FoodStatus;
import seominkim.puppyAlert.domain.food.repository.FoodRepository;
import seominkim.puppyAlert.global.dto.LoginRequestDTO;
import seominkim.puppyAlert.global.dto.SignUpRequestDTO;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.PuppyException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PuppyService {
    private final PuppyRepository puppyRepository;
    private final FoodRepository foodRepository;

    // Puppy 회원가입
    @Transactional
    public String signUp(SignUpRequestDTO signUpDTO){
        Puppy puppy = new Puppy();
        puppy.setPuppyId(signUpDTO.getId());
        puppy.setPassword(signUpDTO.getPassword());
        puppy.setNickName(signUpDTO.getNickName());
        puppy.setName(signUpDTO.getName());
        puppy.setPhoneNumber(signUpDTO.getPhoneNumber());
        puppy.setAddress(signUpDTO.getAddress());
        puppy.setBirth(signUpDTO.getBirth());
        puppy.setLocation(signUpDTO.getLocation());

        validateDuplicatePuppy(puppy);
        puppyRepository.save(puppy);
        return puppy.getPuppyId();
    }

    private void validateDuplicatePuppy(Puppy puppy) {
        List<Puppy> findHosts = puppyRepository.findByName(puppy.getName());
        if (!findHosts.isEmpty()) {
            throw new PuppyException(ErrorCode.EXISTING_ID_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public void checkLogin(LoginRequestDTO loginRequestDTO){
        Optional<Puppy> loginPuppy = puppyRepository.findByPuppyIdAndPassword(loginRequestDTO.getId(), loginRequestDTO.getPassword());
        if(!loginPuppy.isPresent()){
            throw new PuppyException(ErrorCode.INVALID_LOGIN_ERROR);
        }
    }

    // Puppy 전체 검색
    @Transactional(readOnly = true)
    public List<UserInfoResponseDTO> findAll(){
        List<UserInfoResponseDTO> puppyList = puppyRepository.findAll().stream()
                .map(puppy -> {
                    UserInfoResponseDTO dto = new UserInfoResponseDTO();
                    dto.setUserId(puppy.getPuppyId());
                    dto.setNickName(puppy.getNickName());
                    dto.setName(puppy.getName());
                    dto.setBirth(puppy.getBirth());
                    dto.setAddress(puppy.getAddress());
                    dto.setLocation(puppy.getLocation());
                    dto.setPhoneNumber(puppy.getPhoneNumber());
                    return dto;
                })
                .collect(Collectors.toList());

        return puppyList;
    }

    // Puppy 단건 검색
    @Transactional(readOnly = true)
    public UserInfoResponseDTO findById(String puppyId){
        return puppyRepository.findById(puppyId)
                .map(puppy -> {
                    UserInfoResponseDTO dto = new UserInfoResponseDTO();
                    dto.setUserId(puppy.getPuppyId());
                    dto.setNickName(puppy.getNickName());
                    dto.setName(puppy.getName());
                    dto.setBirth(puppy.getBirth());
                    dto.setPhoneNumber(puppy.getPhoneNumber());
                    dto.setAddress(puppy.getAddress());
                    dto.setLocation(puppy.getLocation());
                    return dto;
                })
                .orElseThrow(() -> new PuppyException(ErrorCode.NON_EXISTING_USER));
    }

    @Transactional
    public MatchResponseDTO matchZipbob(MatchRequestDTO matchRequestDTO){
        Food food = foodRepository.findById(matchRequestDTO.getFoodId()).get();
        Puppy puppy = puppyRepository.findById(matchRequestDTO.getPuppyId()).get();
        
        // 집밥 업데이트
        food.setPuppy(puppy);
        food.setStatus(FoodStatus.MATCHED);

        // 업데이트된 집밥 저장
        foodRepository.save(food);

        MatchResponseDTO matchResponseDTO = new MatchResponseDTO();
        matchResponseDTO.setFoodId(food.getFoodId());
        matchResponseDTO.setHostId(food.getHost().getHostId());
        matchResponseDTO.setPuppyId(food.getPuppy().getPuppyId());

        return matchResponseDTO;
    }
}