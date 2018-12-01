    package Main.documentationDef;

    import java.util.Date;

    public class Judgement
    {
        public int id;
        public courtTypes courtType;
        public CourtCase courtCases[];
        public judgementType judgementType;
        public Object source;
        public String courtReporters[];
        public String decision;
        public String summary;
        public String textContent;
        public String legalBases;
        public keywords keyword;
        public ReferencedCourtCase referencedCourtCases[];
        public Date receiptDate;
        public String meansOfAppeal;
        public String lowerCourtJudgements[];
        public personnelType personnelType;
        public judgementForm judgementForm;
        public Division division;
        public Chamber chambers[];
        public DissentingOpinions dissentingOpinions;
    }

