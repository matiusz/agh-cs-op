package Main.documentationDef;

public enum CourtTypes
{
    COMMON,
    SUPREME,
    NATIONAL_APPEAL_CHAMBER,
    CONSTITUTIONAL_TRIBUNAL,
    ADMINISTRATIVE;

    public static CourtTypes stringToEnum(String s)
    {
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
}