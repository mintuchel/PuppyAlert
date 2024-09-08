package seominkim.puppyAlert.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.food.entity.Food;
import seominkim.puppyAlert.domain.menu.entity.Menu;
import seominkim.puppyAlert.domain.user.dto.request.LoginRequest;
import seominkim.puppyAlert.domain.user.dto.request.SignUpRequest;
import seominkim.puppyAlert.domain.user.dto.response.*;
import seominkim.puppyAlert.domain.user.entity.User;
import seominkim.puppyAlert.domain.user.repository.UserRepository;
import seominkim.puppyAlert.global.entity.UserType;
import seominkim.puppyAlert.global.exception.errorCode.ErrorCode;
import seominkim.puppyAlert.global.exception.exception.CommonException;
import seominkim.puppyAlert.global.exception.exception.UserException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
                }).orElseThrow(() -> new UserException(ErrorCode.NON_EXISTING_USER));
    }

    // User 집밥 기록 검색
    // 이건 READONLY 작업이므로 그냥 상황에따라 getFoodList만 해주면 됨
    // 연관관계의 주인이 Food 지만 CUD 작업이 아니므로
    @Transactional(readOnly = true)
    public List<MatchHistoryResponse> getHistory(String userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.NON_EXISTING_USER));

        if(user.getUserType()==UserType.HOST) {
            // Host 엔티티에서 hostFoods 추출해서 사용
            return user.getHostFoods().stream()
                    .map(food -> {
                        // 필요한 엔티티 미리 추출
                        // 참조할때마다 jpa join 쿼리 나가서 미리 해주는게 좋음
                        User puppy = food.getPuppy();
                        Menu menu = food.getMenu();

                        return new MatchHistoryResponse(
                                food.getFoodId(),
                                puppy != null ? puppy.getId() : null,
                                puppy != null ? puppy.getNickName() : null,
                                menu.getMenuName(),
                                menu.getImageURL(),
                                user.getAddress(),
                                user.getDetailAddress(),
                                user.getLocation(),
                                food.getTime(),
                                puppy != null ? puppy.getProfileImageURL() : null
                        );
                    })
                    .collect(Collectors.toList());
        }else{
            // User 엔티티에서 puppyFoods 추출해서 사용
            return user.getPuppyFoods().stream()
                    .map(food -> {
                        // 필요한 엔티티 미리 추출
                        // 참조할때마다 jpa join 쿼리 나가서 미리 해주는게 좋음
                        User curHost = food.getHost();
                        Menu curMenu = food.getMenu();

                        return new MatchHistoryResponse(
                                food.getFoodId(),
                                curHost.getId(),
                                curHost.getNickName(),
                                curMenu.getMenuName(),
                                curMenu.getImageURL(),
                                curHost.getAddress(),
                                curHost.getDetailAddress(),
                                curHost.getLocation(),
                                food.getTime(),
                                curHost.getProfileImageURL()
                        );
                    })
                    .collect(Collectors.toList());
        }
    }

    @Transactional(readOnly = true)
    public DayFoodResponse getDayFood(String userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.NON_EXISTING_USER));

        List<Food> userFoodList;
        UserType type = user.getUserType();

        if(type == UserType.HOST) userFoodList = user.getHostFoods();
        else userFoodList = user.getPuppyFoods();

        if(userFoodList.isEmpty()) throw new UserException(ErrorCode.NO_MATCHED_TODAY_FOOD);

        Food lastMatchedFood = userFoodList.get(userFoodList.size()-1);
        User host = lastMatchedFood.getHost();
        User puppy = lastMatchedFood.getPuppy();
        Menu menu = lastMatchedFood.getMenu();

        // 마지막꺼가 오늘 날짜면
        if(lastMatchedFood.getTime().toLocalDate().isEqual(LocalDate.now())){

            String partnerNickName;
            if(type==UserType.HOST && puppy!=null) partnerNickName = puppy.getNickName();
            else if(type==UserType.PUPPY) partnerNickName = host.getNickName();
            else partnerNickName = null;

            return new DayFoodResponse(
                    partnerNickName,
                    menu.getMenuName(),
                    menu.getImageURL(),
                    lastMatchedFood.getTime(),
                    host.getAddress(),
                    host.getDetailAddress()
            );
        }
        else throw new UserException(ErrorCode.NO_MATCHED_TODAY_FOOD);
    }
}
