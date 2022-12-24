package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {

    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
        House house = null;
        switch (type) {
            case "ShortHouse":
                house = new ShortHouse(name);
                break;
            case "LongHouse":
                house = new LongHouse(name);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }
        this.houses.add(house);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy = null;
        switch (type) {
            case "Ball":
                toy = new Ball();
                break;
            case "Mouse":
                toy = new Mouse();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_TOY_TYPE);
        }
        this.toys.buyToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = this.toys.findFirst(toyType);
        if (toy == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND, toyType));
        }

//        House house = null;
//        for (House h : this.houses) {
//            if (h.getName().equals(houseName)) {
//                house = h;
//                break;
//            }
//        }

        House house = getHouseByName(houseName, this.houses);
        house.buyToy(toy);
        this.toys.removeToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {

        boolean validCatType = ("ShorthairCat".equals(catType) || "LonghairCat".equals(catType));
        if (!validCatType) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);
        }

        Cat cat = null;
        switch (catType) {
            case "ShorthairCat":
                cat = new ShorthairCat(catName, catBreed, price);
                break;
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                break;
        }

        House house = getHouseByName(houseName, this.houses);

        if ("ShortHouse".equals(house.getClass().getSimpleName()) && "ShorthairCat".equals(catType)) {
            house.addCat(cat);
        } else if ("LongHouse".equals(house.getClass().getSimpleName()) && "LonghairCat".equals(catType)) {
            house.addCat(cat);
        } else {
            return ConstantMessages.UNSUITABLE_HOUSE;
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
    }

    @Override
    public String feedingCat(String houseName) {
        House house = getHouseByName(houseName, this.houses);
        house.feeding();
        return String.format(ConstantMessages.FEEDING_CAT, house.getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = getHouseByName(houseName, this.houses);
        Collection<Cat> cats = house.getCats();
        Collection<Toy> toys = house.getToys();
        double catPricesSum = cats.stream().mapToDouble(Cat::getPrice).sum();
        double toyPricesSum = toys.stream().mapToDouble(Toy::getPrice).sum();
        return String.format(ConstantMessages.VALUE_HOUSE, houseName, catPricesSum + toyPricesSum);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (House house : this.houses) {
            sb.append(house.getStatistics()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    private House getHouseByName(String houseName, Collection<House> hoses) {
        House house = null;
        for (House h : houses) {
            if (h.getName().equals(houseName)) {
                house = h;
                break;
            }
        }
        return house;
    }

    private House getHouseByNameSecondVariant(String houseName, Collection<House> hoses) {
        House house;
        return hoses
                .stream()
                .filter(h -> h.getName().equals(houseName))
                .findFirst()
                .orElse(null);
    }

}
