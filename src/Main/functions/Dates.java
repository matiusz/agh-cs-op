package Main.functions;

public class Dates
{
    public static int Month(String date)
    {
        String temp = "" + date.toCharArray()[5] + date.toCharArray()[6];
        return (Integer.parseInt(temp));
    }
}
