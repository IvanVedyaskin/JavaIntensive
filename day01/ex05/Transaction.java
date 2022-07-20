package ex05;

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

    public Transaction(UUID id, User sender, User recipient, double amount) {
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
        return "To " + recipient.getName() + "(id = " + recipient.getId() + ") " + amount +  " with id = " + id.toString();
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
