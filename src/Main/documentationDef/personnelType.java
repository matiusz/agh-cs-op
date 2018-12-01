package Main.documentationDef;

public enum personnelType {
    NOT_SUPREME_COURT,
    ONE_PERSON,
    THREE_PERSON,
    FIVE_PERSON,
    SEVEN_PERSON,
    ALL_COURT,
    ALL_CHAMBER,
    JOINED_CHAMBERS;

    public personnelType stringToEnum(String s) {
        switch (s) {
            case "NOT_SUPREME_COURT":
                return NOT_SUPREME_COURT;
            case "ONE_PERSON":
                return ONE_PERSON;
            case "THREE_PERSON":
                return THREE_PERSON;
            case "FIVE_PERSON":
                return FIVE_PERSON;
            case "SEVEN_PERSON":
                return SEVEN_PERSON;
            case "ALL_COURT":
                return ALL_COURT;
            case "ALL_CHAMBER":
                return ALL_CHAMBER;
            case "JOINED_CHAMBERS":
                return JOINED_CHAMBERS;

            default:
                return ONE_PERSON;

        }
    }
}
