package ex04;

import java.util.UUID;

public class Transaction {
    private UUID id;
    private User recipient;
    private User sender;
    private double amount;
    private String type;
    public enum category {
        DEBIT, CREDIT;
    }

    public Transaction(UUID id, User recipient, User sender, double amount) {
        this.id = id;
        this.recipient = recipient;
        this.sender = sender;
        this.amount = amount;
        if (this.amount < 0){
            type = "OUTCOME";
        }
        else{
            type = "INCOME";
        }
        setUser(this.sender, amount);
    }

    public String getInfo(){
        return "(" + id.toString() + ", " + type + ", " + sender.getName() + ", " + recipient.getName() + ", " + amount + ")";
    }

    private void setUser(User user, double amount){
        user.setBalance(user.getBalance() + amount);
    }

    public double getAmount() {
        return amount;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public UUID getId(){
        return id;
    }
}
