package Main;
import Main.documentationDef.Judge;
import Main.documentationDef.Judgment;
import Main.functions.Commands;
import Main.functions.FileMethods;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import static Main.documentationDef.Judgment.judgmentArray;

public class Main {
    public static void main(String args[]) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        // path = sc.nextLine();
        FileMethods fileMethods = new FileMethods();
        HashMap<Integer, Judgment> judgmentHashMap = new HashMap<>();
        HashMap<String, Judge> judgeHashMap = new HashMap<>();
        //Preload
        String path = args[0];
        //
        try
        {
            for (String string : fileMethods.fileWalk(path)) {
                System.out.println(path + "\\" + string);
                for (Judgment judgment : judgmentArray(path + "\\" + string)) {
                    judgmentHashMap.put(judgment.getId(), judgment);
                }
            }
        }
        catch (NullPointerException ex)
        {
            System.out.println("Invalid starting directory, shutting down");
            System.exit(1);
        }
        System.out.println(judgmentHashMap.size() + " judgments(s) loaded successfully");
        System.out.print("\n");
        System.out.println(Commands.helptext);
        Commands console = new Commands();
        while (true)
        {
            System.out.print("Enter next command:\n>");
            String input = sc.nextLine();
            System.out.print(console.command(input, judgmentHashMap,judgeHashMap));
        }
    }
}


/**
Commands progress:
 help - needs update
 content - DONE
 judge - needs update
 judges - DONE
 months - DONE
 courts - DONE
 regulations - TODO
 jury - DONE
 **/