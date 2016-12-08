package exceptions;

/**
 * Created by zabor on 10.11.2016.
 */
public class MoneyExceededException extends RuntimeException {
    public MoneyExceededException(){
        super("Money limit is exceeded!");
        this.printStackTrace();
    }

}
