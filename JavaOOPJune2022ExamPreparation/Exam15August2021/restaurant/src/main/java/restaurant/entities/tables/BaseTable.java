package restaurant.entities.tables;

import restaurant.common.ExceptionMessages;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseTable implements Table {

    private Collection<HealthyFood> healthyFood; // Accessible only by the base class
    private Collection<Beverages> beverages; // Accessible only by the base class
    private int number;
    private int size;
    private int numberOfPeople; // the counter of people who want a table
    private double pricePerPerson; // the price per person for the table
    private boolean isReservedTable;
    private double allPeople; // calculates the price for all people

    protected BaseTable(int number, int size, double pricePerPerson) {
        this.number = number;
        this.setSize(size);
        this.pricePerPerson = pricePerPerson;
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
    }

    private void setSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    private void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    protected void setAllPeople() {
        this.allPeople = this.numberOfPeople * this.pricePerPerson;
    }

    @Override
    public int getTableNumber() {
        return this.number;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int numberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return this.isReservedTable;
    }

    @Override
    public double allPeople() {
        return this.allPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        this.isReservedTable = true;
        setNumberOfPeople(numberOfPeople);
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        double sumHealthyFood = this.healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();
        double sumBeverages = this.beverages.stream().mapToDouble(Beverages::getPrice).sum();
//        return sumHealthyFood + sumBeverages;
        this.setAllPeople();
        return this.allPeople + sumHealthyFood + sumBeverages;
    }

    @Override
    public void clear() {
        this.healthyFood.clear();
        this.beverages.clear();
        this.isReservedTable = false;
        this.numberOfPeople = 0;
        this.pricePerPerson = 0;
    }

    @Override
    public String tableInformation() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Table - %d", this.number)).append(System.lineSeparator());
        sb.append(String.format("Size - %d", this.size)).append(System.lineSeparator());
        sb.append(String.format("Type - %s", this.getClass().getSimpleName())).append(System.lineSeparator());
        sb.append(String.format("All price - %f", this.pricePerPerson));
        return sb.toString().trim();
    }

}
