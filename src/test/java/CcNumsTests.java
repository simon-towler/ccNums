package ccnums;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Simon
 */
public class CcNumsTests {
    
    @Test
    public void testCcNums() {
        String testNumber = "376039019752033";//amex australia card
    
        CcNums testObject = new CcNums();
        assertTrue(testObject != null);
        
        CcNumber number = testObject.getCcNumber(testNumber);
        

        assertTrue(number != null);
        assertTrue(number.getNumber().equals(testNumber));
        assertTrue(testObject.getCcIssuer(testNumber).equals("AmericanExpress"));
        assertTrue(number.getIssuer().equals("AmericanExpress"));

    }

    @Test
    public void testLuhn() {
        assertTrue(ccnums.CcNumIssuerValidator.luhnTest("49927398716"));
        assertFalse(ccnums.CcNumIssuerValidator.luhnTest("49927398717"));
    }

}
