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

        String testNumber = "376039019752033";//amex australia card
        
        CcNumber number = CcNums.getCcNumber(testNumber);

        assertTrue(number.getIssuer().equals("AmericanExpress"));

        assertTrue(number != null);
        //TODO remove
            System.out.println("The credit card number is: " + number.getNumber());
        assertTrue(number.getNumber().equals(testNumber));
        //TODO remove
            //System.out.println(number.getGroupingPattern());
            System.out.println("That number when grouped is: " + number.getNumberGrouped());
        assertTrue(number.getNumberGrouped().equals("3760 390197 52033"));
    }

    @Test
    public void testLuhn() {
        assertTrue(ccnums.CcNumsValidator.passesLuhnCheck("49927398716"));
        assertFalse(ccnums.CcNumsValidator.passesLuhnCheck("49927398717"));
    }

}
