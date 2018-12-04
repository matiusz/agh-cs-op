package Main.documentationDef;

import java.util.Arrays;

import static Main.documentationDef.Judge.SpecialRoles.*;

public class Judge
{
    public enum SpecialRoles
    {
        PRESIDING_JUDGE,
        REPORTING_JUDGE,
        REASONS_FOR_JUDGEMENT_AUTHOR,
        NULL;

        @Override
        public String toString() {
            switch (this)
            {
                case PRESIDING_JUDGE:
                    return "PRESIDING_JUDGE";
                case REPORTING_JUDGE:
                    return "REPORTING_JUDGE";
                case REASONS_FOR_JUDGEMENT_AUTHOR:
                    return "REASONS_FOR_JUDGEMENT_AUTHOR";
                default:
                    return "";
            }
        }
    }


    String name;
    String function;

    public SpecialRoles[] getSpecialRoles() {
        return specialRoles;
    }

    SpecialRoles specialRoles[];

    public String getName() {
        return name;
    }

    public String getFunction() {
        return function;
    }

    public static SpecialRoles stringToEnum(String s)
    {
        switch (s)
        {
            case "PRESIDING_JUDGE":
                return PRESIDING_JUDGE;
            case "REPORTING_JUDGE":
                return REPORTING_JUDGE;
            case "REASONS_FOR_JUDGEMENT_AUTHOR":
                return REASONS_FOR_JUDGEMENT_AUTHOR;
                default:
                    return NULL;
        }
    }
    public String toString()
    {
        return (name+" - "+ Arrays.toString(specialRoles));
    }


}
