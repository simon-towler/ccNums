package ccnums;

import static ccnums.CcNumsValidator.numberIsValid;

public class CcNumber {

    final Boolean valid = false;
    String number = null;
    private static final String issuer = "Undetermined";

    CcNumber(String number) {
        if (null != ccnums.CcNumsValidator.getIssuer(number)) {
            setNumber(number);
        }
    }

    static CcNumber createNumber(String number) {

        if (numberIsValid(number)) {
            switch (ccnums.CcNumsValidator.getIssuer(number)) {
                case "AmericanExpress":
                    return new AmexCcNumber(number);
            }
        }
        return null;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return(this.number);
    }

    public String getIssuer() {
        return issuer;
    }

}