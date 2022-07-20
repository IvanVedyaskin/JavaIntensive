package ex05;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;


public class Menu {
    private int type;
    private TransactionsService transaction;
    private boolean mode = false;

    public Menu(TransactionsService transaction){
        this.transaction = transaction;
    }

    public Menu(){
        new TransactionsService();
    }

    public void printMenu(){
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add a user");
            System.out.println("2. View user balances");
            System.out.println("3. Perform a transfer");
            System.out.println("4. View all transactions for a specific user");
            if (mode) {
                System.out.println("5. DEV – remove a transfer by ID");
                System.out.println("6. DEV – check transfer validity");
                System.out.println("7. Finish execution");
            }
            else{
                System.out.println("5. Finish execution");
            }
            while (type == 0) {
                try {
                    type = scan.nextInt();
                    if ((mode && (type < 1 || type > 7)) ||
                            (!mode && (type < 1 || type > 5))) {
                        type = 0;
                        System.err.println("Command not found! Repeat.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Date is non-valid! Repeat.");
                    scan.nextLine();
                }
            }
            try{
                executeCommand(type, scan);
            }
            catch (NullPointerException | IllegalArgumentException | InputMismatchException | TransactionsService.IllegalBalance e)
            {
                if (e.getClass().equals(IllegalArgumentException.class)){
                    System.out.println("User have not money!");
                }
                else if (e.getClass().equals(TransactionsService.IllegalBalance.class)){
                    System.out.println("Balance can not is negative!");
                }
                else {
                    e.printStackTrace();
                }
            }
            type = 0;
            System.out.println("---------------------------------------------------------");
        }
    }

    private void executeCommand(int type, Scanner scan) throws NullPointerException, IllegalArgumentException, InputMismatchException, TransactionsService.IllegalBalance {
        User user;
        Transaction[] trArr;
        Transaction trValue;
        switch (type){
            case 1:
                System.out.println("Enter a user name and a balance");
                transaction.addUser(scan.next(), scan.nextInt());
                System.out.println("User with id = " + transaction.getuList().getSize() + " is added");
                break;
            case 2:
                System.out.println("Enter a user ID");
                user = transaction.getuList().getUserById(scan.nextInt());
                System.out.println(user.getName() + " - " + user.getBalance());
                break;

            case 3:
                System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
                transaction.startTransaction(scan.nextInt(), scan.nextInt(), scan.nextDouble());
                System.out.println("The transfer is completed");
                break;

            case 4:
                System.out.println("Enter a user ID");
                trArr = transaction.getuList().getUserById(scan.nextInt()).getTransaction().toArray();
                for (int i = 0; i < trArr.length; i++){
                    System.out.println(trArr[i].getInfo());
                }
                break;

            case 5:
                if (mode) {
                    System.out.println("Enter a user ID and a transfer ID");
                    trValue = transaction.removeTransaction(scan.nextInt(), UUID.fromString(scan.next()));
                    System.out.println("Transfer To " + trValue.getRecipient().getName() + "(id = " + trValue.getRecipient().getId() + ") " + (-trValue.getAmount()) + " removed");
                }
                else{
                    scan.close();
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                break;

            case 6:
                System.out.println("Check results:");
                trArr = transaction.checkNonValidTransaction();
                for (Transaction o: trArr){
                    System.out.println(o.getSender().getName() + "(id = " + o.getSender().getId() + ") has an unacknowledged transfer id = " +
                            o.getId() + " from " + o.getRecipient() .getName()+ "(id = " + o.getRecipient().getId() + ") for " + o.getAmount());
                }
                break;
            default:
                scan.close();
                System.out.println("Goodbye!");
                System.exit(0);
        }
        scan.nextLine();
    }

    public void checkMode(String[] args){
        if (args.length == 1){
            if (args[0].equals("--profile=dev")){
                mode = true;
                System.out.println("Dev mod");
            }
            else if (args[0].equals("--profile=production")){
                System.out.println("Prod mod");
            }
            else{
                System.err.println("Error mod");
                System.exit(1);
            }
        }
        else{
            System.err.println("Error mod");
            System.exit(1);
        }

    }
}
