package src.ctt.GameMlemBot.Enums;

public enum ItemTypes {
    HELMET("Nón"),
    ARMOR("Giáp"),
    LEGGINGS("Quần"),
    BOOTS("Giày"),
    CONSUMABLE("Tiêu thụ");

    String value;

    public String getValue() {
        return value;
    }

    private ItemTypes(String standsFor) {
        this.value = standsFor;
    }
}
