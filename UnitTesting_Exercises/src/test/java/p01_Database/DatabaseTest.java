package p01_Database;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {

    private static final Integer[] INITIAL_ELEMENTS = {13, 42, 69, 73};
    private static final int INVALID_MAX_ELEMENTS_COUNT = 17;

    private Database db;

    @Before
    public void createDatabase() throws OperationNotSupportedException {
        this.db = new Database(INITIAL_ELEMENTS);
    }


    @Test
    public void creatingDatabaseShouldSetElementCorrectly() throws OperationNotSupportedException {
        Integer[] dbElements = this.db.getElements();
        Assert.assertEquals("Count of db elements is incorrect", dbElements.length, INITIAL_ELEMENTS.length);
        for (int i = 0; i < INITIAL_ELEMENTS.length; i++) {
            Assert.assertEquals("We have different elements in the database", dbElements[i], INITIAL_ELEMENTS[i]);
        }
    }

//    @Test
//    public void creatingDatabaseShouldSetElementCorrectly() throws OperationNotSupportedException {
//        Assert.assertArrayEquals(this.db.getElements(), INITIAL_ELEMENTS);
//    }

    @Test(expected = OperationNotSupportedException.class) // elements.length < 1 => Throw
    public void creatingDatabaseWithZeroElementShouldThrowException() throws OperationNotSupportedException {
        Database db = new Database();
    }

    @Test(expected = OperationNotSupportedException.class) // elements.length > 16 => Throw
    public void creatingDatabaseWithMoreThanSixteenElementShouldThrowException() throws OperationNotSupportedException {
        Database db = new Database(new Integer[INVALID_MAX_ELEMENTS_COUNT]);
    }


    @Test
    public void testAddSuccessfully() throws OperationNotSupportedException {
        this.db.add(17);
        Integer[] dbElements = this.db.getElements();
        Assert.assertEquals(INITIAL_ELEMENTS.length + 1, dbElements.length);
        Assert.assertEquals(Integer.valueOf(17), dbElements[dbElements.length - 1]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddThrow() throws OperationNotSupportedException { // method name can be: addingNullShouldThrowException()
        this.db.add(null);
    }

    @Test
    public void removingShouldDecreaseElementsCount() throws OperationNotSupportedException {
        this.db.remove();
        Integer[] dbElements = this.db.getElements();
        Assert.assertEquals(INITIAL_ELEMENTS.length - 1, dbElements.length);
        Assert.assertEquals(Integer.valueOf(69), dbElements[dbElements.length - 1]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void removingFromEmptyDatabaseShouldThrowException() throws OperationNotSupportedException {
//        this.db.remove();
//        this.db.remove();
//        this.db.remove();
//        this.db.remove();
        for (int i = 0; i < INITIAL_ELEMENTS.length; i++) {
            this.db.remove();
        }
        this.db.remove();
    }


}