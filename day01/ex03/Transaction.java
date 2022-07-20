package ex03;

import java.util.UUID;

public class Transaction {
    private UUID id;
    private User recipient;
    private User sender;
    private double amount;
    public enum category {
        DEBIT, CREDIT;
    }

    public Transaction(UUID id, User recipient, User sender, double amount) {
        this.id = id;
        this.recipient = recipient;
        this.sender = sender;
        this.amount = amount;
    }

    public String getInfo(){
        return "(" + id.toString() + ", " + getCategory() + ", " + recipient.getName() + ", " + sender.getName() + ", " + amount + ")";
    }

    private String getCategory(){
        if (amount < 0){
            return category.DEBIT.toString();
        }
        return category.CREDIT.toString();
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
