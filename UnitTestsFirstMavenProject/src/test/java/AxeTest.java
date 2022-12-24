import org.junit.Assert;
import org.junit.Test;
import rpg_lab.Axe;
import rpg_lab.Dummy;

public class AxeTest {

    /**
     * Good practise is to use @Before method to initialise objects and fields. It's required to use constants too
     * see DummyTestWithBefore class
     */

    @Test
    public void weaponLosesDurabilityAfterAttack() {
        // Arrange
        Axe axe = new Axe(10, 10);
        Dummy dummy = new Dummy(10, 10);
        // Act
        axe.attack(dummy);
        // Assert
        Assert.assertEquals(9, axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void brokenWeaponCantAttack() {
        Axe axe = new Axe(10, 1);
        Dummy dummy = new Dummy(10, 10);
        axe.attack(dummy);
        axe.attack(dummy);
    }


}
