package Main.functions;

import Main.documentationDef.CourtTypes;
import Main.documentationDef.Judge;
import Main.documentationDef.Judgment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import static Main.documentationDef.Judgment.judgmentArray;

public class Commands
{
    String log = "";
    final static public String helptext =
            "List of commands:" +
                    "\nload <folder path> [folder paths] - loads new judgments from a given folder(s)" +
                   // "\njudgmentById (byID) - shows judgment with given id" +
                    "\njudge <judge name> - shows how many judgments given judge has" +
                    "\njudges - shows 10 judges with most judgments" +
                    "\nrubrum <id> [ids] - shows a rubrum of a judgment with given id(s)" +
                    "\ncontent <id> [ids]- shows text content of a judgement with given id(s)" +
                    "\nmonths - shows how many judgments have been given each month"+
                    "\ncourts - shows how many judgments each court type has" +
                    "\njury - shows how many judgments each amount of judges has" +
                    "\nlog - show history of entered commands" +
                    "\nhelp (h) - show the list of available commands" +
                    "\nquit (q) - ends the program\n";
    public String loadNewData(HashMap<Integer, Judgment> judgmentHashMap, String[] args) //load
    {
        String output = "";
        FileMethods fileMethods = new FileMethods();
        int count = 0;
        for (String arg : args) {
            try
            {
                for (String string : fileMethods.fileWalk(arg)) {
                    System.out.println(arg + "\\" + string);
                    for (Judgment judgment : judgmentArray(arg + "\\" + string)) {
                        judgmentHashMap.put(judgment.getId(), judgment);
                        count++;
                    }
                }
            }
            catch (NullPointerException ex)
            {
                output+="Invalid directory, no files loaded\n";
                return output;
            }
        }
        output+=(count+" judgments(s) loaded successfully" + '\n');
        return output;
    }
    public String judge(HashMap<Integer, Judgment> judgmentHashMap, String[] args) //judge [Name]
    {
        String output = "";
        int count = 0;
        String name = "";
        if (args.length==0) return "Command \"judge\" requires an argument\n";
        for (String arg:args)
        {
            if (name!="") {name+=" ";}
            name+=arg;
        }
        for (Judgment judgment : judgmentHashMap.values())
        {
            for (int i=0;i<judgment.getJudges().length; i++)
            {
                if(name.equals(judgment.getJudges()[i].getName())) {count++;}
            }
        }
        if (count==0) return "This person has no judgments\n";
        output+=("Amount of judgments: " + count + '\n');
        return output;

    }
    public String judges(HashMap<Integer, Judgment> judgmentHashMap) //judges
    {
        String output = "";
        HashMap<String, Judge> judgeHashMap = new HashMap<>();
        for (Judgment judgment : judgmentHashMap.values())
        {
            for (Judge judge : judgment.getJudges())
            {
                if(judgeHashMap.containsKey(judge.getName()))
                {
                    judgeHashMap.get(judge.getName()).setAmount(judgeHashMap.get(judge.getName()).getAmount()+1);
                }
                else
                {
                    judgeHashMap.put(judge.getName(), judge);
                    judge.setAmount(1);
                }
            }
        }
        Judge most[] = new Judge[10];
        for (int i = 0; i<10; i++)
        {
           most[i] = new Judge();
        }
        for (Judge judge : judgeHashMap.values())
        {
            for (int i=0;i<10;i++)
            {
                if (judge.getAmount()>most[i].getAmount())
                {
                    for (int j=9; j>i; j--)
                    {
                        most[j]=most[j-1];
                    }
                    most[i]=judge;
                    break;
                }
            }
        }
        for (Judge judge : most)
        {
            output+=(judge.getName()+" - "+judge.getAmount()+" judgment(s)\n");
        }
        return output;
    }
    public String rubrum(HashMap<Integer, Judgment> judgmentHashMap, String[] args) //
    {
        String output = "";
        int targetId;
        Judgment[] judgmentArray = new Judgment[args.length];
        for (int i = 0; i<args.length;i++)
        {
            try
            {
                targetId = Integer.parseInt(args[i]);
            }
            catch (NumberFormatException ex)
            {
                return "Parameters have to be numbers\n";
            }
            judgmentArray[i]=(judgmentHashMap.get(targetId));
        }
        for (Judgment judgment:judgmentArray)
        {
            output+=judgment+"\n";
        }
        return output;
    }
    public String showJudgementContent(HashMap<Integer, Judgment> judgmentHashMap, String[] args) //content
    {
        String output = "";
        int targetId;
        Judgment[] judgmentArray = new Judgment[args.length];
        for (int i = 0; i<args.length;i++)
        {
            try
            {
                targetId = Integer.parseInt(args[i]);
            }
            catch (NumberFormatException ex)
            {
                return "Parameters have to be numbers";
            }
            judgmentArray[i]=(judgmentHashMap.get(targetId));
        }
        for (Judgment judgment:judgmentArray)
        {
            if (judgment==null)
                output+="null\n";
            else
            output+=(judgment.showContent() + '\n');
        }
        return output;
    }
    public String jury(HashMap<Integer, Judgment> judgmentHashMap) //jury
    {
        String output = "";
        int judgesAmount[] = new int[20];
        int count = 0;
        for (int i = 0; i < 20; i++) {
            judgesAmount[i] = 0;
        }
        for (Judgment judgment : judgmentHashMap.values()) {
            judgesAmount[judgment.getJudges().length]++;
            count++;
        }
        for (int i = 0; i < 20; i++) {
            if(judgesAmount[i]!=0)
            {output+=(i + " judge(s) - " + judgesAmount[i] + " judgment(s) - " + (float)(judgesAmount[i])*100/count + "% total\n");}
        }
        return output;
    }
    public String months(HashMap<Integer, Judgment> judgmentHashMap) //months
    {
        String output = "";
        int month[] = new int[12];
        int count = 0;
        for (int i = 0; i < 12; i++)
        {
            month[i] = 0;
        }
        for (Judgment judgment : judgmentHashMap.values()) {
            month[Dates.Month(judgment.getJudgmentDate())-1]++;
            count++;
        }
        for (int i = 0; i < 12; i++) {
            if(month[i]!=0)
            {
                switch (i)
                {
                case 0: output+=("January - "); break;
                case 1: output+=("February - "); break;
                case 2: output+=("March - "); break;
                case 3: output+=("April - "); break;
                case 4: output+=("May - "); break;
                case 5: output+=("June - "); break;
                case 6: output+=("July - "); break;
                case 7: output+=("August - "); break;
                case 8: output+=("September - "); break;
                case 9: output+=("October - "); break;
                case 10: output+=("November - "); break;
                case 11: output+=("December - "); break;
                }
                output+=(month[i] + " judgment(s) - " + (float)(month[i])*100/count + "% total\n");
            }
        }
        return output;
    }
    public String courts(HashMap<Integer, Judgment> judgmentHashMap) //courts
    {
        String output="";
        int courts[] = new int[5];
        int count = 0;
        for (int i = 0; i < 5; i++)
        {
            courts[i] = 0;
        }
        for (Judgment judgment : judgmentHashMap.values()) {
            courts[judgment.getCourtType().enumToInt()-1]++;
            count++;
        }
        for (int i = 0; i < 5; i++) {
            output+=(CourtTypes.IntToString(i+1) + " - " + courts[i] + " judgment(s) - " + (float) (courts[i]) * 100 / count + "% total\n");
        }
        return output;
    }
    public String command(String s, HashMap<Integer, Judgment> judgmentHashMap, HashMap<String, Judge> judgeHashMap)
    {
        log+=s+'\n';
        String input[] = s.split(" ", -1);
        String command = input[0];
        String args[] = Arrays.copyOfRange(input, 1, input.length);
        switch (command)
        {
            case "loadNewData":
            case "load":
                return loadNewData(judgmentHashMap, args);
            case "rubrum":
                return rubrum(judgmentHashMap, args);
            case "months":
                return months(judgmentHashMap);
            case "courts":
                return courts(judgmentHashMap);
            case "jury":
                return jury(judgmentHashMap);
            case "judge":
                return judge(judgmentHashMap, args);
            case "judges":
                return judges(judgmentHashMap);
            case "content":
                return showJudgementContent(judgmentHashMap, args);
            case "log":
                return log;
            case "help":
            case "h":
                return helptext;
            case "quit":
            case "q":
                System.out.println("Thank you for using this program");
                System.exit(0);
            default:
                return("Invalid command, please try again\nPress h to show the list of available commands\n");
            }
    }
}

