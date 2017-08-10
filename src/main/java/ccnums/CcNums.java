package ccnums;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static ccnums.CcNumsValidator.numberIsValid;

public class CcNums {

    //public instance methods

    public static void main(String [] args) throws FileNotFoundException, IOException {
        //TODO
    }

    public static String getCcIssuer(String number) {
        return ccnums.CcNumsValidator.getIssuer(number);
    }

    public static CcNumber getCcNumber(String number) {
        if (numberIsValid(number)) return CcNumber.createNumber(number);
        //TODO what if the number is not valid? how to tell the user
        return null;
    }
}