import java.util.*;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock {

    private Map<Integer, Transaction> transactions;

    public ChainblockImpl() {
        this.transactions = new LinkedHashMap<>();
    }

    public int getCount() {
        return this.transactions.size();
    }

    public void add(Transaction transaction) {
        if (!this.transactions.containsKey(transaction.getId())) {
            this.transactions.put(transaction.getId(), transaction);
        }
    }

    public boolean contains(Transaction transaction) {
        return this.contains(transaction.getId()); // имплементацията депендва на долният метод contains(int id)
    }

    public boolean contains(int id) {
        return this.transactions.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if (!this.contains(id)) {
            throw new IllegalArgumentException();
        }
        this.transactions.get(id).setStatus(newStatus);
    }

    public void removeTransactionById(int id) {
        if (!this.contains(id)) {
            throw new IllegalArgumentException();
        }
        this.transactions.remove(id);
    }

    public Transaction getById(int id) {
        if (!this.transactions.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return this.transactions.get(id);
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> transactions = this.transactions.values()
                .stream()
                .filter(transaction -> transaction.getStatus().equals(status))
                .sorted((f, s) -> Double.compare(s.getAmount(), f.getAmount()))
                .collect(Collectors.toList());

        if (transactions.isEmpty()) {
            throw new IllegalArgumentException(); // Този exception се хвърля и в други методи, който използват този метод getByTransactionStatus(TransactionStatus status)
        }

        return transactions;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) { // Не е нужно и тук да хвърляма exception, защото извикваме метода
        Iterable<Transaction> transactions = this.getByTransactionStatus(status);          // getByTransactionStatus(status), в тялото на който се хвърля нужният exception
        List<String> senders = new ArrayList<>();                                         // и ще се предаде по call stack-а и ще се прихване където го търсим:
        transactions.forEach(transaction -> senders.add(transaction.getFrom()));         //  getAllSendersWithTransactionStatusShouldThrowExceptionWhenNoTransactionsWithGivenStatusArePresent()
        return senders;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        Iterable<Transaction> transactions = this.getByTransactionStatus(status);
        List<String> receivers = new ArrayList<>();
        transactions.forEach(transaction -> receivers.add(transaction.getFrom()));
        return receivers;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return this.transactions.values()
                .stream()
                .sorted((f, s) -> {
                    int comp = Double.compare(s.getAmount(), f.getAmount());
                    if (comp == 0) {
                        comp = Integer.compare(f.getId(), s.getId());
                    }
                    return comp;
                }).collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        return null;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        return null;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return null;
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        return null;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        return null;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return null;
    }

    public Iterator<Transaction> iterator() {
        return null;
    }

}
