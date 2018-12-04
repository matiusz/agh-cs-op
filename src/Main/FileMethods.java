package Main;
import Main.documentationDef.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileMethods
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
        File folder = new File(folderpath);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                files.add(file.getName());
            }
        }
        return files;
    }
}