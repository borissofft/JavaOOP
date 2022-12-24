import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.Axe;
import rpg_lab.Dummy;

public class DummyTestWithBefore {

    private static final int AXE_ATTACK = 10;
    private static final int AXE_DURABILITY = 10;
    private static final int DUMMY_HEALTH = 20;
    private static final int DUMMY_EXPERIENCE = 10;

    public Axe axe;
    public Dummy dummy;

    @Before
    public void setup() {
        this.axe = new Axe(AXE_ATTACK, AXE_DURABILITY); // Не е добра практика в тестовия клас на един обект, да се вдигат инстанции на други обекти!!!
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_EXPERIENCE);
    }

    @Test
    public void testTakeAttackSuccessfully() {
        this.axe.attack(this.dummy);
        Assert.assertEquals(10, this.dummy.getHealth());
    }


    @Test(expected = IllegalStateException.class)
    public void testTakeAttackThrow() {
        this.axe.attack(this.dummy);
        this.axe.attack(this.dummy);
        this.axe.attack(this.dummy);
    }

    @Test
    public void testGiveExperienceSuccessfully() { // give experience only dead!!!
        this.axe.attack(this.dummy);
        this.axe.attack(this.dummy);
        Assert.assertEquals(10, this.dummy.giveExperience());
    }

    @Test(expected = IllegalStateException.class) // throw if is alive and try to get experience
    public void testGiveExperienceThrow() {
        this.axe.attack(this.dummy);
        this.dummy.giveExperience();
    }

}
