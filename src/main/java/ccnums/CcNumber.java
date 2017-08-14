package ccnums;

import static ccnums.CcNumsValidator.numberIsValid;

public class CcNumber {

    final Boolean valid = false;
    String number = null;
    private static final String issuer = "Undetermined";

    String grouping = "^(\\d{4})(\\d{6})(\\d{5})$";

    //constructor
    CcNumber(String number) {
            setNumber(number);
    }

    //factory method
    static CcNumber createNumber(String number) {

        if (numberIsValid(number)) {
            switch (ccnums.CcNumsValidator.getIssuer(number)) {
                case "AmericanExpress":
                    return new AmexCcNumber(number);
            }
        }
        //TODO don't just return null here
        return null;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return(this.number);
    }

    public String getNumberGrouped() {
        return number.replaceAll(grouping, "$1 $2 $3");
    }

    public String getIssuer() {
        return issuer;
    }

}