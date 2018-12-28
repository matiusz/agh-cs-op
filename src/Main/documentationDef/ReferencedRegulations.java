package Main.documentationDef;

public class ReferencedRegulations
{
    String journalTitle;
    int journalYear;
    int journalNo;
    int journalEntry;
    String text;

    public ReferencedRegulations(String title, int year, int no, int entry, String text)
    {
        journalTitle=title;
        journalYear=year;
        journalNo=no;
        journalEntry=entry;
        this.text=text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReferencedRegulations that = (ReferencedRegulations) o;
        return journalYear == that.journalYear &&
                journalNo == that.journalNo &&
                journalEntry == that.journalEntry;
    }

    @Override
    public String toString() {
        String s = "" + journalEntry + '\\' + journalNo + '\\' + journalYear;
        return s;
    }

    @Override
    public int hashCode() {

        return journalNo*10000+journalYear;
    }
}
