package ccnums;

public class CcNumber {

    final Boolean valid = false;
    String number = null;
    String issuer = null;

    CcNumber(String number) {
        if (null != CcNumIssuerValidator.getIssuerValidFor(number)) {
            setNumber(number);
            setIssuer(CcNumIssuerValidator.getIssuerValidFor(number));
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

    private void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIssuer() {
        return this.issuer;
    }

}