package halfLife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Player

    private Player pesho;
    private Gun pistol;
    private Gun uzy;


    @Before
    public void setup() {
        this.pesho = new Player("Pesho", 100);
        this.pistol = new Gun("Pistol", 100);
        this.uzy = new Gun("Uzy", 250);

    }

    @Test
    public void constructorSetProperlyUserName() {
        Assert.assertEquals("Pesho", this.pesho.getUsername());
    }

    @Test
    public void constructorSetProperlyHealth() {
        Assert.assertEquals(100, this.pesho.getHealth());
    }

    @Test
    public void constructorSetProperlyCollection() {
        Assert.assertEquals(0, this.pesho.getGuns().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setHealthShouldThrow() {
        Player player = new Player("Tosho", -5);
    }

    @Test(expected = NullPointerException.class)
    public void setNameShouldThrowNull() {
        Player player = new Player(null, 100);
    }

    @Test(expected = NullPointerException.class)
    public void setNameShouldThrowEmpty() {
        Player player = new Player("  ", 100);
    }


    @Test(expected = NullPointerException.class)
    public void addShouldThrow() {
        this.pesho.addGun(null);
    }

    @Test
    public void addSuccessfully() {
        this.pesho.addGun(pistol);
        this.pesho.addGun(uzy);
        Assert.assertEquals(2, this.pesho.getGuns().size());
    }


    @Test
    public void removeSuccessfullyReturnFalse() {
        Assert.assertFalse(this.pesho.removeGun(uzy));
    }

    @Test
    public void removeSuccessfullyReturnTrue() {
        this.pesho.addGun(uzy);
        Assert.assertTrue(this.pesho.removeGun(uzy));
    }


    @Test
    public void getGunByNameShouldReturnNull() {
        Assert.assertNull(this.pesho.getGun("noGun"));
    }

    @Test
    public void getGunByNameSuccessfully() {
        this.pesho.addGun(uzy);
        Assert.assertEquals(uzy, this.pesho.getGun(uzy.getName()));
    }

    @Test(expected = IllegalStateException.class)
    public void takeDamageShouldThrowNoHealth() {
        this.pesho.takeDamage(100);
        this.pesho.takeDamage(100);
    }

    @Test
    public void takeDamageSuccessfullyZero() {
        this.pesho.takeDamage(100);
        Assert.assertEquals(0, this.pesho.getHealth());
    }

    @Test
    public void takeDamageSuccessfully() {
        this.pesho.takeDamage(150);
        Assert.assertEquals(0, this.pesho.getHealth());
    }

}
