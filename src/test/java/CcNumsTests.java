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
    /*
    *  Also tests masking and grouping.
    */
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
    public void testLuhn() {
        assertTrue(ccnums.CcNumsValidator.passesLuhnCheck("49927398716"));
        assertFalse(ccnums.CcNumsValidator.passesLuhnCheck("49927398717"));
    }

}
