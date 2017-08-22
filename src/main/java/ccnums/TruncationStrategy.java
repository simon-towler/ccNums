package ccnums;

/**
 * Created by simontowler on 21/08/2017.
 */
public interface TruncationStrategy {
    String truncate(CcNumber number);
    String mask(CcNumber number);
}
