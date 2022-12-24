package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PetStoreTests {

    private PetStore myStore;
    private Animal littleDog;
    private Animal bigDog;
    private Animal cat;


    @Before
    public void setup() {
        this.myStore = new PetStore();
        this.littleDog = new Animal("dog", 10, 100.00);
        this.bigDog = new Animal("dog", 50, 70.00);
        this.cat = new Animal("cat", 7, 250.00);
    }

    @Test
    public void constructorSetProperlyCollection() {
        Assert.assertEquals(0, this.myStore.getCount());
    }

    @Test
    public void constructorSetProperlyCollection2() {
        Assert.assertEquals(0, this.myStore.getAnimals().size());
    }


    @Test(expected = IllegalArgumentException.class)
    public void addShouldThrowIfNull() {
        this.myStore.addAnimal(null);
    }

    @Test
    public void addSuccessfully() {
        this.myStore.addAnimal(littleDog);
        this.myStore.addAnimal(bigDog);
        Assert.assertEquals(2, this.myStore.getCount());
    }

    @Test
    public void getMostExpensiveNull() {
        Assert.assertNull(this.myStore.getTheMostExpensiveAnimal());
    }

    @Test
    public void getMostExpensiveSuccessfully() {
        this.myStore.addAnimal(littleDog);
        this.myStore.addAnimal(bigDog);
        this.myStore.addAnimal(cat);
        Assert.assertEquals(this.cat, this.myStore.getTheMostExpensiveAnimal());
    }

    @Test
    public void findAllAnimalBySpecieSuccessfully() {
        this.myStore.addAnimal(littleDog);
        this.myStore.addAnimal(bigDog);
        this.myStore.addAnimal(cat);
        Assert.assertEquals(2, this.myStore.findAllAnimalBySpecie("dog").size());
    }


    @Test
    public void findAllAnimalsWithMaxKilograms() {
        this.myStore.addAnimal(littleDog);
        this.myStore.addAnimal(bigDog);
        this.myStore.addAnimal(cat);
        Assert.assertEquals(2, this.myStore.findAllAnimalsWithMaxKilograms(7).size());
    }
    
}

