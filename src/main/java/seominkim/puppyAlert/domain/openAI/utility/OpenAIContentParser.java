package seominkim.puppyAlert.domain.openAI.utility;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class OpenAIContentParser {
    public String parseContent(String response) {
        JSONObject rootObject = new JSONObject(response);

        String contentString = rootObject.getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");

        return contentString;
    }
}