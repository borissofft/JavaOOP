package aquarium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AquariumTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Aquarium

    private Aquarium aquarium;
    private Fish gold;
    private Fish ancistrus;
    private Fish neon;


    @Before
    public void setup() {
        this.aquarium = new Aquarium("freshWater", 2);
        this.gold = new Fish("gold");
        this.ancistrus = new Fish("ancistrus");
        this.neon = new Fish("neon");
    }


    @Test
    public void constructorSetProperlyCapacity() {
        Assert.assertEquals(2, this.aquarium.getCapacity());
    }

    @Test
    public void constructorSetProperlyName() {
        Assert.assertEquals("freshWater", this.aquarium.getName());
    }

    @Test
    public void constructorSetProperlyCollection() {
        Assert.assertEquals(0, this.aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCapacityShouldThrowNegative() {
        Aquarium wrong = new Aquarium("myAquarium", -1);
    }

    @Test(expected = NullPointerException.class)
    public void setNameShouldThrowNull() {
        Aquarium wrong = new Aquarium(null, 1);
    }

    @Test(expected = NullPointerException.class)
    public void setNameShouldThrowEmpty() {
        Aquarium wrong = new Aquarium("  ", 1);
    }


    @Test(expected = IllegalArgumentException.class)
    public void addShouldThrowIfFull() {
        this.aquarium.add(gold);
        this.aquarium.add(ancistrus);
        this.aquarium.add(neon);
    }

    @Test
    public void addSuccessfully() {
        this.aquarium.add(gold);
        this.aquarium.add(ancistrus);
        Assert.assertEquals(2, this.aquarium.getCount());
    }


    @Test(expected = IllegalArgumentException.class)
    public void removeShouldThrow() {
        this.aquarium.remove(neon.getName());
    }

    @Test
    public void removeSuccessfully() {
        this.aquarium.add(gold);
        this.aquarium.add(ancistrus);
        this.aquarium.remove(gold.getName());
        Assert.assertEquals(1, this.aquarium.getCount());
    }


    @Test(expected = IllegalArgumentException.class)
    public void getFishByNameShouldThrow() {
        this.aquarium.sellFish(neon.getName());
    }

    @Test
    public void getFishByNameSuccessfully() {
        this.aquarium.add(gold);
        this.aquarium.add(ancistrus);
        Assert.assertEquals(gold, this.aquarium.sellFish(gold.getName()));
    }

    @Test
    public void getFishByNameSuccessfullySetAvailableFalse() {
        this.aquarium.add(gold);
        this.aquarium.add(ancistrus);
        this.aquarium.sellFish(gold.getName());
        Assert.assertFalse(gold.isAvailable());
    }

    @Test
    public void report() {
        this.aquarium.add(gold);
        this.aquarium.add(ancistrus);
        String output = "Fish available at freshWater: gold, ancistrus";
        Assert.assertEquals(output, this.aquarium.report());
    }

}

