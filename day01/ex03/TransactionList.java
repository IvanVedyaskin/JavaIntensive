package ex03;

import java.util.UUID;

public interface TransactionList {
    void addTransaction(Transaction t);
    Transaction removeById(UUID id);
    Transaction[] toArray();
}
