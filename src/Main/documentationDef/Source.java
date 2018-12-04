package Main.documentationDef;

import java.util.Date;

import static Main.documentationDef.Source.code.*;

public class Source
{
    public enum code
    {
        COMMON_COURT,
        SUPREME_COURT,
        CONSTITUTIONAL_TRIBUNAL,
        NATIONAL_APPEAL_CHAMBER;
    }
    public static code stringToEnum(String s)
    {
        switch (s)
        {
            case "COMMON_COURT":
                return COMMON_COURT;
            case "SUPREME_COURT":
                return SUPREME_COURT;
            case "CONSTITUTIONAL_TRIBUNAL":
                return CONSTITUTIONAL_TRIBUNAL;
            case "NATIONAL_APPEAL_CHAMBER":
                return NATIONAL_APPEAL_CHAMBER;
                default:
                    return COMMON_COURT;
        }
    }
    code code;
    String judgmentUrl;
    String judgmentId;
    String publisher;
    String reviser;
    String publicationDate;
}
