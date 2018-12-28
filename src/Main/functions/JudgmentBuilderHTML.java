package Main.functions;

import Main.documentationDef.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
public class JudgmentBuilderHTML
{
    public int id;
    public Judge judges[];
    public CourtTypes courtType;
    public CourtCase courtCases[];
    public Main.documentationDef.judgmentType judgmentType;
    public Source source;
    public String courtReporters[];
    public String decision;
    public String summary;
    public String textContent;
    public String legalBases;
    public ReferencedCourtCase referencedCourtCases[];
    public String receiptDate;
    public String meansOfAppeal;
    public String lowerCourtJudgments[];
    public personnelType personnelType;
    public String judgmentDate;
    public ReferencedRegulations referencedRegulations[];




    public JudgmentBuilderHTML(Document doc)
    {
        id=-1;
        Element titleEl = doc.selectFirst("TITLE");
        String titleParts[] = titleEl.text().split(" - ", 2);
        String title = titleParts[0];
        this.courtCases=new CourtCase[1];
        this.courtCases[0] = new CourtCase(title);
        Element contentEl = null;
        try {
            contentEl = doc.select("span.info-list-value-uzasadnienie").get(1);
            String content = contentEl.html();
            content=content.replace("<p>", "\n");
            content=content.replace("</p>", "");
            if (content.contains("UZASADNIENIE")) {content = content.split("UZASADNIENIE", 2)[1];}
            if (content.contains("Uzasadnienie")) {content = content.split("Uzasadnienie", 2)[1];}
            textContent=content;
        }
        catch (IndexOutOfBoundsException ex)
        {
            textContent = "";
        }
        try {
            Element judgesEl = doc.selectFirst("table.info-list").selectFirst("tr.niezaznaczona:contains(sędziowie)").selectFirst("td.info-list-value");
            String judgesString = judgesEl.html();
            String judgesStrings[]= judgesString.split("<br>", -1);
            this.judges = new Judge[judgesStrings.length];
            for (int i=0;i<judgesStrings.length;i++)
            {
                String[] judgeString=judgesStrings[i].split(" /");
                Judge judge = new Judge();
                judge.setName(judgeString[0]);
                if (judgeString.length>1)
                {
                    String rolesStrings[] = judgeString[1].split(" ", -1);
                    Judge.SpecialRoles[] roles = new Judge.SpecialRoles[rolesStrings.length];
                    rolesStrings[rolesStrings.length-1]=rolesStrings[rolesStrings.length-1].substring(0, rolesStrings.length-1);
                    for (int j=0; j<rolesStrings.length; j++)
                    {
                        roles[j]=Judge.stringToEnum(rolesStrings[j]);
                    }
                    judge.setSpecialRoles(roles);
                }
                else
                {
                    judge.setSpecialRoles(new Judge.SpecialRoles[0]);
                }
                this.judges[i]=judge;
            }
        }
        catch (NullPointerException ex)
        {
            judges=new Judge[0];
        }
        Element dateEL = doc.selectFirst("tr.niezaznaczona").selectFirst("td.info-list-value");
        String date = dateEL.selectFirst("td").text().substring(0, 10);
        this.judgmentDate=date;
        String courtType = titleParts[1];
        if (courtType.contains("WSA")||courtType.contains("NSA")) this.courtType=CourtTypes.ADMINISTRATIVE;
        else this.courtType=CourtTypes.INVALID;
        try {
            Element referencedEl = doc.selectFirst("table.info-list").selectFirst("tr.niezaznaczona:contains(powołane przepisy)").selectFirst("td.info-list-value");
            String titletext = referencedEl.selectFirst("span").text();
            Element link = referencedEl.selectFirst("a");
            String references = link.html();
            references = references.replaceAll("Dz.U. ", ">");
            references = references.replaceAll(" nr ", ">");
            references = references.replaceAll(" poz ", ">");

            references = references.replaceAll(" poz. ", ">");
            String[] refParts = references.split(">", -1);
            this.referencedRegulations = new ReferencedRegulations[1];
            this.referencedRegulations[0] = new ReferencedRegulations(titletext, Integer.parseInt(refParts[1]), Integer.parseInt(refParts[2]), Integer.parseInt(refParts[3]), "" );
            //System.out.println(references);
        }
        catch (NullPointerException ex)
        {
            referencedRegulations = new ReferencedRegulations[0];
        }
    }
}
