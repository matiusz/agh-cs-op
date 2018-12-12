package Main.functions;
import Main.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileMethods
{
    public String readFile(String pathname)
    {

        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder(100000);

        try (Scanner scanner = new Scanner(file, "UTF-8"))
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

    public ArrayList<String> fileWalk(String folderpath) throws NullPointerException
    {
        ArrayList<String> files = new ArrayList<String>();
        File folder = new File(folderpath);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles==null)
        {
            throw new NullPointerException("Folder invalid");
        }
        for (File file : listOfFiles) {
            if (file.isFile()) {
                files.add(file.getName());
            }
        }
        return files;
    }
}