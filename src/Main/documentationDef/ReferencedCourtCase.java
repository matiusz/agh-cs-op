package Main.documentationDef;

import static java.util.Arrays.deepToString;

public class ReferencedCourtCase
{
    String caseNumber;
    public int[] judgmentIds;
    boolean generated;
    public ReferencedCourtCase(String s, boolean g)
    {
        caseNumber = s;
        generated = g;
    }

    @Override
    public String toString()
    {
        return(caseNumber+" "+generated+" "+ judgmentIds);
    }
}
