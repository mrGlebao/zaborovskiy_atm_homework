package server;

import dao.Client;
import exceptions.MoneyExceededException;

/**
 * Created by zabor on 10.11.2016.
 */
public class TerminalServerImpl implements TerminalServer {

    public TerminalServerImpl() {
    }

    @Override
    public int getMoney(Client client) {
        return client.getMoney();
    }

    @Override
    public void putMoney(int amount, Client client) {
        if (client.getMoney() + amount > 0) {
            client.setMoney(client.getMoney() + amount);
        } else {
            throw new MoneyExceededException();
        }
    }

    //stub
    @Override
    public Client getClient(int id) {
        return new Client(1000, 1000);
    }
}
