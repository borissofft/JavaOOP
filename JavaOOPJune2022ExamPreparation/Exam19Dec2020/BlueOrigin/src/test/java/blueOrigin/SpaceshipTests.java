package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Spaceship

    private Spaceship spaceship;
    private Astronaut pesho;
    private Astronaut gosho;
    private Astronaut tosho;


    @Before
    public void setup() {
        this.spaceship = new Spaceship("myShip", 2);
        this.pesho = new Astronaut("Pesho", 100.00);
        this.gosho = new Astronaut("Gosho", 50.00);
        this.tosho = new Astronaut("Tosho", 30.00);
    }

    @Test
    public void constructorSetProperlyName() {
        Assert.assertEquals("myShip", this.spaceship.getName());
    }

    @Test
    public void constructorSetProperlyCapacity() {
        Assert.assertEquals(2, this.spaceship.getCapacity());
    }

    @Test
    public void constructorSetProperlyCollection() {
        Assert.assertEquals(0, this.spaceship.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCapacityShouldThrow() {
        Spaceship wrong = new Spaceship("Wrong", -1);
    }

    @Test(expected = NullPointerException.class)
    public void setNameShouldThrowNull() {
        Spaceship wrong = new Spaceship(null, 1);
    }

    @Test(expected = NullPointerException.class)
    public void setNameShouldThrowEmpty() {
        Spaceship wrong = new Spaceship("  ", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addShouldThrow() {
        this.spaceship.add(pesho);
        this.spaceship.add(gosho);
        this.spaceship.add(tosho);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addShouldThrowExist() {
        this.spaceship.add(pesho);
        this.spaceship.add(pesho);
    }

    @Test
    public void addSuccessfully() {
        this.spaceship.add(pesho);
        this.spaceship.add(gosho);
        Assert.assertEquals(2, this.spaceship.getCount());
    }


    @Test
    public void removeShouldReturnFalse() {
        Assert.assertFalse(this.spaceship.remove("Az"));
    }

    @Test
    public void removeSuccessfully() {
        this.spaceship.add(pesho);
        Assert.assertTrue(this.spaceship.remove(pesho.getName()));
    }
    

}
