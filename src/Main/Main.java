package Main;

import java.io.IOException;

public class Main
{
    public static void main(String args[])
    {
        String path = "C:\\Users\\Mateusz\\Desktop\\parses";
        Parser parser = new Parser();
        for (String string : parser.fileWalk(path))
        {
            System.out.println(parser.readFile(path+"\\"+string));
        }
    }
}
