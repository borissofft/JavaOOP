import org.junit.Assert;
import org.junit.Test;
import rpg_lab.Axe;
import rpg_lab.Dummy;

public class DummyTest {

//    @Test
//    public void testTakeAttackSuccessfully() {
//        Axe axe = new Axe(10, 10);
//        Dummy target = new Dummy(20, 10);
//        axe.attack(target);
//        Assert.assertEquals(10, target.getHealth());
//    }

    @Test
    public void testTakeAttackSuccessfully() {
        Dummy target = new Dummy(20, 10);
        target.takeAttack(10);
        Assert.assertEquals(10, target.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void testTakeAttackThrow() {
        Axe axe = new Axe(5, 5);
        Dummy target = new Dummy(5, 20);
        axe.attack(target);
        axe.attack(target);
    }

    @Test
    public void testGiveExperienceSuccessfully() { // give experience only dead!!!
        Dummy dummy = new Dummy(0, 20);
        Assert.assertEquals(20, dummy.giveExperience());
    }

    @Test(expected = IllegalStateException.class) // throw if is alive and try to get experience
    public void testGiveExperienceThrow() {
        Dummy dummy = new Dummy(100, 20);
        Assert.assertEquals(20, dummy.giveExperience());
    }

}
