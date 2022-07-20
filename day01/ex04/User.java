package ex04;

import ex01.UserIdsGenerator;

import java.util.UUID;

public class User {
    private int id;
    private String name;
    private double balance;
    private TransactionLinkedList transaction;

    public User(String name, double balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = balance;
        transaction = new TransactionLinkedList();
    }

    public Transaction removeTransaction(UUID idU){
        return transaction.removeById(idU);
    }

    public TransactionLinkedList getTransaction() {
        return transaction;
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

    public String getInfo(){
        return ("(" + id + ", " + name + ", " + balance + "" + ")");
    }
}
