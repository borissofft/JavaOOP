package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {

    private static final String[] NAMES = {
            "A",
            "B",
            "C",
            "D",
            "E"
    };

    private Database db;
    private Person[] people;

    @Before
    public void createDatabase() throws OperationNotSupportedException {
        people = new Person[5];

        for (int i = 0; i < 5; i++) {
            people[i] = new Person(i + 1, NAMES[i]);
        }

        this.db = new Database(people);
    }

    @Test
    public void creatingDatabaseShouldSetElementCorrectly() throws OperationNotSupportedException {
        Person[] dbElements = this.db.getElements();
        Assert.assertArrayEquals(people, dbElements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void creatingDatabaseWithZeroElementShouldThrowException() throws OperationNotSupportedException {
        Database database = new Database();
    }

    @Test
    public void testAddSuccessfully() throws OperationNotSupportedException {
        this.db.add(new Person(6, "newPerson"));
        Assert.assertEquals(people.length + 1, this.db.getElements().length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddThrow() throws OperationNotSupportedException {
        this.db.add(null);
    }

    @Test
    public void removingShouldDecreaseElementsCount() throws OperationNotSupportedException {
        this.db.remove();
        Assert.assertEquals(people.length - 1, this.db.getElements().length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void removingFromEmptyDatabaseShouldThrowException() throws OperationNotSupportedException {
        for (int i = 0; i < people.length; i++) {
            this.db.remove();
        }
        this.db.remove();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findByUsernameShouldThrowExceptionWhenCalledWithNull() throws OperationNotSupportedException {
        this.db.findByUsername(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findByUsernameShouldThrowExceptionWithDuplicatedUsernames() throws OperationNotSupportedException {
        this.db.add(new Person(12, "B"));
        this.db.findByUsername("B");
    }

    @Test
    public void findByUsernameShouldReturnCorrectPerson() throws OperationNotSupportedException {
        Person found = this.db.findByUsername("B");
//        Assert.assertEquals(found, db.findById(2)); // Добра практика е да се тества само един метод, тук извикваме втори
        boolean areEqual = found.getId() == 2 && found.getUsername().equals("B");
        Assert.assertTrue(areEqual);
    }

    @Test
    public void findByUsernameShouldBeCaseSensitive() throws OperationNotSupportedException {
        // Трябва да намери добавеният Person с име малка буква "b"
        this.db.add(new Person(69, "b"));
        Person found = this.db.findByUsername("b");
        boolean areEqual = found.getId() == 69 && found.getUsername().equals("b");
        Assert.assertTrue(areEqual);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findIdShouldThrowIfNoUserFound() throws OperationNotSupportedException {
        Person found = this.db.findById(0);
    }

    @Test
    public void findIdShouldReturnCorrectPerson() throws OperationNotSupportedException {
        Person found = this.db.findById(1);
//        Assert.assertEquals(found, db.findById(1)); // Добра практика е да се тества само един метод, тук извикваме втори
        boolean areEqual = found.getId() == 1 && found.getUsername().equals("A");
        Assert.assertTrue(areEqual);
    }

}