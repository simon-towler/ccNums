package ccnums;

/**
 * Created by simontowler on 10/07/2017.
 */
class AmexCcNumber extends ccnums.CcNumber {

    //instance variables
    String number;

    private static final String issuer = "AmericanExpress";//TODO make constants for issuer strings

    String grouping = groupingPatterns.getProperty(getIssuer());

    //constructor
    AmexCcNumber(String number) {
        setNumber(number);
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }


    public String getIssuer() {
            return issuer;
        }

    @Override
    public String getNumber() {
        return(number);
    }

    @Override
    public String getNumberGrouped() {
        return number.replaceAll(grouping, "$1 $2 $3");
    }
    //TODO remove
    @Override
    String getGroupingPattern() {return grouping;}
}
