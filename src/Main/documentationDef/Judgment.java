    package Main.documentationDef;

    import java.util.Date;

    public class Judgment
    {
        public int id;
        public courtTypes courtType;
        public CourtCase courtCases[];
        public judgmentType judgmentType;
        public Source source;
        public String courtReporters[];
        public String decision;
        public String summary;
        public String textContent;
        public String legalBases;
        public keywords keyword;
        public ReferencedCourtCase referencedCourtCases[];
        public String receiptDate;
        public String meansOfAppeal;
        public String lowerCourtJudgments[];
        public personnelType personnelType;
        public judgmentForm judgmentForm;
        //public Division division;
        //public Chamber chambers[];
        public DissentingOpinions dissentingOpinions[];
    }

