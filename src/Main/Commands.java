package Main;

import Main.documentationDef.Judgment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Commands
{
    public static void judgmentById(HashMap<Integer, Judgment> judgmentHashMap)
    {
        System.out.println("Please enter target Id");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int targetId = Integer.parseInt(input);
        System.out.println(judgmentHashMap.get(targetId));
    }

    public static void showJudgementContent(HashMap<Integer, Judgment> judgmentHashMap)
    {
        System.out.println("Please enter target Id");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int targetId = Integer.parseInt(input);
        System.out.println(judgmentHashMap.get(targetId).showContent());
    }
}
