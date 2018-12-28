package Main.documentationDef;

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
    public void setCode(Source.code code) {
        this.code = code;
    }
    public void setJudgmentUrl(String judgmentUrl) {
        this.judgmentUrl = judgmentUrl;
    }
    public void setJudgmentId(String judgmentId) {
        this.judgmentId = judgmentId;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public void setReviser(String reviser) {
        this.reviser = reviser;
    }
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    String reviser;
    String publicationDate;
}
