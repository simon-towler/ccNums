package ccnums;

import java.util.Properties;
import java.io.FileInputStream;
import java.lang.System;
import java.io.FileNotFoundException;
import java.io.IOException;

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
        return ccnums.CcNumIssuerValidator.getIssuerValidFor(number);
    }

    public static CcNumber getCcNumber(String number) {
        CcNumber ccNumber;
        String issuer = getCcIssuer(number);
        if (null != issuer) {
            if (issuer.equals("AmericanExpress")) {
                ccNumber = new AmexCcNumber(number);
            } else {
                    ccNumber = new CcNumber(number);
            }
            return ccNumber;
        }
        return null;
    }
}