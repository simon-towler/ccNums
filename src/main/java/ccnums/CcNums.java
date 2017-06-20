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
        System.out.println("Done");//TODO: remove
    }

    public static CcNumber getCcNumber(String number) {
        if (!CcNumValidator.isValid(number)) {
            return new CcNumber(number);
        }
        return new CcNumber(number);
    }
}