package ccnums;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static ccnums.CcNumsValidator.numberIsValid;

public abstract class CcNumber {
    // constants
    static final String groupDelimiter = " ";
    static final String maskingChar = "*";


    // variables
    static Properties groupingPatterns;

    // instance variables
    String number;
    String issuer;
    String grouping;
    int[] validLengths;

    /*
     * Default truncator for all instances derived from CcNumber.
     * Changing the value of this in any instance at run time
     * will change truncation behaviour for all CcNumbers.
     */
    static TruncationStrategy truncator = new TruncateDssSixAndFour();

    public void setTruncator(TruncationStrategy truncator) {
        this.truncator = truncator;
    }

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

    // instance methods

    void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return(number);
    }

    void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    void setValidLengths(int[] lengths) {
        validLengths = lengths;
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

    public String truncate() {
        return truncator.truncate(this);
    }

    public String mask() throws Exception {
        return truncator.mask(this);
    }

    /**
     * Sets the regex that represents the grouping pattern
     * for this card number.
     */
    void setGrouping(String number) {
        grouping = CcNumber.groupingPatterns.getProperty(getGroupingPropertyName(number));
    }

    public String getNumberGrouped() {
        return groupNumber(this);
    }

    String groupNumber(CcNumber number) {
        return number.getNumber().replaceAll(grouping, getGroupingReplacement());
    }

    String groupNumber(String number) throws Exception {
        if (isValidLength(number)) {
            String regex = grouping.replaceAll("d", "d|\\.");// TODO this works for happy path, but may not be safe
            return number.replaceAll(regex, getGroupingReplacement());
        } else {
            throw new Exception("Can't group number. Invalid length."); // TODO improve
        }
    }

    boolean isValidLength(String number) {
        return IntStream.of(validLengths).anyMatch(x -> x == number.length());
    }

    public String getIssuer() {
        return issuer;
    }

    public String getMaskingChar() {
        return maskingChar;
    }

    @Override
    public String toString() {
        return getNumberGrouped();
    }

}