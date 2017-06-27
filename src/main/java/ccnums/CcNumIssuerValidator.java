package ccnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CcNumIssuerValidator {

    public static String getIssuerValidFor(String number) {

        //TODO identify the issuer and
        // check if the number is valid for the issuer
        final Pattern amexPattern =
                Pattern.compile("^3[47][0-9]{13}$");
        Matcher matcher =
                amexPattern.matcher(number);
        if(matcher.matches()) {
            return "AmericanExpress";
        }

        return null;
    }

}