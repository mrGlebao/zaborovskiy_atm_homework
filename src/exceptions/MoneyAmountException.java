package exceptions;

/**
 * Created by zabor on 10.11.2016.
 */
public class MoneyAmountException extends RuntimeException {
    public MoneyAmountException(int money){
        super("Money amount of "+money+" is not a multiple of 100!");
        this.printStackTrace();
    }
}
