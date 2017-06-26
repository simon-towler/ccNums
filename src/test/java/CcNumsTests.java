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
        String testNumber = "376039019752033";//australia card
    
        CcNums testObject = new CcNums();
        
        CcNumber number = testObject.getCcNumber(testNumber);
        
        assertTrue(testObject != null);
        assertTrue(number != null);
        assertTrue(number.getNumber().equals(testNumber));

    }

}
