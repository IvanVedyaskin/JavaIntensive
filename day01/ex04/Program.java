package ex04;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        TransactionsService clients = new TransactionsService();

        System.out.println("-Create Users-");

        clients.addUser("Mike", 1200);
        clients.addUser("John", 50000);
        clients.addUser("Buster", 1000);

        for (int i = 0; i < clients.getSize(); ++i) {
            System.out.println(clients.getuList().getUserByIndex(i).getInfo());
        }

        System.out.println("-Do transactions-");
        try {
            clients.startTransaction(1, 2, 1000);
            System.out.println("OK");
            clients.startTransaction(1, 2, 1000);
            System.out.println("OK");
            clients.startTransaction(3, 1, 1500);
            System.out.println("OK");
            clients.startTransaction(1, 3, 1000);
            System.out.println("OK");
        } catch (IllegalArgumentException e) {
            System.out.printf("IncorrectTransactionException: %s\n", e.getMessage());
        }
        for (int i = 0; i < clients.getSize(); ++i) {
            System.out.println(clients.getuList().getUserByIndex(i).getInfo());
        }
        Transaction[] transact = clients.transactionInArray(1);
        for (int i = 0; i < transact.length; ++i) {
            System.out.println(transact[i].getInfo());
        }
        transact = clients.transactionInArray(2);
        for (int i = 0; i < transact.length; ++i) {
            System.out.println(transact[i].getInfo());
        }

        transact = clients.transactionInArray(3);
        for (int i = 0; i < transact.length; ++i) {
            System.out.println(transact[i].getInfo());
        }

        transact = clients.transactionInArray(1);
        System.out.println("-Show num of illegal transactions-");
        Transaction[] errTr = clients.checkNonValidTransaction();
        System.out.println(errTr.length + " incorrect transactions registered");

        System.out.println("-Remove some transaction-");
        clients.removeTransaction(transact[0].getId(),2);

        System.out.println("-Show num of illegal transactions-");
        errTr = clients.checkNonValidTransaction();
        System.out.println(errTr.length + " incorrect transactions registered");
        for (int i = 0; i < errTr.length; i++){
            System.out.println(errTr[i].getInfo());
        }

        System.out.println("-Remove some transaction-");
        transact = clients.transactionInArray(1);
        clients.removeTransaction(transact[1].getId(),1);
        System.out.println("-Show num of illegal transactions-");
        errTr = clients.checkNonValidTransaction();
        System.out.println(errTr.length + " incorrect transactions registered");

        for (int i = 0; i < errTr.length; i++){
            System.out.println(errTr[i].getInfo());
        }
        System.exit(0);
    }
}
