package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ShopTest {

    private Shop shop;
    private Goods milk;
    private Goods rice;

    @Before
    public void setup() {
        this.shop = new Shop();
        this.milk = new Goods("milk", "111");
        this.rice = new Goods("rice", "222");
    }

    @Test
    public void constructorSetProperlyCollection() {
        Assert.assertEquals(12, this.shop.getShelves().size());
    }

    @Test
    public void constructorSetProperlyCollectionTwo() {
        for (Goods value : this.shop.getShelves().values()) {
            Assert.assertNull(value);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void addShouldThrowOne() throws OperationNotSupportedException {
        this.shop.addGoods("myShelf", this.rice);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addShouldThrowTwo() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves1", this.rice);
        this.shop.addGoods("Shelves1", this.rice);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void addShouldThrowThree() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves1", this.rice);
        this.shop.addGoods("Shelves2", this.rice);
    }

    @Test
    public void addSuccessfully() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves1", this.rice);
        Assert.assertEquals(this.rice, this.shop.getShelves().get("Shelves1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeShouldThrowOne() {
        this.shop.removeGoods("myShelf", this.rice);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeShouldThrowTwo()  {
        this.shop.removeGoods("Shelves1", this.rice);
    }

    @Test
    public void removeSuccessfully() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves1", this.rice);
        this.shop.removeGoods("Shelves1", this.rice);
        Assert.assertNull(this.shop.getShelves().get("Shelves1"));
    }

    @Test
    public void removeSuccessfullyMassage() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves1", this.rice);
        String output = "Goods: 222 is removed successfully!";
        Assert.assertEquals(output, this.shop.removeGoods("Shelves1", this.rice));
    }

}