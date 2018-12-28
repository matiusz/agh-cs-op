    package Main.documentationDef;

    import Main.functions.JudgmentBuilderHTML;
    import Main.functions.JudgmentBuilderJSON;
    import org.jsoup.nodes.Document;

    import java.util.HashMap;

    public class Judgment
    {
        int id;


        public int getId()
        {
            return courtCases[0].hashCode();
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

        public ReferencedRegulations[] getReferencedRegulations() {
            return referencedRegulations;
        }

        ReferencedRegulations referencedRegulations[];
        //keywords keyword;
        ReferencedCourtCase referencedCourtCases[];
        String receiptDate;
        String meansOfAppeal;
        //String lowerCourtJudgments[];
        personnelType personnelType;
        //judgmentForm judgmentForm;
        //public Division division;
        //public Chamber chambers[];
        //DissentingOpinions dissentingOpinions[];
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
            referencedRegulations=builderObject.referencedRegulations;
            id=builderObject.id;
            courtType=builderObject.courtType;
            judgmentType=builderObject.judgmentType;
            source=builderObject.source;
            courtReporters=builderObject.courtReporters;
            decision=builderObject.decision;
            summary=builderObject.summary;
            textContent=builderObject.textContent;
            legalBases=builderObject.legalBases;
          //  keyword=builderObject.keyword;
            referencedCourtCases=builderObject.referencedCourtCases;
            receiptDate=builderObject.receiptDate;
            meansOfAppeal=builderObject.meansOfAppeal;
            //judgmentForm=builderObject.judgmentForm;
            //dissentingOpinions=builderObject.dissentingOpinions;
            judges=builderObject.judges;
            judgmentDate=builderObject.judgmentDate;
            courtCases=builderObject.courtCases;
        }
        public Judgment(JudgmentBuilderHTML builderObject)
        {
            referencedRegulations=builderObject.referencedRegulations;
            id=builderObject.id;
            courtType=builderObject.courtType;
            judgmentType=builderObject.judgmentType;
            source=builderObject.source;
            courtReporters=builderObject.courtReporters;
            decision=builderObject.decision;
            summary=builderObject.summary;
            textContent=builderObject.textContent;
            legalBases=builderObject.legalBases;
            //  keyword=builderObject.keyword;
            referencedCourtCases=builderObject.referencedCourtCases;
            receiptDate=builderObject.receiptDate;
            meansOfAppeal=builderObject.meansOfAppeal;
            //judgmentForm=builderObject.judgmentForm;
            //dissentingOpinions=builderObject.dissentingOpinions;
            judges=builderObject.judges;
            judgmentDate=builderObject.judgmentDate;
            courtCases=builderObject.courtCases;
        }

        public static Judgment[] judgmentJSONArray(String filepath)
        {
            JudgmentBuilderJSON builderObjects[] = JudgmentBuilderJSON.parseFromJSONArray(filepath);
            Judgment judgments[] = new Judgment[builderObjects.length];
            for (int i=0; i<builderObjects.length; i++)
            {
                judgments[i]= new Judgment(builderObjects[i]);
            }
            return judgments;
        }

        public static Judgment findBySignature(HashMap<Integer, Judgment> judgmentHashMap, String s) throws IllegalArgumentException
        {
            for(Judgment judgment : judgmentHashMap.values())
            {
                if (judgment.courtCases[0].caseNumber.equals(s))
                {
                    return judgment;
                }
            }
            throw new IllegalArgumentException("Judgement not found");
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
        public String getCourtCase()
        {
            return (courtCases[0].caseNumber);
        }
    }

