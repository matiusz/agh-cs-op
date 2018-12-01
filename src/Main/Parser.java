package Main;
import Main.documentationDef.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;


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
        parsed.division = new Division(json.getJSONObject("division").getInt("id"));

        return parsed;
    }
}