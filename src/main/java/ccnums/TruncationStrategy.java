package ccnums;

/**
 * Created by simontowler on 21/08/2017.
 */
public interface TruncationStrategy {
    // truncation omits a part of the number, indicating the point of omission
    String truncate(CcNumber number);
    // masking replaces digits of the grouped number with the same number of masking characters
    String mask(CcNumber number) throws Exception;
}
