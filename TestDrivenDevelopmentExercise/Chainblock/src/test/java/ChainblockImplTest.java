import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChainblockImplTest {

    private Chainblock chainblock;

    private static final int ID = 1;
    private static final TransactionStatus SUCCESSFUL_STATUS = TransactionStatus.SUCCESSFUL;
    private static final String SENDER = "Pesho";
    private static final String RECEIVER = "Gosho";
    private static final double INITIAL_AMOUNT = 100.0;

    private static final int[] IDS = {1, 2, 3, 4, 5, 6};
    private static final String[] NAMES = {"A", "B", "C", "D", "E", "F"};
    private static final double[] AMOUNTS = {10, 20, 30, 40, 50, 60};


    private static final Transaction TRANSACTION_ID_ONE =
            new TransactionImpl(ID, SUCCESSFUL_STATUS, SENDER, RECEIVER, INITIAL_AMOUNT);

    @Before
    public void createChainblock() {
        this.chainblock = new ChainblockImpl();
    }

    public void addSingleTransaction() {
        this.chainblock.add(TRANSACTION_ID_ONE);
    }

    public void addTransactions(int count) {
        for (int i = 1; i <= count; i++) {
            this.chainblock.add(new TransactionImpl(i, SUCCESSFUL_STATUS, SENDER, RECEIVER, INITIAL_AMOUNT));
        }
    }

    public void addTransactionsWithDifferentAmount(int count) {
        for (int i = 1; i <= count; i++) {
            this.chainblock.add(new TransactionImpl(i, SUCCESSFUL_STATUS, SENDER, RECEIVER, INITIAL_AMOUNT * i));
        }
    }


    @Test
    public void containsByIdReturnsTrueIfTransactionIsPresent() {
        addSingleTransaction();
        Assert.assertTrue(this.chainblock.contains(TRANSACTION_ID_ONE.getId()));
    }

    @Test
    public void containsByIdReturnsFalseIfTransactionIsNotPresent() {
        Assert.assertFalse(this.chainblock.contains(TRANSACTION_ID_ONE.getId()));
    }

    @Test
    public void addShouldIncreaseCount() {
        final int EXPECTED_COUNT = 1;
        addSingleTransaction();
        Assert.assertEquals(EXPECTED_COUNT, this.chainblock.getCount());
    }

    @Test
    public void containsByTransactionShouldReturnTrueIfTransactionIsPresent() {
        addSingleTransaction();
        Assert.assertTrue(this.chainblock.contains(TRANSACTION_ID_ONE.getId()));
    }

    @Test
    public void getCountReturnZeroOnEmptyDatabase() {
        Assert.assertEquals(0, this.chainblock.getCount());
    }

    @Test
    public void getCountShouldReturnOneWhenAddingTheSameTransactionMultipleTimes() {
        for (int i = 0; i < 10; i++) {
            this.chainblock.add(TRANSACTION_ID_ONE);
        }
        Assert.assertEquals(1, this.chainblock.getCount());
    }

    @Test
    public void getCountShouldWorkProperlyWithTenElements() {
        final int ELEMENTS_COUNT = 10;
        this.addTransactions(ELEMENTS_COUNT);
        Assert.assertEquals("Adding multiple transactions do not increase count", ELEMENTS_COUNT, this.chainblock.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void changingStatusShouldThrowExceptionWithInvalidTransactionId() {
        addSingleTransaction();
        TransactionStatus newStatus = TransactionStatus.values()[(SUCCESSFUL_STATUS.ordinal() + 1) % TransactionStatus.values().length];
        this.chainblock.changeTransactionStatus(ID + 1, newStatus);
    }

    @Test
    public void changingStatusShouldChangeStatusCorrectly() {
        addSingleTransaction();
        TransactionStatus newStatus = TransactionStatus.values()[(SUCCESSFUL_STATUS.ordinal() + 1) % TransactionStatus.values().length];
        this.chainblock.changeTransactionStatus(ID, newStatus);
        Assert.assertEquals(newStatus, TRANSACTION_ID_ONE.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeTransactionByIdShouldThrowExceptionWithInvalidTransactionId() {
        addSingleTransaction();
        this.chainblock.removeTransactionById(ID + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeTransactionByIdShouldThrowExceptionOnEmptyDatabase() {
        new ChainblockImpl().removeTransactionById(ID);
    }

    @Test
    public void removeTransactionByIdShouldRemoveSuccessfully() {
        addSingleTransaction();
        this.chainblock.removeTransactionById(ID);
        Assert.assertEquals(0, this.chainblock.getCount());
    }

    @Test
    public void removeTransactionByIdShouldRemoveTheCorrectTransaction() {
        final int ELEMENTS_COUNT = 10;
        this.addTransactions(ELEMENTS_COUNT);
        this.chainblock.removeTransactionById(ELEMENTS_COUNT - 1);
        Assert.assertFalse(this.chainblock.contains(ELEMENTS_COUNT - 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTransactionByIdShouldThrowExceptionWithInvalidTransactionId() {
        addSingleTransaction();
        this.chainblock.getById(ID + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTransactionByIdShouldThrowExceptionOnEmptyDatabase() {
        new ChainblockImpl().getById(ID);
    }

    @Test
    public void getTransactionByIdShouldGetItSuccessfullyWithSingleElement() {
        addSingleTransaction();
        Transaction found = this.chainblock.getById(ID);
        Assert.assertEquals("Get by ID returned wrong transaction", TRANSACTION_ID_ONE.getId(), found.getId());
    }

    @Test
    public void getTransactionByIdShouldReturnTheCorrectTransaction() {
        final int ELEMENTS_COUNT = 10;
        this.addTransactions(ELEMENTS_COUNT);
        Transaction found = this.chainblock.getById(ELEMENTS_COUNT - 1);
        Assert.assertEquals("Get by ID returned wrong transaction", ELEMENTS_COUNT - 1, found.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByTransactionStatusShouldThrowExceptionIfThereAreNoTransactionsWithGivenStatus() {
        final int ELEMENTS_COUNT = 10;
        this.addTransactions(ELEMENTS_COUNT);
        this.chainblock.getByTransactionStatus(TransactionStatus.values()[(SUCCESSFUL_STATUS.ordinal() + 1) % TransactionStatus.values().length]);
    }

    @Test
    public void getByTransactionStatusShouldReturnAllTransactionsWithGivenStatus() {
        final int ELEMENTS_COUNT = 9;
        this.addTransactions(ELEMENTS_COUNT);
        this.chainblock.add(new TransactionImpl(ID * 127, TransactionStatus.values()[(SUCCESSFUL_STATUS.ordinal() + 1) % TransactionStatus.values().length], SENDER, RECEIVER, INITIAL_AMOUNT));
        Iterable<Transaction> transactions = this.chainblock.getByTransactionStatus(SUCCESSFUL_STATUS);
        int count = 0;
        for (Transaction transaction : transactions) {
            count++;
        }
        Assert.assertEquals(ELEMENTS_COUNT, count);
    }

    @Test
    public void getByTransactionStatusShouldReturnAllTransactionsWithGivenStatusOrderedDescendingByAmount() {
        final int ELEMENTS_COUNT = 9;
        this.addTransactionsWithDifferentAmount(ELEMENTS_COUNT);
        Iterable<Transaction> transactions = this.chainblock.getByTransactionStatus(SUCCESSFUL_STATUS);
        boolean areOrdered = true;
        double amount = Double.POSITIVE_INFINITY;
        for (Transaction transaction : transactions) {
            if (amount < transaction.getAmount()) {
                areOrdered = false;
                break;
            } else {
                amount = transaction.getAmount();
            }
        }
        Assert.assertTrue(areOrdered);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAllSendersWithTransactionStatusShouldThrowExceptionWhenNoTransactionsWithGivenStatusArePresent() {
        final int ELEMENTS_COUNT = 20;
        this.addTransactionsWithDifferentAmount(ELEMENTS_COUNT);
        this.chainblock.getAllSendersWithTransactionStatus(TransactionStatus.values()[(SUCCESSFUL_STATUS.ordinal() + 1) % TransactionStatus.values().length]);
    }

    @Test
    public void getAllSendersWithTransactionStatusShouldReturnCorrectNames() {
        for (int i = 0; i < IDS.length; i++) {
            this.chainblock.add(new TransactionImpl(IDS[i], SUCCESSFUL_STATUS, NAMES[i], NAMES[i], AMOUNTS[i]));
        }
        Iterable<String> senders = this.chainblock.getAllSendersWithTransactionStatus(SUCCESSFUL_STATUS);

        int index = NAMES.length - 1;
        boolean areEqual = true;
        for (String sender : senders) {
            if (!sender.equals(NAMES[index--])) {
                areEqual = false;
                break;
            }
        }
        Assert.assertTrue(areEqual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAllReceiversWithTransactionStatusShouldThrowExceptionWhenNoTransactionsWithGivenStatusArePresent() {
        final int ELEMENTS_COUNT = 20;
        this.addTransactionsWithDifferentAmount(ELEMENTS_COUNT);
        this.chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.values()[(SUCCESSFUL_STATUS.ordinal() + 1) % TransactionStatus.values().length]);
    }

    @Test
    public void getAllReceiversWithTransactionStatusShouldReturnCorrectNames() {
        for (int i = 0; i < IDS.length; i++) {
            this.chainblock.add(new TransactionImpl(IDS[i], SUCCESSFUL_STATUS, NAMES[i], NAMES[i], AMOUNTS[i]));
        }
        Iterable<String> receivers = this.chainblock.getAllReceiversWithTransactionStatus(SUCCESSFUL_STATUS);

        int index = NAMES.length - 1;
        boolean areEqual = true;
        for (String sender : receivers) {
            if (!sender.equals(NAMES[index--])) {
                areEqual = false;
                break;
            }
        }
        Assert.assertTrue(areEqual);
    }

    @Test
    public void getAllOrderedByAmountDescendingThenByIdShouldReturnSortedElements() {
        Transaction t1 = new TransactionImpl(1, SUCCESSFUL_STATUS, "A", "B", 100);
        Transaction t2 = new TransactionImpl(2, SUCCESSFUL_STATUS, "A", "B", 150);
        Transaction t3 = new TransactionImpl(3, SUCCESSFUL_STATUS, "A", "B", 100);
        Transaction t4 = new TransactionImpl(4, SUCCESSFUL_STATUS, "A", "B", 200);

        this.chainblock.add(t1);
        this.chainblock.add(t2);
        this.chainblock.add(t3);
        this.chainblock.add(t4);
        // 4, 2, 1, 3
        int[] expectedIds = {4, 2, 1, 3};

        Iterable<Transaction> ordered = this.chainblock.getAllOrderedByAmountDescendingThenById();
        int index = 0;
        for (Transaction transaction : ordered) {
            Assert.assertEquals(expectedIds[index++], transaction.getId());
        }

        Assert.assertEquals(expectedIds.length, index);
    }


}