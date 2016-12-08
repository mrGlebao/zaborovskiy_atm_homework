package exceptions;

/**
 * Created by zabor on 10.11.2016.
 */
public class AccountLockedException extends RuntimeException {

    public AccountLockedException(long time) {
        super("Terminal is locked! Wait for " + time + " millis to unlock!");
        this.printStackTrace();
    }
}
