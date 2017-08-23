package ccnums;

import static ccnums.CcNumber.maskingChar;

/**
 * Created by simontowler on 23/08/2017.
 */
public abstract class Truncate implements TruncationStrategy {

    String getMasks(int numberOfMasks) {
        String masks = "";
        for (int i = 0; i < numberOfMasks; i++) masks += maskingChar;
        return masks;
    }

}
