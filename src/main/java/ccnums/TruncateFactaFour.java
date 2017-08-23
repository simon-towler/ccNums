package ccnums;

import static ccnums.CcNumber.maskingChar;

/**
 * Created by simontowler on 21/08/2017.
 */
public class TruncateFactaFour extends Truncate {
    private static final int firstChars = 0;
    private static final int lastChars = 4;

    // returns a wildcard and the last four digits of the number
    @Override
    public String truncate(CcNumber ccNumber) {
        String number = ccNumber.getNumber();
        return maskingChar + number.substring(number.length() - lastChars, number.length());
    }

    // returns grouped wildcards for masked digits, and the last four digits of the number
    @Override
    public String mask(CcNumber ccNumber) throws Exception {
        String number = ccNumber.getNumber();
        number = getMasks(number.length() - lastChars)
                + number.substring(number.length() - lastChars, number.length());
        return ccNumber.groupNumber(number);
    }
}
