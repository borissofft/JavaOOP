package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {
    //TODO: Test Garage class

    private final static int EXAMPLE_MAX_SPEED = 250;

    private Garage garage;
    private Car porsche;
    private Car mercedes;
    private Car audi;


    @Before
    public void setup() {
        this.garage = new Garage();
        this.porsche = new Car("Porche", 300, 1200);
        this.mercedes = new Car("Mercedes", 200, 1500);
        this.audi = new Car("Audi", 220, 1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCarShouldThrow() {
        this.garage.addCar(null);
    }

    @Test
    public void testAddCarSuccessfully() {
        this.garage.addCar(porsche);
        Assert.assertEquals(1, this.garage.getCount());
        this.garage.addCar(porsche);
        Assert.assertEquals(2, this.garage.getCount());
    }

    @Test
    public void testGetCarsSuccessfully() {
        this.garage.addCar(porsche);
        List<Car> carsInGarage = this.garage.getCars();
        Assert.assertEquals(1, this.garage.getCount());
        Assert.assertEquals(this.porsche.getBrand(), carsInGarage.get(0).getBrand());
    }

    @Test
    public void testGetFastestCar() {
        this.garage.addCar(this.porsche);
        this.garage.addCar(this.mercedes);
        this.garage.addCar(this.audi);
        List<Car> carsWithSpeedAboveValue = this.garage.findAllCarsWithMaxSpeedAbove(EXAMPLE_MAX_SPEED);
        Assert.assertEquals(this.porsche.getBrand(), carsWithSpeedAboveValue.get(0).getBrand());
    }

    @Test
    public void testGetTheMostExpensiveCar() {
        this.garage.addCar(this.porsche);
        this.garage.addCar(this.mercedes);
        this.garage.addCar(this.audi);
        Car mostExpensiveCar = this.garage.getTheMostExpensiveCar();
        Assert.assertEquals(this.mercedes.getBrand(), mostExpensiveCar.getBrand());
    }

    @Test
    public void testFindAllCarsByBrand() {
        this.garage.addCar(this.porsche);
        this.garage.addCar(this.porsche);
        this.garage.addCar(this.mercedes);
        this.garage.addCar(this.audi);
        List<Car> cars = this.garage.findAllCarsByBrand(this.porsche.getBrand());
        Assert.assertEquals(2, cars.size());
    }

}