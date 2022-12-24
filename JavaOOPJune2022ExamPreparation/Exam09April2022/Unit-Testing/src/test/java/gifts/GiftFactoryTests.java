package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

public class GiftFactoryTests {

    private GiftFactory giftFactory;
    private Gift firstGift;
    private Gift secondGift;
    private Gift thirdGift;

    @Before
    public void setup() {
        this.giftFactory = new GiftFactory();
        this.firstGift = new Gift("first", 5.0);
        this.secondGift = new Gift("second", 10.0);
        this.thirdGift = new Gift("third", 20.0);
    }


    @Test
    public void giftFactoryConstructorInitializingShouldHaveListWithZeroElements() {
        Assert.assertEquals(0, this.giftFactory.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createGiftShouldThrowIfTryToAddGiftWithSameName() {
        this.giftFactory.createGift(firstGift);
        this.giftFactory.createGift(firstGift);
    }

    @Test
    public void createGiftShouldAddSuccessfully() {
        this.giftFactory.createGift(firstGift);
        this.giftFactory.createGift(secondGift);
        Assert.assertEquals(2, this.giftFactory.getCount());
    }

    @Test
    public void createGiftShouldAddSuccessfullyRightGifts() {
        this.giftFactory.createGift(firstGift);
        this.giftFactory.createGift(secondGift);
        this.giftFactory.createGift(thirdGift);
        Collection<Gift> gifts = this.giftFactory.getPresents();
        Assert.assertTrue(gifts.contains(secondGift));
    }

    @Test(expected = NullPointerException.class)
    public void removeGiftShouldThrowNullName() {
        this.giftFactory.removeGift(null);
    }

    @Test(expected = NullPointerException.class)
    public void removeGiftShouldThrowEmptyName() {
        this.giftFactory.removeGift("  ");
    }

    @Test
    public void removeGiftShouldRemoveRightGiftByName() {
        this.giftFactory.createGift(firstGift);
        this.giftFactory.createGift(secondGift);
        this.giftFactory.removeGift(secondGift.getType());
        Assert.assertEquals(1, this.giftFactory.getCount());
    }

    @Test
    public void getPresentWithLeastMagicReturnNullIfEmpty() {
        Assert.assertNull(this.giftFactory.getPresentWithLeastMagic());
    }

    @Test
    public void getPresentWithLeastMagicReturnRightPresent() {
        this.giftFactory.createGift(firstGift);
        this.giftFactory.createGift(secondGift);
        this.giftFactory.createGift(thirdGift);
        Assert.assertEquals(firstGift, this.giftFactory.getPresentWithLeastMagic());
    }

    @Test
    public void getPresentByNameShouldReturnNulIfEmpty() {
        Assert.assertNull(this.giftFactory.getPresent("123"));
    }

    @Test
    public void getPresentByNameShouldReturnRightPresent() {
        this.giftFactory.createGift(firstGift);
        this.giftFactory.createGift(secondGift);
        this.giftFactory.createGift(thirdGift);
        Assert.assertEquals(secondGift, this.giftFactory.getPresent(secondGift.getType()));
    }

    @Test
    public void getPresents() {
        this.giftFactory.createGift(firstGift);
        this.giftFactory.createGift(secondGift);
        this.giftFactory.createGift(thirdGift);
        Assert.assertEquals(3, this.giftFactory.getPresents().size());
    }

}
