package ccnums;

/**
 * Created by simontowler on 21/08/2017.
 */
public class TruncateDssSixAndFour extends Truncate {
    private static final int firstChars = 6;
    private static final int lastChars = 4;

    @Override
    public String truncate(CcNumber ccNumber) {
        String number = ccNumber.getNumber();
        return number.substring(0, firstChars)
                + getMasks(1)
                + number.substring(number.length() - lastChars, number.length());
    }

    @Override
    public String mask(CcNumber ccNumber) throws Exception {//TODO complete
        String number = ccNumber.getNumber();
        number = number.substring(0, firstChars)
                + getMasks(number.length() - firstChars - lastChars)
                + number.substring(number.length() - lastChars, number.length());
        return ccNumber.groupNumber(number);
    }

}
