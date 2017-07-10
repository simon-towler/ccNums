package ccnums;

/**
 * Created by simontowler on 10/07/2017.
 */
    class AmexCcNumber extends ccnums.CcNumber {

        private static final String issuer = "AmericanExpress";//TODO make constants for issuer strings

        AmexCcNumber(String number) {
        super(number);
    }
}
