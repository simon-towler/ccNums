package ccnums;

/**
 * Created by simontowler on 21/08/2017.
 */
public class TruncateDssSixAndFour implements TruncationStrategy {
    @Override
    public String truncate(CcNumber ccNumber) {
        String number = ccNumber.getNumber();
        return number.substring(0, 6) + "*" + number.substring(number.length() - 4, number.length());
    }

    @Override
    public String mask(CcNumber ccNumber) {//TODO complete
        String number = ccNumber.getNumber();
        return number.replaceAll(".", "*");
    }

}
