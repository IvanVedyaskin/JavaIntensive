package ex03;

import java.util.UUID;


public class TransactionLinkedList implements TransactionList{

    private TransactionNode first;
    private TransactionNode last;
    private int size = 0;

    static class TransactionNode {
        private TransactionNode tNext;
        private TransactionNode tPrev;
        private Transaction transactionStart;

        public TransactionNode(Transaction transaction) {
            this.transactionStart = transaction;
            this.tNext = null;
            this.tPrev = null;
        }

        public TransactionNode(){
        }
    }
    public TransactionLinkedList(Transaction t) {
        first = new TransactionNode(t);
        size++;
    }

    public TransactionLinkedList() {
    }

    @Override
    public void addTransaction(Transaction t) {
        try{
            if (size == 0){
                first = new TransactionNode(t);
                last = first;
            }
            else {
                last.tNext = new TransactionNode(t);
                last.tNext.tPrev = last;
                last = last.tNext;
            }
            size++;
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public Transaction removeById(UUID id) {
        TransactionNode tmp = first;
        while ((tmp != null) && (tmp.transactionStart.getId() != id)){
            tmp = tmp.tNext;
        }
        if (tmp.transactionStart != null){
            if (tmp.tNext != null){
                tmp.tNext.tPrev = tmp.tPrev;
            }
            if (tmp.tPrev != null) {
                tmp.tPrev.tNext = tmp.tNext;
            }
            size--;
        }
        else{
            System.out.println("Отсутствует данная транзакция!");
            return null;
        }
        return first.transactionStart;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] arr = new Transaction[size];
        TransactionNode tmp = first;
        for (int i = 0; i < size; i++){
            arr[i] = tmp.transactionStart;
            tmp = tmp.tNext;
        }
        return arr;
    }
}
