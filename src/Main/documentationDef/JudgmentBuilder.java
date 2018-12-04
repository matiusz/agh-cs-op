package Main.documentationDef;

import Main.FileMethods;
import org.json.JSONArray;
import org.json.JSONObject;

public class JudgmentBuilder
{
    public int id;
    public Judge judges[];
    public CourtTypes courtType;
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
    public String judgmentDate;


    private void simpleFieldsParse(JSONObject json)
    {
        id = (int) json.get("id");
        judgmentType = judgmentType.stringToEnum(json.optString("judgmentType"));
        decision =json.optString("decision");
        summary =json.optString("summary");
        textContent =json.optString("textContent");
        legalBases =json.optString("legalBases");
        keyword = keywords.stringToEnum(json.optString("keyword"));
        receiptDate = json.optString("receiptDate");
        meansOfAppeal = json.optString("meansOfAppeal");
        judgmentForm = judgmentForm.stringToEnum(json.optString("judgmentForm"));
        personnelType = personnelType.stringToEnum(json.optString("personnelType"));
        courtType = CourtTypes.stringToEnum(json.optString("courtType"));
        judgmentDate = json.optString("judgmentDate");
    }

    private void parseReporters(JSONObject json)
    {
        JSONArray reporters =  json.getJSONArray("courtReporters");
        courtReporters = new String[reporters.length()];
        for (int i = 0; i<reporters.length(); i++)
        {
            courtReporters[i]=reporters.getString(i);
        }
    }

    private void parseLowerCourtJudgment(JSONObject json)
    {
        JSONArray lowerCourt =  json.getJSONArray("lowerCourtJudgments");
        lowerCourtJudgments = new String[lowerCourt.length()];
        for (int i = 0; i<lowerCourt.length(); i++)
        {
            lowerCourtJudgments[i]=lowerCourt.getString(i);
        }
    }

    private void parseSource(JSONObject json)
    {
        Source source = new Source();
        JSONObject jsonsource = json.getJSONObject("source");
        source.code = Source.stringToEnum(jsonsource.optString("code"));
        source.judgmentUrl = jsonsource.optString("judgmentUrl");
        source.judgmentId = jsonsource.optString("judgmentsId");
        source.publisher = jsonsource.optString("publisher");
        source.reviser = jsonsource.optString("reviser");
        source.publicationDate = jsonsource.optString("publicationDate");
        this.source=source;
    }
    private void parseCourtCases(JSONObject json)
    {
        JSONArray courtCases =  json.getJSONArray("courtCases");
        this.courtCases = new CourtCase[courtCases.length()];
        for (int i = 0; i<courtCases.length(); i++)
        {
            this.courtCases[i]=new CourtCase(courtCases.optString(i));
        }
    }

    private void parseJudges(JSONObject json)
    {
        JSONArray judgeArray = json.getJSONArray("judges");
        judges = new Judge[judgeArray.length()];
        for (int i = 0; i<judgeArray.length(); i++)
        {
            Judge judge = new Judge();
            JSONObject judgeObject = judgeArray.getJSONObject(i);
            judge.function=judgeObject.optString("function");
            judge.name=judgeObject.optString("name");
            JSONArray rolesArrayJSON = judgeObject.getJSONArray("specialRoles");
            judge.specialRoles = new Judge.SpecialRoles[rolesArrayJSON.length()];
            for (int j = 0; j<rolesArrayJSON.length(); j++)
            {
                    judge.specialRoles[j]=Judge.stringToEnum(rolesArrayJSON.optString(j));
            }
            judges[i]=judge;
        }
    }

    private void parseDissentingOpinions(JSONObject json)
    {
        JSONArray diss = json.getJSONArray("dissentingOpinions");
        dissentingOpinions = new DissentingOpinions[diss.length()];
        for (int i = 0; i < diss.length(); i++) {
            JSONObject dissobj = diss.getJSONObject(i);
            DissentingOpinions parsedDiss = new DissentingOpinions(dissobj.optString("textContent"));
            JSONArray authors = json.getJSONArray("authors");
            String auth[] = new String[authors.length()];
            for (int j = 0; j < authors.length(); j++) {
                auth[j] = authors.getString(j);
            }
            dissentingOpinions[i] = parsedDiss;
        }
    }

    private void parseReferencedCourtCases(JSONObject json)
    {
        JSONArray refs = json.getJSONArray("referencedCourtCases");
        this.referencedCourtCases = new ReferencedCourtCase[refs.length()];
        for (int i = 0; i < refs.length(); i++) {
            JSONObject refobj = refs.getJSONObject(i);
            ReferencedCourtCase parsedRef = new ReferencedCourtCase(refobj.optString("caseNumber"), refobj.getBoolean("genterated"));
            JSONArray ids = json.getJSONArray("judgmentIds");
            parsedRef.judgmentIds = new int[ids.length()];
            for (int j = 0; j < ids.length(); j++) {
                parsedRef.judgmentIds[j] = ids.getInt(j);
            }
            this.referencedCourtCases[i] = parsedRef;
        }
    }

    private void completeParse(JSONObject json)
    {
        simpleFieldsParse(json);
        parseReporters(json);
        parseLowerCourtJudgment(json);
        parseSource(json);
        parseCourtCases(json);
        parseDissentingOpinions(json);
        parseReferencedCourtCases(json);
        parseJudges(json);
    }

    public static JudgmentBuilder parseFromString(String arg)
    {
        FileMethods fileMethods= new FileMethods();
        JSONObject json = new JSONObject(fileMethods.readFile(arg));
        JudgmentBuilder parsed = new JudgmentBuilder();
        parsed.completeParse(json);
        return parsed;
    }
}
