package ccnums;

public class CcNumber {

    final Boolean valid = false;
    String number = null;
    private static final String issuer = "Undetermined";

    CcNumber(String number) {
        if (null != ccnums.CcNumIssuerValidator.getIssuerValidFor(number)) {
            setNumber(number);
        }
    }

    String normalize(String number) {
        return(number);//TODO add normalization logic here
    }

    void setNumber(String number) {
        this.number = normalize(number);
    }

    public String getNumber() {
        return(this.number);
    }

    public String getIssuer() {
        return issuer;
    }

}