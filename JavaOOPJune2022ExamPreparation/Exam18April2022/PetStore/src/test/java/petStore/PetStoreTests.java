package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PetStoreTests {

    private PetStore petStore;
    private Animal littleDog;
    private Animal bigDog;
    private Animal cat;

    @Before
    public void setup() {
        this.petStore = new PetStore();
        this.littleDog = new Animal("dog",20, 3000);
        this.bigDog = new Animal("dog",70, 1000);
        this.cat = new Animal("cat",10, 1500);
    }

    @Test
    public void constructorRightInitialize() {
        Assert.assertEquals(0, petStore.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAnimalShouldThrowAddingNull() {
        this.petStore.addAnimal(null);
    }

    @Test
    public void addAnimalShouldAddSuccessfully() {
        this.petStore.addAnimal(littleDog);
        Assert.assertEquals(1, this.petStore.getCount());
        this.petStore.addAnimal(bigDog);
        Assert.assertEquals(2, this.petStore.getCount());
    }

    @Test
    public void addAnimalShouldAddSuccessfullyRightAnimal() {
        this.petStore.addAnimal(littleDog);
        this.petStore.addAnimal(bigDog);
        Assert.assertEquals(bigDog, this.petStore.getAnimals().get(1));
    }

    @Test
    public void getAnimalsShouldReturnAllAddedAnimals() {
        this.petStore.addAnimal(littleDog);
        this.petStore.addAnimal(bigDog);
        this.petStore.addAnimal(cat);
        List<Animal> animals = this.petStore.getAnimals();
        Assert.assertEquals(3, this.petStore.getCount());
    }

    @Test
    public void getTheMostExpensiveAnimalShouldReturnNullIfEmptyCollection() {
        Assert.assertNull(this.petStore.getTheMostExpensiveAnimal());
    }

    @Test
    public void getTheMostExpensiveAnimalShouldReturnTheRightAnimal() {
        this.petStore.addAnimal(littleDog);
        this.petStore.addAnimal(bigDog);
        this.petStore.addAnimal(cat);
        Assert.assertEquals(littleDog, this.petStore.getTheMostExpensiveAnimal());
    }

    @Test
    public void findAllAnimalBySpecieReturnRightAnimals() {
        this.petStore.addAnimal(littleDog);
        this.petStore.addAnimal(bigDog);
        this.petStore.addAnimal(cat);
        Assert.assertEquals(2, this.petStore.findAllAnimalBySpecie("dog").size());
    }

    @Test
    public void findAllAnimalsWithMaxKilogramsSuccessfully() {
        this.petStore.addAnimal(littleDog);
        this.petStore.addAnimal(bigDog);
        this.petStore.addAnimal(cat);
        Assert.assertEquals(2, this.petStore.findAllAnimalsWithMaxKilograms(10).size());
    }

}

