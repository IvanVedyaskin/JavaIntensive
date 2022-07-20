package ex00;

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

    public Transaction(UUID id, User recipient, category type, User sender, double amount) {
        this.id = id;
        this.recipient = recipient;
        this.sender = sender;
        this.amount = 0;
        if (amount > 0 && type == category.CREDIT && sender.getBalance() >= amount ||
                amount < 0 && type == category.DEBIT && -amount <= sender.getBalance()){
            this.amount = amount;
        }
        this.type = "INCOME";
        if (type == category.DEBIT){
            this.type = "OUTCOME";
        }
    }

    public String getInfo(){
        return "(" + id.toString() + ", " + getCategory() + ", " + recipient.getName() + ", " + sender.getName() + ", " + amount + ")";
    }

    private String getCategory(){
        return type;
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
