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
    SpecialRoles specialRoles[];

    public void setName(String name) {
        this.name = name;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public void setSpecialRoles(SpecialRoles[] specialRoles)
    {
        this.specialRoles = specialRoles;
    }

    int amount = 0;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public SpecialRoles[] getSpecialRoles() {
        return specialRoles;
    }


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
    public static String arrayToString(Judge[] judges)
    {
        String result = "";
        for (Judge judge: judges)
        {
            result+=judge+", ";
        }
        return result;
    }

}
