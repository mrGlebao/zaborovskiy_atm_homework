package exceptions;

/**
 * Created by zabor on 10.11.2016.
 */
public class PinIsNotValidException extends RuntimeException {
    public PinIsNotValidException(int num) {
        super("Pin is not valid! Attempt number " + num);
        this.printStackTrace();
    }
}
