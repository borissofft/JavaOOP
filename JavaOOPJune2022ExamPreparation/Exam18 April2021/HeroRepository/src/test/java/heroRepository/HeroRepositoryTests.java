package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroRepositoryTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS HeroRepository

    private HeroRepository heroRepository;
    private Hero pesho;
    private Hero gosho;


    @Before
    public void setup() {
        this.heroRepository = new HeroRepository();
        this.pesho = new Hero("Pesho", 5);
        this.gosho = new Hero("Gosho", 3);
    }


    @Test
    public void constructorSetProperlyOne() {
        Assert.assertEquals(0, this.heroRepository.getCount());
    }

    @Test
    public void constructorSetProperlyTwo() {
        Assert.assertEquals(0, this.heroRepository.getHeroes().size());
    }


    @Test(expected = NullPointerException.class)
    public void createShouldThrowIfNull() {
        this.heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createShouldThrowIfExist() {
        this.heroRepository.create(pesho);
        this.heroRepository.create(pesho);
    }

    @Test
    public void createSuccessfullyReturnRightString() {
        Assert.assertEquals("Successfully added hero Pesho with level 5"
                , this.heroRepository.create(pesho));
    }

    @Test
    public void createSuccessfullyReturnRightCount() {
        this.heroRepository.create(pesho);
        this.heroRepository.create(gosho);
        Assert.assertEquals(2, this.heroRepository.getCount());
    }


    @Test(expected = NullPointerException.class)
    public void removeShouldThrow() {
        this.heroRepository.remove(null);
    }

    @Test
    public void removeSuccessfully() {
        this.heroRepository.create(pesho);
        Assert.assertTrue(this.heroRepository.remove(pesho.getName()));
    }


    @Test
    public void getSomeObjectByLevelSuccessfullyNull() {
        Assert.assertNull(this.heroRepository.getHeroWithHighestLevel());
    }

    @Test
    public void getSomeObjectByLevelSuccessfullyHero() {
        this.heroRepository.create(pesho);
        this.heroRepository.create(gosho);
        Assert.assertEquals(pesho, this.heroRepository.getHeroWithHighestLevel());
    }

    @Test
    public void getHeroByNameSuccessfullyNull() {
        Assert.assertNull(this.heroRepository.getHero("Az"));
    }

    @Test
    public void getHeroByNameSuccessfully() {
        this.heroRepository.create(gosho);
        Assert.assertEquals(gosho, this.heroRepository.getHero("Gosho"));
    }

    @Test
    public void getCollectionSuccessfully() {
        this.heroRepository.create(pesho);
        this.heroRepository.create(gosho);
        Assert.assertEquals(2, this.heroRepository.getHeroes().size());
    }

}
