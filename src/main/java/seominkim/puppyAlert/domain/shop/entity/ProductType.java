package seominkim.puppyAlert.domain.shop.entity;

public enum ProductType {
    SUPER_MARKET("슈퍼"),
    MEAT("정육"),
    RICE_CAKE("떡"),
    RICE("쌀"),
    VEGETABLES("야채"),
    SIDE_DISH("반찬"),
    AGRICULTURAL("농산물");

    private final String description;

    ProductType(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
