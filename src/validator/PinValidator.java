package validator;

import dao.Client;

/**
 * Created by zabor on 10.11.2016.
 */
public interface PinValidator {
    public boolean validatePin(int pin, Client client);
}
