package ex03;

import java.util.LinkedList;
import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        User user1 = new User("Alexandr", 500);
        User user2 = new User("Alexey", 100);
        Transaction transaction1 = new Transaction(UUID.randomUUID(), user1, user2,500);
        Transaction transaction2 = new Transaction(UUID.randomUUID(), user1, user2,-500);
        Transaction transaction3 = new Transaction(UUID.randomUUID(), user1, user2,500);
        Transaction[] arr;

        TransactionLinkedList transactionLinkedList = new TransactionLinkedList();
        transactionLinkedList.addTransaction(transaction1);
        transactionLinkedList.addTransaction(transaction2);
        transactionLinkedList.addTransaction(transaction3);
        arr = transactionLinkedList.toArray();
        for (Transaction o: arr){
            System.out.println(o.getInfo());
        }
        System.out.println(transactionLinkedList.getSize());
        System.out.println("_________________________________");
        transactionLinkedList.removeById(transaction3.getId());
        arr = transactionLinkedList.toArray();
        for (Transaction o: arr){
            System.out.println(o.getInfo());
        }
        System.out.println(transactionLinkedList.getSize());
    }
}
