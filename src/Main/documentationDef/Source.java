package Main.documentationDef;

import java.util.Date;

public class Source
{
    public enum code
    {
        COMMON_COURT,
        SUPREME_COURT,
        CONSTITUTIONAL_TRIBUNAL,
        NATIONAL_APPEAL_CHAMBER;
    }
    code code;
    String judgementUrl;
    String judgementId;
    String publisher;
    String reviser;
    Date publicationDate;

}
