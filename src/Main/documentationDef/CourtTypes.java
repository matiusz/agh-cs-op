package Main.documentationDef;

public enum CourtTypes {
    COMMON,
    SUPREME,
    NATIONAL_APPEAL_CHAMBER,
    CONSTITUTIONAL_TRIBUNAL,
    ADMINISTRATIVE;

    public static CourtTypes stringToEnum(String s) {
        switch (s) {
            case "COMMON":
                return COMMON;
            case "NATIONAL_APPEAL_CHAMBER":
                return NATIONAL_APPEAL_CHAMBER;
            case "SUPREME":
                return SUPREME;
            case "CONSTITUTIONAL_TRIBUNAL":
                return CONSTITUTIONAL_TRIBUNAL;
            case "ADMINISTRATIVE":
                return ADMINISTRATIVE;
            default:
                return COMMON;
        }
    }

    public int enumToInt() {
        switch (this) {
            case COMMON:
                return 1;
            case NATIONAL_APPEAL_CHAMBER:
                return 2;
            case SUPREME:
                return 3;
            case CONSTITUTIONAL_TRIBUNAL:
                return 4;
            case ADMINISTRATIVE:
                return 5;
            default:
                return 0;
        }
    }

    public static String IntToString(int i) {
        switch (i) {
            case 1:
                return "COMMON";
            case 2:
                return "NATIONAL_APPEAL_CHAMBER";
            case 3:
                return "SUPREME";
            case 4:
                return "CONSTITUTIONAL_TRIBUNAL";
            case 5:
                return "ADMINISTRATIVE";
            default:
                return "";

        }
    }
}
