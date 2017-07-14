package ccnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CcNumIssuerValidator {

    public static boolean numberIsValid(String number) {
        //returns true is number is valid for an issuer
        //and passes a Luhn check
        if(!passesLuhnCheck(number)) return false;
        if (null == getIssuerValidFor(number)) return false;
        return true;
    }

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

    //Method taken from http://rosettacode.org/wiki/Luhn_test_of_credit_card_numbers#Java, accessed 11 July 2017
    public static boolean passesLuhnCheck(String number){
        int s1 = 0, s2 = 0;
        String reverse = new StringBuffer(number).reverse().toString();
        for(int i = 0 ;i < reverse.length();i++){
            int digit = Character.digit(reverse.charAt(i), 10);
            if(i % 2 == 0){//this is for odd digits, they are 1-indexed in the algorithm
                s1 += digit;
            }else{//add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
                s2 += 2 * digit;
                if(digit >= 5){
                    s2 -= 9;
                }
            }
        }
        return (s1 + s2) % 10 == 0;
    }

}