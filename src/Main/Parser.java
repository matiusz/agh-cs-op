package Main;
import Main.documentationDef.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Parser
{
    public String readFile(String pathname)
    {

        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder((int)file.length());

        try (Scanner scanner = new Scanner(file, "Cp1250"))
        {
            while(scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + System.lineSeparator());
            }
            return fileContents.toString();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileContents.toString();
    }

    public ArrayList<String> fileWalk(String folderpath)
    {
        ArrayList<String> files = new ArrayList<String>();
        File folder = new File("C:\\Users\\Mateusz\\Desktop\\parses");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                files.add(file.getName());
            }
        }
        return files;
    }

    public Judgment parseFromString(String arg)
    {
        JSONObject json = new JSONObject(arg);
        Judgment parsed = new Judgment();

        parsed.id = (int) json.get("id");
        parsed.judgmentType = judgmentType.stringToEnum(json.optString("judgmentType"));
        parsed.decision =json.optString("decision");
        parsed.summary =json.optString("summary");
        parsed.textContent =json.optString("textContent");
        parsed.legalBases =json.optString("legalBases");
        parsed.keyword = keywords.stringToEnum(json.optString("keyword"));
        parsed.receiptDate = json.optString("receiptDate");
        parsed.meansOfAppeal = json.optString("meansOfAppeal");
        {
            JSONArray reporters =  json.getJSONArray("courtReporters");
            parsed.courtReporters = new String[reporters.length()];
            for (int i = 0; i<reporters.length(); i++)
            {
                parsed.courtReporters[i]=reporters.getString(i);
            }
        }
        {
            JSONArray lowerCourt =  json.getJSONArray("lowerCourtJudgments");
            parsed.lowerCourtJudgments = new String[lowerCourt.length()];
            for (int i = 0; i<lowerCourt.length(); i++)
            {
                parsed.lowerCourtJudgments[i]=lowerCourt.getString(i);
            }
        }
        {
            Source source = new Source();
            JSONObject jsonsource = json.getJSONObject("source");
            source.code = Source.stringToEnum(jsonsource.optString("code"));
            source.judgmentUrl = jsonsource.optString("judgmentUrl");
            source.judgmentId = jsonsource.optString("judgmentsId");
            source.publisher = jsonsource.optString("publisher");
            source.reviser = jsonsource.optString("reviser");
            source.publicationDate = jsonsource.optString("publicationDate");
            parsed.source=source;
        }
        {
            JSONArray courtCases =  json.getJSONArray("courtCases");
            parsed.courtCases = new CourtCase[courtCases.length()];
            for (int i = 0; i<courtCases.length(); i++)
            {
                parsed.courtCases[i]=new CourtCase(courtCases.optString(i));
            }
        }
        parsed.judgmentForm = judgmentForm.stringToEnum(json.optString("judgmentForm"));
        parsed.personnelType = personnelType.stringToEnum(json.optString("personnelType"));
        parsed.courtType = courtTypes.stringToEnum(json.optString("courtType"));
        {
            JSONArray diss = json.getJSONArray("dissentingOpinions");
            parsed.dissentingOpinions = new DissentingOpinions[diss.length()];
            for (int i = 0; i < diss.length(); i++) {
                JSONObject dissobj = diss.getJSONObject(i);
                DissentingOpinions parsedDiss = new DissentingOpinions(dissobj.optString("textContent"));
                JSONArray authors = json.getJSONArray("authors");
                parsedDiss.authors = new String[authors.length()];
                for (int j = 0; j < authors.length(); j++) {
                    parsedDiss.authors[j] = authors.getString(j);
                }
                parsed.dissentingOpinions[i] = parsedDiss;
            }
        }
        {
            JSONArray refs = json.getJSONArray("referencedCourtCases");
            parsed.referencedCourtCases = new ReferencedCourtCase[refs.length()];
            for (int i = 0; i < refs.length(); i++) {
                JSONObject refobj = refs.getJSONObject(i);
                ReferencedCourtCase parsedRef = new ReferencedCourtCase(refobj.optString("caseNumber"), refobj.getBoolean("genterated"));
                JSONArray ids = json.getJSONArray("judgmentIds");
                parsedRef.judgmentIds = new int[ids.length()];
                for (int j = 0; j < ids.length(); j++) {
                    parsedRef.judgmentIds[j] = ids.getInt(j);
                }
                parsed.referencedCourtCases[i] = parsedRef;
            }
        }
        /** Classes not in sample file but included in format specs
        //parsed.division = new Division(json.getJSONObject("division").getInt("id"));
        /*
        {
            JSONArray cham = json.getJSONArray("chambers");
            parsed.chambers = new Chamber[cham.length()];
            for(int i = 0; i<cham.length(); i++)
            {
                parsed.chambers[i]=new Chamber(cham.getJSONObject(i).getInt("id"));
            }
        }
        */
        return parsed;
    }
}