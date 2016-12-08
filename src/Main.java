import dao.Client;
import exceptions.AccountLockedException;
import exceptions.MoneyAmountException;
import exceptions.MoneyExceededException;
import exceptions.PinIsNotValidException;
import terminal.Terminal;
import terminal.TerminalImpl;

/**
 * Created by zabor on 10.11.2016.
 */
public class Main {
    public static final int NUM_OF_ITERATIONS = 4;

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client(100, 100000);

        Terminal terminal0 = new TerminalImpl(client);
        for (int i = 0; i < 4; i++) {
            try {
                Thread.sleep(100);
                terminal0.howManyMoney(10);
            } catch (PinIsNotValidException | AccountLockedException | MoneyExceededException | MoneyAmountException ex) {
            }
        }
            Thread.sleep(6000);
            try {
                Thread.sleep(100);
                terminal0.howManyMoney(10);
            } catch (PinIsNotValidException | AccountLockedException | MoneyExceededException | MoneyAmountException ex) {
            }
            try {
                Thread.sleep(100);
                terminal0.howManyMoney(100);
            } catch (PinIsNotValidException | AccountLockedException | MoneyExceededException | MoneyAmountException ex) {
            }
            try {
                Thread.sleep(100);
                terminal0.howManyMoney(10);
            } catch (PinIsNotValidException | AccountLockedException | MoneyExceededException | MoneyAmountException ex) {
            }

}
}
