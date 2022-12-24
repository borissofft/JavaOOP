package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ComputerManagerTests {
    // TODO: Test ComputerManager

    private ComputerManager manager;
    private Computer desktop;
    private Computer laptop;
    private Computer notepad;

    @Before
    public void setup() {
        this.manager = new ComputerManager();
        this.desktop = new Computer("Acer", "Desktop", 100.00);
        this.laptop = new Computer("Dell", "Laptop", 50.00);
        this.notepad = new Computer("Dell", "Notepad", 20.00);
    }


    @Test
    public void constructorSetProperlyCollection() {
        Assert.assertEquals(0, this.manager.getCount());
    }

    @Test
    public void constructorSetProperlyCollectionSize() {
        Assert.assertEquals(0, this.manager.getComputers().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addShouldThrowIfNull() {
        this.manager.addComputer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addShouldThrowIfExist() {
        this.manager.addComputer(desktop);
        this.manager.addComputer(desktop);
    }

    @Test
    public void addSuccessfully() {
        this.manager.addComputer(desktop);
        this.manager.addComputer(laptop);
        Assert.assertEquals(2, this.manager.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeShouldThrowNullManufacturer() {
        this.manager.addComputer(desktop);
        this.manager.removeComputer(null,"laptop");
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeShouldThrowNullModel() {
        this.manager.addComputer(desktop);
        this.manager.removeComputer("Dell",null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeShouldThrowNullObject() {
        this.manager.addComputer(desktop);
        this.manager.removeComputer("Wrong","wrong");
    }

    @Test
    public void removeSuccessfully() {
        this.manager.addComputer(desktop);
        this.manager.addComputer(laptop);
        Assert.assertEquals(desktop, this.manager.removeComputer(desktop.getManufacturer(), desktop.getModel()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCollectionByManufacturerShouldThrow() {
        this.manager.addComputer(desktop);
        this.manager.addComputer(laptop);
        this.manager.getComputersByManufacturer(null);
    }

    @Test
    public void getCollectionByManufacturerSuccessfully() {
        this.manager.addComputer(desktop);
        this.manager.addComputer(laptop);
        this.manager.addComputer(notepad);
        List<Computer> dell = this.manager.getComputersByManufacturer("Dell");
        Assert.assertEquals(2, dell.size());
    }

}