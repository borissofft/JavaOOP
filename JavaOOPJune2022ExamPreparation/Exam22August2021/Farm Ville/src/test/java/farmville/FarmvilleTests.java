package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Farm

    private Farm farm;
    private Animal dog;
    private Animal cat;
    private Animal fish;

    @Before
    public void setup() {
        this.farm = new Farm("myFarm", 2);
        this.dog = new Animal("dog", 10);
        this.cat = new Animal("cat", 5);
        this.fish = new Animal("fish", 15);
    }

    @Test
    public void constructorSetProperlyName() {
        Assert.assertEquals("myFarm", this.farm.getName());
    }

    @Test
    public void constructorSetProperlyCapacity() {
        Assert.assertEquals(2, this.farm.getCapacity());
    }

    @Test
    public void constructorSetProperlyCollectionOfAnimals() {
        Assert.assertEquals(0, this.farm.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCapacityShouldThrow() {
        Farm otherFarm = new Farm("otherFarm", -5);
    }

    @Test(expected = NullPointerException.class)
    public void setNameShouldThrowNull() {
        Farm otherFarm = new Farm(null, 5);
    }

    @Test(expected = NullPointerException.class)
    public void setNameShouldThrowEmpty() {
        Farm otherFarm = new Farm("  ", 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addShouldThrow() {
        this.farm.add(this.dog);
        this.farm.add(this.cat);
        this.farm.add(this.fish);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addShouldThrowExist() {
        this.farm.add(this.dog);
        this.farm.add(this.dog);
    }

    @Test
    public void addSuccessfully() {
        this.farm.add(this.dog);
        this.farm.add(this.cat);
        Assert.assertEquals(2, this.farm.getCount());
    }

//    @Test(expected = )
//    public void removeShouldThrow() {
//
//    }

    @Test
    public void removeSuccessfully() {
        this.farm.add(this.dog);
        this.farm.add(this.cat);
        this.farm.remove("dog");
        Assert.assertEquals(1, this.farm.getCount());
    }


}
