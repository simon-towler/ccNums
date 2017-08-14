package ccnums;

/**
 * Created by simontowler on 10/07/2017.
 */
class AmexCcNumber extends ccnums.CcNumber {

    //instance variables

    private static final String issuer = "AmericanExpress";//TODO make constants for issuer strings
    String grouping = "^(\\d{4})(\\d{6})(\\d{5})$";


    AmexCcNumber(String number) {
        super(number);
    }

    public String getIssuer() {
            return this.issuer;
        }
}
