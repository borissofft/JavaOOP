package L02Encapsulation.Exercise.P03ShoppingSpree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {

    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    private void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }

    public String getName() {
        return this.name;
    }

    public void buyProduct(Product product) {
        double cost = product.getCost();
        if (this.money >= cost) {
            this.money -= cost;
            this.products.add(product);
        } else {
            throw new IllegalArgumentException(String.format("%s can't afford %s", this.name, product.getName()));
        }
    }

//    public List<Product> getProducts() {
//        return Collections.unmodifiableList(this.products);
//    }

//    @Override
//    public String toString() {
//
//        String output = "Nothing bought";
//
//        if (!this.products.isEmpty()) {
////            output = this.products.stream().map(Product::getName).collect(Collectors.joining(", "));
//            output = this.products.toString().replaceAll("[\\[\\]]", "");
//        }
//
//        return this.getName() + " - " + output;
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append(" - ");

//        List<String> productList = this.products.stream().map(Product::getName).collect(Collectors.toList());
//        sb.append(String.join(",", productList));

        String productData = products
                .stream()
                .map(Product::getName)
                .collect(Collectors.joining(", "));
        if (productData.isEmpty()) {
            sb.append("Nothing bought");
        } else {
            sb.append(productData);
        }

        return sb.toString();
    }

}

