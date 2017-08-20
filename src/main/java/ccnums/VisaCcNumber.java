package ccnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by simontowler on 17/08/2017.
 */
class VisaCcNumber extends CcNumber {
    //variables
    static final String issuer = "Visa";//TODO make constants for issuer strings

    //constructor
    VisaCcNumber(String number) {
        setNumber(number);
        setGrouping(getNumber());
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String getNumber() {
        return(number);
    }

    @Override
    public String getIssuer() {
        return issuer;
    }

    private String getGroupingPropertyName(String number) {
        return getIssuer() + "." + number.length();
    }

    private String getGroupingReplacement() {
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
    private void setGrouping(String number) {
        grouping = CcNumber.groupingPatterns.getProperty(getGroupingPropertyName(number));
    }

    @Override
    public String getNumberGrouped() {
            return number.replaceAll(grouping, getGroupingReplacement());
    }

}
