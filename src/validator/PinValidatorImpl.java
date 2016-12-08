package validator;

import dao.Client;

/**
 * Created by zabor on 10.11.2016.
 */
public class PinValidatorImpl implements PinValidator {

    @Override
    public boolean validatePin(int pin, Client client) {
        if (pin == client.getPin()) {
            return true;
        }
        return false;
    }
}
