    package Main.documentationDef;

    import Main.functions.JudgmentBuilderJSON;

    public class Judgment
    {
        int id;

        public int getId()
        {
            return id;
        }

        Judge judges[];
        CourtTypes courtType;
        public CourtCase courtCases[];
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

        /*
        public static Judgment JudgmentFromString(String filepath)
        {
            JudgmentBuilderJSON builderObject = JudgmentBuilderJSON.parseFromString(filepath);
            return new Judgment(builderObject);
        }
        */
        public Judgment(JudgmentBuilderJSON builderObject)
        {
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
            courtCases=builderObject.courtCases;
        }

        public static Judgment[] judgmentArray(String filepath)
        {
            JudgmentBuilderJSON builderObjects[] = JudgmentBuilderJSON.parseFromArray(filepath);
            Judgment judgments[] = new Judgment[builderObjects.length];
            for (int i=0; i<builderObjects.length; i++)
            {
                judgments[i]= new Judgment(builderObjects[i]);
            }
            return judgments;
        }

        public CourtTypes getCourtType()
        {
            return courtType;
        }

        public Judge[] getJudges()
        {
            return judges;
        }
        public String getJudgmentDate()
        {
            return judgmentDate;
        }

        public String showContent()
        {
            return(textContent);
        }
        public String toString()
        {
            return (courtCases[0].caseNumber+", "+judgmentDate+", "+CourtTypes.IntToString(courtType.enumToInt())+", "+ Judge.arrayToString(judges));
        }
    }

