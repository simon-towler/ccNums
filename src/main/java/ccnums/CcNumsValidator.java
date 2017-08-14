package ccnums;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CcNumsValidator {
    //instance variables
    static Properties ccNumPatterns;

    // create and load properties
    // modeled after https://docs.oracle.com/javase/tutorial/essential/environment/properties.html
    static {
        try {
            ccNumPatterns = new Properties();
            //TODO make the path independent of my machine
            FileInputStream in = new FileInputStream("/Users/simontowler/IdeaProjects/ccNums/src/main/resources/issuer_ccnum_patterns.properties");
            ccNumPatterns.load(in);
            in.close();
        } catch(IOException e) {
            //TODO improve
            System.out.println(e.getMessage());
        }
    }

    public static boolean numberIsValid(String number) {
        //returns true is number is valid for an issuer
        //and passes a Luhn check
        if(!passesLuhnCheck(number)) return false;
        if (null == getIssuer(number)) return false;
        return true;
    }

    static String getIssuer(String number) {
        for (String propertyName : ccNumPatterns.stringPropertyNames()) {
            Pattern pattern =
                    Pattern.compile(ccNumPatterns.getProperty(propertyName));
            Matcher matcher =
                    pattern.matcher(number);
            if (matcher.matches()) {
                return propertyName;
            }
        }
        //TODO replace
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