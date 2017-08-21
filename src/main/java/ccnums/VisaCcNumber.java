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

}
