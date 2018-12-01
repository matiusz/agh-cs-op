package Main;
import Main.documentationDef.Judgement;
import Main.documentationDef.judgementType;
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

    public void parseFromString(String arg)
    {
        JSONObject json = new JSONObject(arg);
        Judgement parsed = new Judgement();
        parsed.id = (int) json.get("id");
        parsed.judgementType = judgementType.stringToEnum(json.getString("judgementType"));
    }
}