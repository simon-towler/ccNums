package ccnums;

import static ccnums.CcNumber.maskingChar;

/**
 * Created by simontowler on 21/08/2017.
 */
public class TruncateFactaFour implements TruncationStrategy {
    @Override
    public String truncate(CcNumber ccNumber) {
        String number = ccNumber.getNumber();
        return maskingChar + number.substring(number.length() - 4, number.length());
    }

    @Override
    public String mask(CcNumber ccNumber) {
        String number = ccNumber.getNumber();
        number = "***********" + number.substring(number.length() - 4, number.length());
        return ccNumber.groupNumber(number);
    }
}
