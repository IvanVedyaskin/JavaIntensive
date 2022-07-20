package ex00;

import java.util.Scanner;
import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        User user1 = new User("Alexandr", 500);
        User user2 = new User("Alexey", 500);

        Transaction transaction1 = new Transaction(UUID.randomUUID(), user2, Transaction.category.DEBIT, user1,-500);
        Transaction transaction2 = new Transaction(UUID.randomUUID(), user1, Transaction.category.CREDIT, user2,500);


        System.out.println(transaction1.getInfo());
        System.out.println(transaction2.getInfo());
    }
}
