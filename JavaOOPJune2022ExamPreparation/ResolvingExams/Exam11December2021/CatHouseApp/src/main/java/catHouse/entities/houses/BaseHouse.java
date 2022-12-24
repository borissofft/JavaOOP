package catHouse.entities.houses;

import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.toys.Toy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class BaseHouse implements House {

    private String name;
    private int capacity;
    private Collection<Toy> toys;
    private Collection<Cat> cats;

    public BaseHouse(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity; // The number of Cat аn House can have.
        this.toys = new ArrayList<>();
        this.cats = new ArrayList<>();
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.HOUSE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int sumSoftness() {
        return this.toys.stream().mapToInt(Toy::getSoftness).sum();
    }

    @Override
    public void addCat(Cat cat) {
        if (this.cats.size() == this.capacity) {
            throw new IllegalStateException("Not enough capacity for this cat.");
        }
        this.cats.add(cat);
    }

    @Override
    public void removeCat(Cat cat) {
        this.cats.remove(cat);
    }

    @Override
    public void buyToy(Toy toy) {
        this.toys.add(toy);
    }

    @Override
    public void feeding() {
        this.cats.forEach(Cat::eating);
    }

    @Override
    public String getStatistics() {
        String catNames = "none";
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s:", this.name, this.getClass().getSimpleName())).append(System.lineSeparator());
        sb.append("Cats: ");
        if (this.cats.size() != 0) {
            catNames = this.cats.stream().map(Cat::getName).collect(Collectors.joining(" "));
        }
        sb.append(catNames).append(System.lineSeparator());
        sb.append(String.format("Toys: %d Softness: %d", this.toys.size(), this.toys.stream().mapToInt(Toy::getSoftness).sum()));
        return sb.toString().trim();
    }

    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public Collection<Cat> getCats() {
        return this.cats;
    }

    @Override
    public Collection<Toy> getToys() {
        return this.toys;
    }

}
