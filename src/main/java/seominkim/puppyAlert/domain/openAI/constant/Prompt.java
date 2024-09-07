package seominkim.puppyAlert.domain.openAI.constant;

public enum Prompt {

    CHECK_RESPONSE_SPEC("한식 중 돼지고기,양파,파 재료들로 만들 수 있는 메뉴 3가지만 추천해줘. 메뉴 이름만 알려줘. 딱 메뉴이름 3개만 답변으로 주는거야"),
    CHECK_MENU("%s 이게 음식인지 아닌지만 알려줘. 한국음식일 가능성이 높아. true 아니면 false 둘 중 하나로만 답변을 주는거야"),
    GET_AVAILABLE_ZIPBOB("%s 중 %s 재료들로 만들 수 있는 메뉴 3가지만 추천해줘. 메뉴 이름만 알려줘. 딱 메뉴이름 3개만 답변으로 주는거야");

    private final String prompt;

    Prompt(String prompt){
        this.prompt = prompt;
    }

    public String getPrompt(){
        return prompt;
    }
}
