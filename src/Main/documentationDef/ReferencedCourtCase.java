package Main.documentationDef;

import static java.util.Arrays.deepToString;

public class ReferencedCourtCase
{
    public String getCaseNumber() {
        return caseNumber;
    }

    public int[] getJudgmentIds() {
        return judgmentIds;
    }

    public boolean isGenerated() {
        return generated;
    }


    String caseNumber;

    public void setJudgmentIds(int[] judgmentIds) {
        this.judgmentIds = judgmentIds;
    }

    int[] judgmentIds;
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
