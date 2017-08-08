package ccnums;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static ccnums.CcNumsValidator.numberIsValid;

public class CcNums {

    public static void main(String [] args) throws FileNotFoundException, IOException {
        // create and load properties
        // modeled after https://docs.oracle.com/javase/tutorial/essential/environment/properties.html
        Properties issuersCcNumPatterns = new Properties();
        FileInputStream in = new FileInputStream("../resources/issuer_ccnum_patterns.properties");
        issuersCcNumPatterns.load(in);
        in.close();
    }

    public static String getCcIssuer(String number) {
        return ccnums.CcNumsValidator.getIssuer(number);
    }

    public static CcNumber getCcNumber(String number) {
        if (numberIsValid(number)) return CcNumber.createNumber(number);
        //TODO what if the number is not valid? how to tell the user
        return null;
    }
}