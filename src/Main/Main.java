package Main;

import Main.documentationDef.Judgment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main
{
    public static void main(String args[]) throws IOException {
        String path = args[0];
        FileMethods fileMethods = new FileMethods();
        ArrayList<Judgment> judgments = new ArrayList<Judgment>();
        HashMap<Integer, Judgment> judgmentHashMap = new HashMap<Integer, Judgment>();
        //System.out.println("Please enter target folder path");
        Scanner sc = new Scanner(System.in);
        //String path = sc.nextLine();

        for (String string : fileMethods.fileWalk(path))
        {
            //System.out.println(fileMethods.readFile(path+"\\"+string));
            Judgment judgment = new Judgment(path+"\\"+string);
            judgmentHashMap.put(judgment.getId(), judgment);
        }
        System.out.println("\nList of commands:\njudgmentById (byID) - shows judgment with given id\nshowJudgmentContent (content) - shows text content of a judgement with given id\nq - ends the program");
        //String command = "";
        commands:
        while (true)
        {
            System.out.println("\nEnter command:");
            String command = sc.nextLine();
            switch (command) {
                case "judgmentById":
                case "byID":
                    Commands.judgmentById(judgmentHashMap);
                    continue commands;
                case "showJudgmentContent":
                case "content":
                    Commands.showJudgementContent(judgmentHashMap);
                    continue commands;
                case "q":
                    break commands;
            }
        }
        System.out.println("Thank you for using this program");
        //String line = reader.readLine();
    }
}
