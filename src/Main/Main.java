package Main;

import Main.documentationDef.Judgment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main
{
    public static void main(String args[]) throws IOException {
        String path = "C:\\Users\\Mateusz\\Desktop\\parses";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Parser parser = new Parser();
        ArrayList<Judgment> judgments = new ArrayList<Judgment>();
        for (String string : parser.fileWalk(path))
        {
           // System.out.println(parser.readFile(path+"\\"+string));
            judgments.add(parser.parseFromString(parser.readFile(path+"\\"+string)));
        }
        //String line = reader.readLine();
    }
}
