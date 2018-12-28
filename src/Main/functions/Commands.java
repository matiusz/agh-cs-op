package Main.functions;

import Main.documentationDef.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.HashMap;

import static Main.documentationDef.Judgment.findBySignature;
import static Main.documentationDef.Judgment.judgmentJSONArray;

public class Commands
{
    String log = "";
    final static public String helptext =
            "List of commands:" +
                    "\r\nload <folder path>; [folder paths] - loads new judgments from a given folder(s)" +
                    "\r\njudge <judge name> - shows how many judgments given judge has" +
                    "\r\njudges - shows 10 judges with most judgments" +
                    "\r\nrubrum <id>; [ids] - shows a rubrum of a judgment with given Case Number" +
                    "\r\ncontent <id>- shows text content of a judgement with given id(s)" +
                    "\r\nregulations - shows regulations that are referenced most often" +
                    "\r\nmonths - shows how many judgments have been given each month"+
                    "\r\ncourts - shows how many judgments each court type has" +
                    "\r\njury - shows how many judgments each amount of judges has" +
                    "\r\nlog - show history of entered commands" +
                    "\r\nhelp (h) - show the list of available commands" +
                    "\r\nquit (q) - ends the program\r\n";
    String commandlist[] = new String[13];
    {
        commandlist[0]="load";
        commandlist[1]="judge";
        commandlist[2]="judges";
        commandlist[3]="rubrum";
        commandlist[4]="content";
        commandlist[5]="months";
        commandlist[6]="courts";
        commandlist[7]="jury";
        commandlist[8]="log";
        commandlist[9]="help";
        commandlist[10]="quit";
        commandlist[11]="h";
        commandlist[12]="q";
    }
    public String loadNewData(HashMap<Integer, Judgment> judgmentHashMap, String args) //load
    {
        String output = "";
        FileMethods fileMethods = new FileMethods();
        String argSplit[] = args.split("; ", -1);
        int count = 0;
        for (String arg : argSplit) {
  //          try {
                for (String string : fileMethods.fileWalk(arg)) {
                    if (string.contains(".json"))
                    {
                        for (Judgment judgment : judgmentJSONArray(arg + "\\" + string))
                        {
                            judgmentHashMap.put(judgment.getId(), judgment);
                            count++;
                        }
                    }
                    if (string.contains(".html"))
                    {
                        Document doc = Jsoup.parse(fileMethods.readFile(arg+"\\"+string), "UTF-8");
                        JudgmentBuilderHTML builderObject = new JudgmentBuilderHTML(doc);
                        Judgment judgment = new Judgment(builderObject);
                        judgmentHashMap.put(judgment.getId(), judgment);
                        count++;
                    }
                }
        }
        if (count==0) {output+="No matching files found";}
        else {output+=(count + " judgments(s) loaded successfully" + "\r\n");}
        return output;
    }
    public String judge(HashMap<Integer, Judgment> judgmentHashMap, String args) //judge [Name]
    {
        String output = "";
        int count = 0;
        for (Judgment judgment : judgmentHashMap.values())
        {
            for (int i=0;i<judgment.getJudges().length; i++)
            {
                if(args.equals(judgment.getJudges()[i].getName())) {count++;}
            }
        }
        if (count==0) return "This person has no judgments\r\n";
        output+=("Amount of judgments: " + count + "\r\n");
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
            output+=(judge.getName()+" - "+judge.getAmount()+" judgment(s)\r\n");
        }
        return output;
    }
    public String rubrum(HashMap<Integer, Judgment> judgmentHashMap, String args) //
    {
        String output = "";
        if (args.equals("")) {return "Function \"rubrum\" requires at least one argument\r\n";}
        String arguments[]=args.split("; ", -1);
        Judgment result;
        for (String arg:arguments)
        {
            try {
                result = findBySignature(judgmentHashMap, arg);
                output += result + "\n";
            } catch (IllegalArgumentException ex)
            {
                output += "Judgment " + arg + " not found" + "\r\n";
            }
        }
        return output;
    }
    public String showJudgementContent(HashMap<Integer, Judgment> judgmentHashMap, String args) //content
    {
        String output = "";
        Judgment result;
        try
        {
            result=findBySignature(judgmentHashMap, args);
        }
        catch (IllegalArgumentException ex)
        {
            return "Judgment not found"+"\r\n";
        }
        output+=result.showContent()+"\r\n";
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
            {output+=(i + " judge(s) - " + judgesAmount[i] + " judgment(s) - " + (float)(judgesAmount[i])*100/count + "% total\r\n");}
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
                output+=(month[i] + " judgment(s) - " + (float)(month[i])*100/count + "% total\r\n");
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
            output+=(CourtTypes.IntToString(i+1) + " - " + courts[i] + " judgment(s) - " + (float) (courts[i]) * 100 / count + "% total\r\n");
        }
        return output;
    }
    public String regulations(HashMap<Integer, Judgment> judgmentHashMap)
     {
            String output = "";
            HashMap<ReferencedRegulations, Integer> referencedRegulationsHashMap = new HashMap<>();
            for (Judgment judgment : judgmentHashMap.values())
            {
                for(ReferencedRegulations refReg : judgment.getReferencedRegulations())
                {
                    if (referencedRegulationsHashMap.containsKey(refReg))
                    {
                        referencedRegulationsHashMap.put(refReg,referencedRegulationsHashMap.get(refReg)+1);
                    }
                    else
                    {
                        referencedRegulationsHashMap.put(refReg, 0);
                    }
                }
            }
            ReferencedRegulations refRegs[] = new ReferencedRegulations[10];
            int count[] = new int[10];
            for (int i=0;i<10;i++)
            {
                count[i]=0;
            }
            for (ReferencedRegulations refReg : referencedRegulationsHashMap.keySet())
            {
                for (int i =0; i<10; i++)
                {
                    if(referencedRegulationsHashMap.get(refReg)>count[i])
                    {
                        for(int j=9;j>i;j--)
                        {
                            count[j]=count[j-1];
                            refRegs[j]=refRegs[j-1];
                        }
                        refRegs[i]=refReg;
                        count[i]=referencedRegulationsHashMap.get(refReg);
                        break;
                    }
                }
            }
            for(int i =0;i<10;i++)
            {
                output+=refRegs[i]+"\r\n";
            }
            return output;
        }
    public String command(String s, HashMap<Integer, Judgment> judgmentHashMap, HashMap<String, Judge> judgeHashMap)
    {
        log+=s+"\r\n";
        String input[] = s.split(" ", 2);
        String args = "";
        String command = input[0];
        command=command.toLowerCase();
        if (input.length!=1)
        {
            args=input[1];
        }
       switch (command) {
            case "":
                return "";
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
           case "regulations":
               return regulations(judgmentHashMap);
            case "help":
            case "h":
                return helptext;
            case "quit":
            case "q":
                return "qqq";
            default:
                {
                    return ("Invalid command, please try again\r\nSuggested commands: " + Levenshtein.suggest(command, commandlist) + "\r\n");
                }
        }
    }
}
