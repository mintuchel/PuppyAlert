package seominkim.puppyAlert.domain.openai.utility;

import org.json.JSONObject;

public class Parser {
    public static String parseContent(String response) {
        JSONObject rootObject = new JSONObject(response);

        String contentString = rootObject.getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");

        return contentString;
    }
}
