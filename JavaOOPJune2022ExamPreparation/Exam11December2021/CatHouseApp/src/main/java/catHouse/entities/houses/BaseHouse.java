package catHouse.entities.houses;

import catHouse.common.ConstantMessages;
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

    protected BaseHouse(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.toys = new ArrayList<>();
        this.cats = new ArrayList<>();
    }


    @Override
    public int sumSoftness() {
        return this.toys.stream().mapToInt(Toy::getSoftness).sum();
    }

    @Override
    public void addCat(Cat cat) {
        if (this.cats.size() < this.capacity) {
            this.cats.add(cat);
        } else {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_CAT);
        }
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

        Collection<Toy> toysInHouse = this.getToys();

        String noCats = null;
        Collection<Cat> catsInHouse = this.getCats();
        if (catsInHouse.size() == 0) {
            noCats = "none";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s:", this.name, this.getClass().getSimpleName())).append(System.lineSeparator());
        sb.append("Cats: ");
        if (noCats != null) {
            sb.append(noCats).append(System.lineSeparator());
        } else {
            sb.append(String.format("%s", this.cats.stream().map(Cat::getName).collect(Collectors.joining(" ")))).append(System.lineSeparator());
        }
        sb.append("Toys: ").append(this.toys.size()).append(" Softness: ").append(this.toys.stream().mapToInt(Toy::getSoftness).sum());

        return sb.toString();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
