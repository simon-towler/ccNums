package ccnums;

/**
 * Created by simontowler on 17/08/2017.
 */
class VisaCcNumber extends CcNumber {

    //constructor
    VisaCcNumber(String number) {
        setNumber(number);
        setIssuer("Visa");
        setGrouping(getNumber());
        setValidLengths(new int[]{16,13});//TODO load valid lengths from properties

    }

}
