package ccnums;

public class CcNumber {

    final Boolean valid = false;
    String number = null;

    CcNumber(String number) {
        if (CcNumValidator.isValid(number)) {
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

}