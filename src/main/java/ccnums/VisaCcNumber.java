package ccnums;

/**
 * Created by simontowler on 17/08/2017.
 */
class VisaCcNumber extends ccnums.CcNumber {

    //instance variables
    String number;
    static final String issuer = "Visa";//TODO make constants for issuer strings
    String grouping = groupingPatterns.getProperty(getIssuer());

    //constructor
    VisaCcNumber(String number) {
        setNumber(number);
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String getIssuer() {
        return issuer;
    }

    @Override
    public String getNumber() {
        return(number);
    }

    @Override
    public String getNumberGrouped() {
        if (number.length() == 16) {
            return number.replaceAll(grouping, "$1 $2 $3 $4");
        } else {
            assert (number.length() == 13);
            return number.replaceAll("^(\\d{4})(\\d{3})(\\d{3})(\\d{3})$", "$1 $2 $3 $4");
        }
    }

}
