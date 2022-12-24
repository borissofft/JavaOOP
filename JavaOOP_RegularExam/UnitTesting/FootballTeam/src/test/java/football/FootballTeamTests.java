package football;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FootballTeamTests {

    private FootballTeam footballTeam;
    private Footballer small;
    private Footballer middle;
    private Footballer big;

    @Before
    public void setup() {
        this.footballTeam = new FootballTeam("myTeam", 2);
        this.small = new Footballer("small");
        this.middle = new Footballer("middle");
        this.big = new Footballer("big");
    }


    @Test
    public void constructorSetProperlyName() {
        Assert.assertEquals("myTeam", this.footballTeam.getName());
    }

    @Test
    public void constructorSetProperlyVacantPositions() {
        Assert.assertEquals(2, this.footballTeam.getVacantPositions());
    }

    @Test
    public void constructorSetProperlyCollection() {
        Assert.assertEquals(0, this.footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setVacantPositionsShouldThrow() {
        FootballTeam wrong = new FootballTeam("wrong", -10);
    }

    @Test(expected = NullPointerException.class)
    public void setNameShouldThrowNull() {
        FootballTeam wrong = new FootballTeam(null, 5);
    }

    @Test(expected = NullPointerException.class)
    public void setNameShouldThrowEmpty() {
        FootballTeam wrong = new FootballTeam("  ", 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addFootballerShouldThrowIfFull() {
        this.footballTeam.addFootballer(small);
        this.footballTeam.addFootballer(middle);
        this.footballTeam.addFootballer(big);
    }

    @Test
    public void addFootballerSuccessfully() {
        this.footballTeam.addFootballer(small);
        this.footballTeam.addFootballer(middle);
        Assert.assertEquals(2, this.footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeFootballerShouldThrow() {
        this.footballTeam.removeFootballer("emptyCollection");
    }

    @Test
    public void removeFootballerSuccessfully() {
        this.footballTeam.addFootballer(small);
        this.footballTeam.addFootballer(middle);
        this.footballTeam.removeFootballer(small.getName());
        Assert.assertEquals(1, this.footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void footballerForSaleShouldThrow() {
        this.footballTeam.footballerForSale("emptyCollection");
    }

    @Test
    public void footballerForSaleSuccessfully() {
        this.footballTeam.addFootballer(small);
        this.footballTeam.addFootballer(middle);
        Assert.assertEquals(small, this.footballTeam.footballerForSale(small.getName()));
        Assert.assertFalse(small.isActive());
    }

    @Test
    public void getStatisticsSuccessfully() {
        this.footballTeam.addFootballer(small);
        this.footballTeam.addFootballer(middle);
        String output = "The footballer small, middle is in the team myTeam.";
        Assert.assertEquals(output, this.footballTeam.getStatistics());
    }

}
