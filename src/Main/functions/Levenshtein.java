package Main.functions;

public class Levenshtein
{
    public static int levDistance(String a, String b) //Algorithm from: https://pl.wikipedia.org/wiki/Odleg%C5%82o%C5%9B%C4%87_Levenshteina
    {
        int cost;
        int levArray[][] = new int[a.length()][b.length()];
        for (int i=0; i<a.length();i++)
        {
            levArray[i][0]=i;
        }
        for (int i=0; i<b.length();i++)
        {
            levArray[0][i]=i;
        }
        for (int i = 1; i<a.length();i++)
        {
            for (int j = 1;j<b.length();j++)
            {
                if (a.toCharArray()[i]==b.toCharArray()[j]) {cost=0;} else {cost=1;}
                levArray[i][j]=Math.min(Math.min(levArray[i-1][j]+1, levArray[i][j-1]+1), levArray[i-1][j-1]+cost);
            }
        }
        return levArray[a.length()-1][b.length()-1];
    }
    public static String suggest(String s, String[] list)
    {
        int a;
        String suggested[] = new String[3];
        int suggestedDistances[] = new int[3];
        for (int i =0;i<3;i++)
        {
            suggested[i]=null;
            suggestedDistances[i]=100;
        }
        for (String check : list)
        {
            a=levDistance(s, check);
            for(int i =0;i<3;i++)
            {
                if (suggestedDistances[i]>a)
                {
                    for (int j=2;j>i;j--)
                    {
                        suggestedDistances[j]=suggestedDistances[j-1];
                        suggested[j]=suggested[j-1];
                    }
                    suggestedDistances[i]=a;
                    suggested[i]=check;
                    break;
                }
            }
        }
        String result = "";
        for (String str:suggested)
        {
            if (result!="") result+=", ";
            result+=str;
        }
        return result;
    }
}