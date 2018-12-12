package Main.functions;


import Main.Main;

public class Dates
{
    public static int Year(String date)
    {
        String temp = "" + date.toCharArray()[0] + date.toCharArray()[1] + date.toCharArray()[2] + date.toCharArray()[3];
        return (Integer.parseInt(temp));
    }
    public static int Month(String date)
    {
        String temp = "" + date.toCharArray()[5] + date.toCharArray()[6];
        return (Integer.parseInt(temp));
    }
    public static int Day(String date)
    {
        String temp = "" + date.toCharArray()[8] + date.toCharArray()[9];
        return (Integer.parseInt(temp));
    }
}
