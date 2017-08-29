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
    public void testTruncate() throws Exception {

        // fixture
        String testNumber;
        CcNumber numberObj;
        final TruncationStrategy factaTruncator;
        final TruncationStrategy dssTruncator;
        String truncateResponse;
        String maskResponse;

        //setup
        factaTruncator = new TruncateFactaFour();
        dssTruncator = new TruncateDssSixAndFour();

        // Amex setup
        testNumber = "376039019752033";//amex australia card
        numberObj = CcNums.getCcNumber(testNumber);
        numberObj.setTruncator(factaTruncator);

        // Amex truncate facta test
        System.out.println();
        System.out.println("FACTA TRUNCATION OF AMEX NUMBER " + testNumber);
        truncateResponse = numberObj.truncate();
        System.out.println("Amex truncated to FACTA 4 is: " + truncateResponse);
        assertTrue(truncateResponse.equals("*2033"));
        System.out.println();

        // Amex mask facta test
        System.out.println("FACTA MASKING OF AMEX NUMBER " + testNumber);
        maskResponse = numberObj.mask();
        System.out.println("Amex masked to FACTA 4 is: " + maskResponse);
        assertTrue(maskResponse.equals("**** ****** *2033"));
        System.out.println();

        // Amex truncate dss test
        System.out.println("DSS TRUNCATION OF AMEX NUMBER " + testNumber);
        numberObj.setTruncator(dssTruncator);
        truncateResponse = numberObj.truncate();
        System.out.println("Amex truncated to PCI DSS is: " + truncateResponse);
        assertTrue(truncateResponse.equals("376039*2033"));
        System.out.println();

        // Amex mask dss test
        System.out.println("DSS MASKING OF AMEX NUMBER " + testNumber);
        maskResponse = numberObj.mask();
        System.out.println("Amex masked to PCI DSS is: " + maskResponse);
        assertTrue(maskResponse.equals("3760 39**** *2033"));
        System.out.println();

        // Visa 16 setup
        testNumber = "4012888888881881";
        // reset truncation behaviour for all CcNumbers
        numberObj.setTruncator(factaTruncator);
        numberObj = CcNums.getCcNumber(testNumber);

        // Visa 16 truncate facta test
        System.out.println("FACTA TRUNCATION OF VISA 16-DIGIT NUMBER " + testNumber);
        truncateResponse = numberObj.truncate();
        System.out.println("Visa 16 truncated to FACTA 4 is: " + truncateResponse);
        assertTrue(truncateResponse.equals("*1881"));
        System.out.println();

        // Visa 16 mask facta test
        System.out.println("FACTA MASKING OF VISA 16-DIGIT NUMBER " + testNumber);
        maskResponse = numberObj.mask();
        System.out.println("Visa 16 masked to FACTA 4 is: " + maskResponse);
        assertTrue(maskResponse.equals("**** **** **** 1881"));
        System.out.println();

        // Visa 16 truncate dss test
        System.out.println("DSS TRUNCATION OF VISA 16-DIGIT NUMBER " + testNumber);
        numberObj.setTruncator(dssTruncator);
        truncateResponse = numberObj.truncate();
        System.out.println("Visa 16 truncated to PCI DSS is: " + truncateResponse);
        assertTrue(truncateResponse.equals("401288*1881"));
        System.out.println();

        // Visa 16 mask dss test
        System.out.println("DSS MASKING OF VISA 16-DIGIT NUMBER " + testNumber);
        maskResponse = numberObj.mask();
        System.out.println("Visa 16 masked to PCI DSS is: " + maskResponse);
        assertTrue(maskResponse.equals("4012 88** **** 1881"));
        System.out.println();

        // Visa 13 setup
        testNumber = "4222222222222";
        // reset truncation behaviour for all CcNumbers
        numberObj.setTruncator(factaTruncator);
        numberObj = CcNums.getCcNumber(testNumber);

        // Visa 13 truncate facta test
        System.out.println("FACTA TRUNCATION OF VISA 13-DIGIT NUMBER " + testNumber);
        truncateResponse = numberObj.truncate();
        System.out.println("Visa 13 truncated to FACTA 4 is: " + truncateResponse);
        assertTrue(truncateResponse.equals("*2222"));
        System.out.println();

        // Visa 13 mask facta test
        System.out.println("FACTA MASKING OF VISA 13-DIGIT NUMBER " + testNumber);
        maskResponse = numberObj.mask();
        System.out.println("Visa 13 masked to FACTA 4 is: " + maskResponse);
        assertTrue(maskResponse.equals("**** *** **2 222"));
        System.out.println();

        // Visa 13 truncate dss test
        System.out.println("DSS TRUNCATION OF VISA 13-DIGIT NUMBER " + testNumber);
        numberObj.setTruncator(dssTruncator);
        truncateResponse = numberObj.truncate();
        System.out.println("Visa 13 truncated to PCI DSS is: " + truncateResponse);
        assertTrue(truncateResponse.equals("422222*2222"));
        System.out.println();

        // Visa 13 mask dss test
        System.out.println("DSS MASKING OF VISA 13-DIGIT NUMBER " + testNumber);
        maskResponse = numberObj.mask();
        System.out.println("Visa 13 masked to PCI DSS is: " + maskResponse);
        assertTrue(maskResponse.equals("4222 22* **2 222"));
        System.out.println();
    }
    
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
        System.out.println("When the Visa credit card number is: " + visaNumber02.getNumber());
        assertTrue(visaNumber02.getNumber().equals(visaTestNumber02));
        //TODO remove
        System.out.println("That Visa number when grouped is: " + visaNumber02.getNumberGrouped());
        assertTrue(visaNumber02.getNumberGrouped().equals("4012 8888 8888 1881"));

        //VISA 13 NUMBER TEST
        CcNumber visaNumber03 = CcNums.getCcNumber(visaTestNumber03);
        assertTrue(visaNumber03 != null);
        assertTrue(visaNumber03.getIssuer().equals("Visa"));


        //TODO remove
        System.out.println("And when the Visa credit card number is: " + visaNumber03.getNumber());
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
