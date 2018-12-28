package Main;
import Main.documentationDef.Judge;
import Main.documentationDef.Judgment;
import Main.functions.Commands;
import Main.functions.FileMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Main
{
    public static void main(String args[]) throws IOException {

        Scanner sc = new Scanner(System.in);
        // path = sc.nextLine();
        HashMap<Integer, Judgment> judgmentHashMap = new HashMap<>();
        HashMap<String, Judge> judgeHashMap = new HashMap<>();
        Commands console = new Commands();
        //Preload
        String path = args[0];
        //
        console.loadNewData(judgmentHashMap, path);
        /*
        try
        {
            for (String string : fileMethods.fileWalk(path)) {
                System.out.println(path + "\\" + string);

                for (Judgment judgment : judgmentJSONArray(path + "\\" + string))
                {
                    judgmentHashMap.put(judgment.getId(), judgment);
                }

            }
        }

        catch (NullPointerException ex)
        {
            System.out.println("Invalid starting directory, shutting down");
            System.exit(1);
        }
        */
        System.out.println(judgmentHashMap.size() + " judgments(s) loaded successfully");
        System.out.print("\n");
        System.out.print(Commands.helptext);
        PrintWriter out;
        try
        {
            out = new PrintWriter(args[1]);
        }
        catch (FileNotFoundException ex)
        {
            File outputFile = new File(args[1]);
            outputFile.createNewFile();
            out = new PrintWriter(args[1]);
        }

        while (true)
        {
            System.out.print(">");
            String input = sc.nextLine();
            out.print(input+"\r\n");
            String results = console.command(input, judgmentHashMap,judgeHashMap);
            if (results.equals("qqq"))
            {
                System.out.println("Thank you for using this program");
                out.flush();
                out.close();
                System.exit(0);
            }
            out.print(results+"\r\n");
            System.out.print(results);
        }
    }
}


/**
Commands progress:
 help - needs update
 content - DONE
 rubrum - DONE
 judge - needs update
 judges - DONE
 months - DONE
 courts - DONE
 regulations - TODO
 jury - DONE
 **/