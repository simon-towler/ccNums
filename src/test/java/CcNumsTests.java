package ccnums;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 *
 * @author Simon
 */
public class CcNumsTests {
    
    @Test
    public void testCcNums() {

        String amexTestNumber = "376039019752033";//amex australia card
        // Test numbers from https://www.paypalobjects.com/en_US/vhelp/paypalmanager_help/credit_card_numbers.htm accessed 17aug17
        String visaTestNumber01 = "4111111111111111";
        String visaTestNumber02 = "4012888888881881";
        String visaTestNumber03 = "4222222222222";
        
        CcNumber amexNumber01 = CcNums.getCcNumber(amexTestNumber);

        assertTrue(amexNumber01.getIssuer().equals("AmericanExpress"));

        assertTrue(amexNumber01 != null);
        //TODO remove
            System.out.println("The Amex credit card number is: " + amexNumber01.getNumber());
        assertTrue(amexNumber01.getNumber().equals(amexTestNumber));
        //TODO remove
            //System.out.println(number.getGroupingPattern());
            System.out.println("That Amex number when grouped is: " + amexNumber01.getNumberGrouped());
        assertTrue(amexNumber01.getNumberGrouped().equals("3760 390197 52033"));


        //VISA 16 NUMBER TEST
        CcNumber visaNumber02 = CcNums.getCcNumber(visaTestNumber02);
        assertTrue(visaNumber02 != null);
        assertTrue(visaNumber02.getIssuer().equals("Visa"));


        //TODO remove
        System.out.println("The Visa credit card number is: " + visaNumber02.getNumber());
        assertTrue(visaNumber02.getNumber().equals(visaTestNumber02));
        //TODO remove
        System.out.println("That Visa number when grouped is: " + visaNumber02.getNumberGrouped());
        assertTrue(visaNumber02.getNumberGrouped().equals("4012 8888 8888 1881"));

        //VISA 13 NUMBER TEST
        CcNumber visaNumber03 = CcNums.getCcNumber(visaTestNumber03);
        assertTrue(visaNumber03 != null);
        assertTrue(visaNumber03.getIssuer().equals("Visa"));


        //TODO remove
        System.out.println("The Visa credit card number is: " + visaNumber03.getNumber());
        assertTrue(visaNumber03.getNumber().equals(visaTestNumber03));
        //TODO remove
        System.out.println("That Visa number when grouped is: " + visaNumber03.getNumberGrouped());
        assertTrue(visaNumber03.getNumberGrouped().equals("4222 222 222 222"));
    }

    @Test
    public void testLuhn() {
        assertTrue(ccnums.CcNumsValidator.passesLuhnCheck("49927398716"));
        assertFalse(ccnums.CcNumsValidator.passesLuhnCheck("49927398717"));
    }

}
