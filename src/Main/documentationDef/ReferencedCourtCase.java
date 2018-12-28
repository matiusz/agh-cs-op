package Main.documentationDef;

public class ReferencedCourtCase
{
    public int[] getJudgmentIds() {
        return judgmentIds;
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
