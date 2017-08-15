package ccnums;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static ccnums.CcNumsValidator.numberIsValid;

public abstract class CcNumber {

    final Boolean valid = false;
    String number = null;
    private static String issuer;

    String grouping;

    //instance variables
    static Properties groupingPatterns;

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
            }
        }
        //TODO don't just return null here
        return null;
    }

    public abstract void setNumber(String number);

    public String getNumber() {
        return(this.number);
    }

    public String getNumberGrouped() {
        return number.replaceAll(grouping, "$1 $2 $3");
    }
    //TODO remove
    String getGroupingPattern() {return grouping;}

    public String getIssuer() {
        return issuer;
    }

}