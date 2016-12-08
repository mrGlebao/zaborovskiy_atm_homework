package dao;

/**
 * Created by zabor on 13.11.2016.
 */
public class Client {
   private int id = 100;
   private int pin;
   private int money;

    public Client(int pin, int money){
        this.pin = pin;
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPin() {
        return pin;
    }
}
