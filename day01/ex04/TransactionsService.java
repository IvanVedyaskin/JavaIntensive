package ex04;

import java.util.UUID;

public class TransactionsService {
    private UsersList uList;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public TransactionsService(UsersList list) {
        this.uList = list;
    }
    public TransactionsService() {
        uList = new UsersArrayList();
    }

    public void addUser(String name, double balance){
        uList.add(new User(name, balance));
        size++;
    }

    public UsersList getuList() {
        return uList;
    }

    public double getBalance(int id){
        return uList.getUserById(id).getBalance();
    }

    public void startTransaction(int id1, int id2, double sum) throws IllegalArgumentException{
        UUID tmp = UUID.randomUUID();
        if ((uList.getUserById(id1) != null) && (uList.getUserById(id2) != null))
        {
            if (sum > 0 && uList.getUserById(id2).getBalance() >= sum) {
                uList.getUserById(id2).getTransaction().addTransaction(new Transaction(tmp, uList.getUserById(id1), uList.getUserById(id2), -sum));
                uList.getUserById(id1).getTransaction().addTransaction(new Transaction(tmp, uList.getUserById(id2), uList.getUserById(id1), sum));
            }
            else {
                throw new IllegalArgumentException();
            }
        }
        else
            throw new IllegalArgumentException();
    }

    public Transaction[] transactionInArray(int id){
        return uList.getUserById(id).getTransaction().toArray();
    }

    public void removeTransaction(UUID idT, int idU){
        uList.getUserById(idU).removeTransaction(idT);
    }

    public Transaction[] checkNonValidTransaction(){
        TransactionLinkedList tmp = new TransactionLinkedList();
        boolean equal = false;

        for (int i = 0; i < uList.getSize(); i++) {
            if (uList.getUserById(i + 1).getTransaction() != null) {
                Transaction[] arr1 = uList.getUserById(i + 1).getTransaction().toArray();
                for (Transaction transaction : arr1) {
                    Transaction[] arr2 = transaction.getRecipient().getTransaction().toArray();
                    if (arr2.length == 0){
                        equal = false;
                    }
                    for (Transaction value : arr2) {
                        if (transaction.getId().equals(value.getId())) {
                            equal = true;
                            break;
                        }
                    }
                    if (!equal) {
                        tmp.addTransaction(transaction);
                    }
                    equal = false;
                }
            }
        }
        return tmp.toArray();
    }
}
