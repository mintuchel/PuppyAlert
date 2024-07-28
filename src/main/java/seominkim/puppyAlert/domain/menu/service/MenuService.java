package seominkim.puppyAlert.domain.menu.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.menu.entity.Menu;
import seominkim.puppyAlert.domain.menu.repository.MenuRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    // findOne 이라는 메서드 자체가 트랜잭션을 관리하게 해야함
    // addNewMenu 에 대한 트랜잭션을 책임져야하므로 얘도 Transactional 이 되어야함
    @Transactional
    public Menu findOne(String menuName){

        if(checkIfMenuExists(menuName)) {
            return menuRepository.findById(menuName).get();
        }

        return addNewMenu(menuName);
    }

    @Transactional(readOnly = true)
    private boolean checkIfMenuExists(String menuName){
        return menuRepository.existsById(menuName);
    }

    @Transactional
    private Menu addNewMenu(String menuName){
        Menu menu = new Menu();
        menu.setMenuName(menuName);
        menu.setImageURL(getImageURLByKakaoAPI(menuName));

        // Food 와 Menu 간의 연관관계의 주인이 Food 이기 때문에 Menu 쪽에 직접 저장하면 안됨!
        // 이거 하면 DupulicateKeyException 뜸
        // menuRepository.save(menu);

        return menu;
    }

    private String getImageURLByKakaoAPI(String menuName){
        String imageURL = "";

        URL url = null;
        String readLine = null;
        StringBuilder buffer = null;
        BufferedReader bufferedReader = null;
        HttpURLConnection urlConnection = null;

        String RESTAPIKey = "bd02cf38bea539c77e8e9e7622711517";

        try {
            // 1. 날릴 URL 설정하기
            menuName = URLEncoder.encode(menuName, StandardCharsets.UTF_8);

            // URL 에 query parameters 붙여주기
            String apiUrl =
                    "https://dapi.kakao.com/v2/search/image?query="
                            + menuName
                            + "&sort=accuracy&size=1";

            // 2. Request Message 객체 만들기. requestMessage 설정정보 구성
            url = new URL(apiUrl);
            urlConnection = (HttpURLConnection) url.openConnection();

            // 3. Request Message HTTP method 설정
            urlConnection.setRequestMethod("GET");

            // Request Message Header 정보 추가
            // Header에 Authoriztion 을 위해 "KakaoAK + 내 API 키" 넣어줘야함
            urlConnection.setRequestProperty("Authorization", "KakaoAK " + RESTAPIKey);
            // json 형식임을 명시
            urlConnection.setRequestProperty("Accept", "application/json");

            buffer = new StringBuilder();

            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
            while ((readLine = bufferedReader.readLine()) != null) {
                buffer.append(readLine).append("\n");
            }

            String totalJSONstring = buffer.toString();

            // JSONString 을 JSON Objects 로 파싱해줌
            JSONParser parser = new JSONParser();

            // 가장 큰 JSONObject 가져오기. Documents 랑 Meta
            // 일단 Documents / MetaData 이렇게 두 개로 나누기
            JSONObject jsonObject = (JSONObject) parser.parse(totalJSONstring);

            // Documents JSONObject 들만 추출
            JSONArray documentArray = (JSONArray) jsonObject.get("documents");

            JSONObject curDocument = (JSONObject) documentArray.get(0);

            imageURL = (String) curDocument.get("thumbnail_url");

            if(imageURL.isEmpty()){
                imageURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSN_WM3EWTjbq2QuZC7txWQP92E2VoA3SwZtg&s";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageURL;
    }
}
