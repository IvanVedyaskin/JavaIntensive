package ex01;

public class User {
    private int id;
    private String name;
    private double balance;

    public User(String name, double balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = 0;
        if (balance >= 0){
            this.balance = balance;
        }
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
