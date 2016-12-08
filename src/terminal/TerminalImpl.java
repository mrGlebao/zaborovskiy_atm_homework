package terminal;

import dao.Client;
import exceptions.AccountLockedException;
import exceptions.MoneyAmountException;
import exceptions.MoneyExceededException;
import exceptions.PinIsNotValidException;
import managers.LockManager;
import server.TerminalServer;
import server.TerminalServerImpl;
import ui.UserInterface;
import ui.UserInterfaceImpl;
import validator.PinValidator;
import validator.PinValidatorImpl;

import java.util.concurrent.TimeUnit;

/**
 * Created by zabor on 09.11.2016.
 */
public class TerminalImpl implements Terminal {

    private final Client client;

    private final TerminalServer server;

    private final PinValidator pinValidator;

    private final LockManager manager;

    private final UserInterface ui = new UserInterfaceImpl();

    public TerminalImpl(Client client) {
        this.client = client;
        this.server = new TerminalServerImpl();
        this.pinValidator = new PinValidatorImpl();
        this.manager = new LockManager(5, TimeUnit.SECONDS);
    }

    @Override
    public void howManyMoney(int pin) {
        if (manager.isLocked()) {
            ui.showMessage("Account is locked! Wait for " + manager.getTimeLeft() + " millis to retry!");
            throw new AccountLockedException(manager.getTimeLeft());
        }
        boolean pinIsValid = pinValidator.validatePin(pin, client);
        if (!pinIsValid) {
            ui.showMessage("Pin is not valid! Try again!");
            manager.incrementBadAttempts();
            throw new PinIsNotValidException(manager.getBadAttempts());
        } else {
            manager.clear();
        }
        ui.showMessage(String.valueOf(server.getMoney(client)));
    }

    @Override
    public void getSomeMoney(int pin, int money) {
        if (manager.isLocked()) {
            ui.showMessage("Account is locked! Wait for " + manager.getTimeLeft() + " millis to retry!");
            throw new AccountLockedException(manager.getTimeLeft());
        }
        boolean pinIsValid = pinValidator.validatePin(pin, client);
        if (!pinIsValid) {
            ui.showMessage("Pin is not valid! Try again!");
            manager.incrementBadAttempts();
            throw new PinIsNotValidException(manager.getBadAttempts());
        } else {
            manager.clear();
        }
        if (money % 100 == 0) {
            try {
                server.putMoney(-money, client);
                ui.showMessage("You got " + money + ", balance is " + client.getMoney() + ".");
            } catch (MoneyExceededException ex) {
                ui.showMessage("You can't get more money than you have! Put smaller amount!");
            }
        } else {
            ui.showMessage("Amount of money to get must be a multiple of 100!");
            throw new MoneyAmountException(money);
        }
    }

    @Override
    public void putSomeMoney(int pin, int money) {
        if (manager.isLocked()) {
            ui.showMessage("Account is locked! Wait for " + manager.getTimeLeft() + " millis to retry!");
            throw new AccountLockedException(manager.getTimeLeft());
        }
        boolean pinIsValid = pinValidator.validatePin(pin, client);
        if (!pinIsValid) {
            ui.showMessage("Pin is not valid! Try again!");
            manager.incrementBadAttempts();
            throw new PinIsNotValidException(manager.getBadAttempts());
        } else {
            manager.clear();
        }
        if (money % 100 == 0) {
            server.putMoney(money, client);
            ui.showMessage("You put " + money + ", balance is " + client.getMoney() + ".");
        } else {
            ui.showMessage("Amount of money to put must be a multiple of 100!");
            throw new MoneyAmountException(money);
        }
    }

}
