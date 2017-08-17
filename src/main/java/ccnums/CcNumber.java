package ccnums;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static ccnums.CcNumsValidator.numberIsValid;

public abstract class CcNumber {
    // instance variables
    String number = null;
    static String issuer;
    String grouping;
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

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return(number);
    }

    abstract String getNumberGrouped();

    public String getIssuer() {
        return issuer;
    }

}