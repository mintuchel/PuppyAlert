package seominkim.puppyAlert.global.utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class ImageCrawler {

    @Value("${KAKAO_KEY}")
    private String KAKAO_KEY;

    public String getImageURLByKakaoAPI(String menuName){
        String imageURL = "";

        URL url = null;
        String readLine = null;
        StringBuilder buffer = null;
        BufferedReader bufferedReader = null;
        HttpURLConnection urlConnection = null;

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
            urlConnection.setRequestProperty("Authorization", "KakaoAK " + KAKAO_KEY);
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
