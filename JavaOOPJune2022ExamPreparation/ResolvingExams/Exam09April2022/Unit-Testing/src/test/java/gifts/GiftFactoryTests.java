package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GiftFactoryTests {

    private GiftFactory giftFactory;
    private Gift smallGift;
    private Gift middleGift;
    private Gift bigGift;


    @Before
    public void setup() {
        this.giftFactory = new GiftFactory();
        this.smallGift = new Gift("smallGift", 10.00);
        this.middleGift = new Gift("middleGift", 30.00);
        this.bigGift = new Gift("bigGift", 50.00);
    }

    @Test
    public void constructorSetProperlyCollection() {
        Assert.assertEquals(0, this.giftFactory.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createGiftShouldThrow() {
        this.giftFactory.createGift(this.smallGift);
        this.giftFactory.createGift(this.smallGift);
    }

    @Test
    public void addSuccessfully() {
        this.giftFactory.createGift(this.smallGift);
        this.giftFactory.createGift(this.middleGift);
        Assert.assertEquals(2, this.giftFactory.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void removeShouldThrowIfNull() {
        this.giftFactory.removeGift(null);
    }

    @Test(expected = NullPointerException.class)
    public void removeShouldThrowIfEmpty() {
        this.giftFactory.removeGift("  ");
    }

    @Test
    public void removeSuccessfully() {
        this.giftFactory.createGift(this.smallGift);
        this.giftFactory.createGift(this.middleGift);
        Assert.assertTrue(this.giftFactory.removeGift(smallGift.getType()));
    }

    @Test
    public void getPresentByLeastMagicSuccessfullyNull() {
        Assert.assertNull(this.giftFactory.getPresentWithLeastMagic());
    }

    @Test
    public void getPresentByLeastMagicSuccessfullySuccessfully() {
        this.giftFactory.createGift(this.smallGift);
        this.giftFactory.createGift(this.middleGift);
        this.giftFactory.createGift(this.bigGift);
        Assert.assertEquals(this.smallGift, this.giftFactory.getPresentWithLeastMagic());
    }

    @Test
    public void getPresentByNameSuccessfullyNull() {
        Assert.assertNull(this.giftFactory.getPresent("noElements"));
    }

    @Test
    public void getPresentByNameSuccessfully() {
        this.giftFactory.createGift(this.smallGift);
        this.giftFactory.createGift(this.middleGift);
        this.giftFactory.createGift(this.bigGift);
        Assert.assertEquals(this.smallGift, this.giftFactory.getPresent("smallGift"));
    }

    @Test
    public void getCollectionOfGifts() {
        this.giftFactory.createGift(this.smallGift);
        this.giftFactory.createGift(this.middleGift);
        this.giftFactory.createGift(this.bigGift);
        Assert.assertEquals(3, this.giftFactory.getPresents().size());
    }

}
