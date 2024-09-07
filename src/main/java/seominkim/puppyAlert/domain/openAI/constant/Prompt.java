package seominkim.puppyAlert.domain.openAI.constant;

public enum Prompt {

    CHECK_RESPONSE_SPEC("한식 중 돼지고기,양파,파 재료들로 만들 수 있는 메뉴 3가지만 추천해줘. 메뉴 이름만 알려줘. 무조건 메뉴이름 3개만 답변으로 주는거야"),

    CHECK_MENU("%s 이게 음식인지 아닌지만 알려줘. 한국음식일 가능성이 높아. 무조건 true 아니면 false 둘 중 하나로 답변을 주는거야"),

    GET_AVAILABLE_ZIPBOB("%s 요리들 중 %s 재료들 중 몇 개를 사용해서 만들 수 있는 메뉴 3가지만 추천해줘. 메뉴이름은 10자 이하여야해. 무조건 메뉴 이름만 알려줘. 무조건 메뉴이름 3개만 답변으로 주는거야"),

    GET_RECIPE_DESCRIPTION("%s의 특징과 인기를 표현하는 소개 문구를 작성해줘. 답변을 무조건 한 문구로 주고 그 문구는 무조건 '음식!'이라는 단어로 끝나야해."),

    GET_RECIPE("난 %s 이 음식을 만들고 싶어. 이 메뉴에 대한 레시피를 간단하게 알려줘. 다섯 단계로 알려줘. 답변은 5줄로 구성되어 있어. 각 줄마다 한 개의 단계로 무조건 5줄만 보내줘야해");

    private final String prompt;

    Prompt(String prompt){
        this.prompt = prompt;
    }

    public String getPrompt() { return prompt; }
}
