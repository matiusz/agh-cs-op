package Main.documentationDef;

import java.util.Objects;

public class  CourtCase
{
    String caseNumber;
    public CourtCase(String s)
    {
        caseNumber = s;
    }

    @Override
    public int hashCode() {

        return Objects.hash(caseNumber);
    }
}
