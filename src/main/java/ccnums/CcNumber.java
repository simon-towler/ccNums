package ccnums;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ccnums.CcNumsValidator.numberIsValid;

public abstract class CcNumber {
    // constants
    static final String groupDelimiter = " ";


    // variables
    static Properties groupingPatterns;

    // instance variables
    String number;
    String issuer;
    String grouping;

    // create and load properties
    // modeled after https://docs.oracle.com/javase/tutorial/essential/environment/properties.html
    static {
        try {
            groupingPatterns = new Properties();
            //TODO make the path independent of my machine
            FileInputStream in = new FileInputStream("/Users/simontowler/IdeaProjects/ccNums/src/main/resources/grouping_patterns.properties");
            groupingPatterns.load(in);
            in.close();
        } catch(IOException e) {
            //TODO improve
            System.out.println(e.getMessage());
        }
    }

    //factory method
    static CcNumber createNumber(String number) {
        CcNumber numberObj;

        if (numberIsValid(number)) {
            switch (ccnums.CcNumsValidator.getIssuer(number)) {
                case "AmericanExpress":
                    numberObj = new AmexCcNumber(number);
                    return numberObj;
                case "Visa":
                    numberObj = new VisaCcNumber(number);
                    return numberObj;
            }
        }
        //TODO don't just return null here
        return null;
    }

    void setNumber(String number) {
        this.number = number;
    }

    void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getNumber() {
        return(number);
    }

    String getGroupingPropertyName(String number) {
        return getIssuer() + "." + number.length();
    }

    String getGroupingReplacement() {
        String replacement = "";
        Pattern pattern =
                Pattern.compile(grouping);
        Matcher matcher =
                pattern.matcher(number);
        for (int i = 1; i <= matcher.groupCount(); i++) {
            replacement += "$" + i;
            if (i < matcher.groupCount()) replacement += groupDelimiter;
        }
        return replacement;
    }

    /**
     * Sets the regex that represents the grouping pattern
     * for this card number.
     */
    void setGrouping(String number) {
        grouping = CcNumber.groupingPatterns.getProperty(getGroupingPropertyName(number));
    }

    public String getNumberGrouped() {
        return number.replaceAll(grouping, getGroupingReplacement());
    }

    public String getIssuer() {
        return issuer;
    }

}