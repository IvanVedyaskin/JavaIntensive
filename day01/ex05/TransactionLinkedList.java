package ex05;

import java.util.UUID;


public class    TransactionLinkedList implements TransactionList {

    private TransactionNode first;
    private TransactionNode last;
    private int size = 0;

    static class TransactionNode {
        public TransactionNode tNext;
        public TransactionNode tPrev;
        public Transaction transactionStart;

        public TransactionNode(Transaction transaction) {
            this.transactionStart = transaction;
            this.tNext = null;
            this.tPrev = null;
        }

        public TransactionNode(){
        }
    }

    public TransactionNode getFirst() {
        return first;
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
        Transaction tmp2;

        while ((tmp != null) && (!tmp.transactionStart.getId().equals(id))){
            tmp = tmp.tNext;
        }
        if (tmp != null){
            if (tmp.equals(last)){
                if (last.equals(first)){
                    last = null;
                    first = null;
                }
                else {
                    last = last.tPrev;
                    last.tNext = null;
                }
            }
            else if (tmp.equals(first)){
                if (first.tNext != null){
                    first = first.tNext;
                    first.tPrev = null;
                }
            }
            else {
                tmp.tNext.tPrev = tmp.tPrev;
                tmp.tPrev.tNext = tmp.tNext;
            }
            size--;
        }
        else{
            System.out.println("Отсутствует данная транзакция!");
            return null;
        }
        return tmp.transactionStart;
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
