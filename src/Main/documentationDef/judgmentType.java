package Main.documentationDef;

public enum judgmentType
{
    DECISION,
    RESOLUTION,
    SENTENCE,
    REGULATION,
    REASONS;
    public static judgmentType stringToEnum(String s)
    {
        switch (s)
        {
            case "DECISION":
                return DECISION;
            case "RESOLUTION":
                return RESOLUTION;
            case "SENTENCE":
                return SENTENCE;
            case "REGULATION":
                return REGULATION;
            case "REASONS":
                return REASONS;
            default:
                return DECISION;
        }
    }
}
