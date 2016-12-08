package server;
import dao.Client;
/**
 * Created by zabor on 10.11.2016.
 */
public interface TerminalServer {
    public int getMoney(Client client);
    public void putMoney(int money, Client client);
    public Client getClient(int id);
}
