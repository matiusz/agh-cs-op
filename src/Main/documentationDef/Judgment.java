    package Main.documentationDef;

    import java.util.Arrays;

    public class Judgment
    {
        int id;

        public int getId()
        {
            return id;
        }

        Judge judges[];
        CourtTypes courtType;
        CourtCase courtCases[];
        judgmentType judgmentType;
        Source source;
        String courtReporters[];
        String decision;
        String summary;
        String textContent;
        String legalBases;
        keywords keyword;
        ReferencedCourtCase referencedCourtCases[];
        String receiptDate;
        String meansOfAppeal;
        String lowerCourtJudgments[];
        personnelType personnelType;
        judgmentForm judgmentForm;
        //public Division division;
        //public Chamber chambers[];
        DissentingOpinions dissentingOpinions[];
        String judgmentDate;

        public Judgment(String filepath)
        {
            JudgmentBuilder builderObject = JudgmentBuilder.parseFromString(filepath);
            id=builderObject.id;
            courtType=builderObject.courtType;
            judgmentType=builderObject.judgmentType;
            source=builderObject.source;
            courtReporters=builderObject.courtReporters;
            decision=builderObject.decision;
            summary=builderObject.summary;
            textContent=builderObject.textContent;
            legalBases=builderObject.legalBases;
            keyword=builderObject.keyword;
            referencedCourtCases=builderObject.referencedCourtCases;
            receiptDate=builderObject.receiptDate;
            meansOfAppeal=builderObject.meansOfAppeal;
            judgmentForm=builderObject.judgmentForm;
            dissentingOpinions=builderObject.dissentingOpinions;
            judges=builderObject.judges;
            judgmentDate=builderObject.judgmentDate;

        }

        @Override
        public String toString()
        {
            return(id+"\n"+judgmentDate+'\n'+courtType+'\n'+ Arrays.toString(judges));
        }

        public String showContent()
        {
            return(textContent);
        }
    }

