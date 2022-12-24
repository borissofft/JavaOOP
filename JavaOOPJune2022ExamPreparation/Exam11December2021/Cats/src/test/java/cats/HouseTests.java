package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {

    private House house;
    private Cat little;
    private Cat middle;
    private Cat big;

    @Before
    public void setup() {
        this.house = new House("MyHouse", 2);
        little = new Cat("little");
        middle = new Cat("middle");
        big = new Cat("big");
    }

    @Test
    public void constructorShouldProperlySetCapacity() {
        Assert.assertEquals(2, this.house.getCapacity());
    }

    @Test
    public void constructorShouldProperlySetName() {
        Assert.assertEquals("MyHouse", this.house.getName());
    }

    @Test
    public void constructorShouldProperlyInitializeList() {
        Assert.assertEquals(0, this.house.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void setNameShouldThrowIfNull() {
        House house = new House(null, 5);
    }

    @Test(expected = NullPointerException.class)
    public void setNameShouldThrowIfEmpty() {
        House house = new House("  ", 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCapacityShouldThrowIfNegative() {
        House house = new House("Negative", -7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addCatShouldThrowIfFull() {
        this.house.addCat(little);
        this.house.addCat(middle);
        this.house.addCat(big);
    }

    @Test
    public void addCatShouldWorkProperly() {
        this.house.addCat(little);
        this.house.addCat(middle);
        Assert.assertEquals(2, this.house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeCatShouldThrowIfEmpty() {
        this.house.removeCat("noCat");
    }

    @Test
    public void removeCatShouldWorkProperly() {
        this.house.addCat(little);
        this.house.addCat(middle);
        this.house.removeCat("middle");
        Assert.assertEquals(1, this.house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void catForSaleShouldThrowIfNoCat() {
        this.house.catForSale("noCat");
    }

    @Test
    public void catForSaleShouldWorkProperly() {
        this.house.addCat(little);
        this.house.addCat(middle);
        Cat cat = this.house.catForSale("little");
        Assert.assertEquals(this.little, cat);
    }

    @Test
    public void catForSaleShouldSetHungryProperly() {
        this.house.addCat(little);
        this.house.addCat(middle);
        Cat cat = this.house.catForSale("little");
        Assert.assertFalse(cat.isHungry());
    }

    @Test
    public void statisticsShouldReturnRightString() {
        this.house.addCat(little);
        this.house.addCat(middle);
        String result = this.house.statistics();
        String myString = "The cat little, middle is in the house MyHouse!";
        Assert.assertEquals(myString, result);
    }

}
