package Main.functions;

import Main.documentationDef.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class JudgmentBuilderJSON
{
    public int id;
    public Judge judges[];
    public CourtTypes courtType;
    public CourtCase courtCases[];
    public Main.documentationDef.judgmentType judgmentType;
    public Source source;
    public String courtReporters[];
    public String decision;
    public String summary;
    public String textContent;
    public String legalBases;
    public ReferencedCourtCase referencedCourtCases[];
    public String receiptDate;
    public String meansOfAppeal;
    public String lowerCourtJudgments[];
    public personnelType personnelType;
    public String judgmentDate;
    public ReferencedRegulations referencedRegulations[];


    private void simpleFieldsParse(JSONObject json)
    {
        id = json.getInt("id");
        judgmentType = judgmentType.stringToEnum(json.optString("judgmentType"));
        decision =json.optString("decision");
        summary =json.optString("summary");
        textContent =json.optString("textContent");
        legalBases =json.optString("legalBases");
        receiptDate = json.optString("receiptDate");
        meansOfAppeal = json.optString("meansOfAppeal");
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
        source.setCode(Source.stringToEnum(jsonsource.optString("code")));
        source.setJudgmentUrl(jsonsource.optString("judgmentUrl"));
        source.setJudgmentId(jsonsource.optString("judgmentsId"));
        source.setPublisher(jsonsource.optString("publisher"));
        source.setReviser(jsonsource.optString("reviser"));
        source.setPublicationDate(jsonsource.optString("publicationDate"));
        this.source=source;
    }
    private void parseCourtCases(JSONObject json)
    {
        JSONArray courtCases = json.getJSONArray("courtCases");
        this.courtCases = new CourtCase[courtCases.length()];
        for (int i = 0; i<courtCases.length(); i++)
        {
            this.courtCases[i]=new CourtCase(courtCases.getJSONObject(i).optString("caseNumber"));
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
            judge.setFunction(judgeObject.optString("function"));
            judge.setName(judgeObject.optString("name"));
            JSONArray rolesArrayJSON = judgeObject.getJSONArray("specialRoles");
            judge.setSpecialRoles(new Judge.SpecialRoles[rolesArrayJSON.length()]);
            for (int j = 0; j<rolesArrayJSON.length(); j++)
            {
                    judge.getSpecialRoles()[j]=Judge.stringToEnum(rolesArrayJSON.optString(j));
            }
            judges[i]=judge;
        }
    }
    private void parseReferencedCourtCases(JSONObject json)
    {
        JSONArray refs = json.getJSONArray("referencedCourtCases");
        this.referencedCourtCases = new ReferencedCourtCase[refs.length()];
        for (int i = 0; i < refs.length(); i++) {
            JSONObject refobj = refs.getJSONObject(i);
            ReferencedCourtCase parsedRef = new ReferencedCourtCase(refobj.optString("caseNumber"), refobj.getBoolean("generated"));
            JSONArray ids = refobj.getJSONArray("judgmentIds");
            parsedRef.setJudgmentIds(new int[ids.length()]);
            for (int j = 0; j < ids.length(); j++) {
                parsedRef.getJudgmentIds()[j] = ids.getInt(j);
            }
            this.referencedCourtCases[i] = parsedRef;
        }
    }
    private void parseReferencedRegulations(JSONObject json)
    {
       JSONArray refs = json.getJSONArray("referencedRegulations");
       this.referencedRegulations = new ReferencedRegulations[refs.length()];
       for (int i = 0; i<refs.length(); i++)
       {
           JSONObject refobj = refs.getJSONObject(i);
           this.referencedRegulations[i] = new ReferencedRegulations(refobj.optString("journalTitle"), refobj.getInt("journalYear"),refobj.getInt("journalNo"), refobj.getInt("journalEntry"), refobj.optString("text") );
       }
    }

    private void completeParse(JSONObject json)
    {
        simpleFieldsParse(json);
        parseReporters(json);
        parseLowerCourtJudgment(json);
        parseSource(json);
        parseCourtCases(json);
        parseReferencedCourtCases(json);
        parseReferencedRegulations(json);
        parseJudges(json);
    }
    public static JudgmentBuilderJSON[] parseFromJSONArray(String arg)
    {
        FileMethods fileMethods = new FileMethods();
        JSONObject json = new JSONObject(fileMethods.readFile(arg));
        JSONArray jsonarray = json.getJSONArray("items");
        JudgmentBuilderJSON parses[] = new JudgmentBuilderJSON[jsonarray.length()];
        for (int i=0; i<jsonarray.length();i++)
        {
            parses[i]= new JudgmentBuilderJSON();
        }
        for (int i=0; i<jsonarray.length();i++)
        {
            parses[i].completeParse(jsonarray.getJSONObject(i));
        }
        return parses;
    }
}
