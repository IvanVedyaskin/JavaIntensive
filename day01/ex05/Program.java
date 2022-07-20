package ex05;

import java.util.LinkedList;

public class Program {
    public static void main(String[] args) {
        TransactionsService clients = new TransactionsService();
        Menu menu = new Menu(clients);

        menu.checkMode(args);
        menu.printMenu();

        System.exit(0);
    }
}
