package Main.documentationDef;

public class DissentingOpinions
{
    String textContent;
    String authors[];

    public String getTextContent() {
        return textContent;
    }

    public String[] getAuthors() {
        return authors;
    }

    public DissentingOpinions(String content)
    {
        textContent = content;
    }
    public DissentingOpinions(String content, String authors[])
    {
        textContent = content;
        this.authors = authors;
    }
}
