//package L02Encapsulation.Exercise.P03ShoppingSpree;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Scanner;
//import java.util.stream.Collectors;
//
//public class Main2{
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        List<Person> people = new ArrayList<>();
//        List<Product> products = new ArrayList<>();
//
//        try {
//
//            people = Arrays.stream(scanner.nextLine().split(";"))
//                    .map(p -> {
//                        String[] data = p.split("=");
//                        return new Person(data[0], Double.parseDouble(data[1]));
//                    })
//                    .collect(Collectors.toList());
//
//            products = Arrays.stream(scanner.nextLine().split(";"))
//                    .map(p -> {
//                        String[] data = p.split("=");
//                        return new Product(data[0], Double.parseDouble(data[1]));
//                    })
//                    .collect(Collectors.toList());
//
//        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
//            return;
//        }
//
//
//        String input = scanner.nextLine();
//
//        while (!"END".equals(input)) {
//
//            String[] data = input.split("\\s+");
//
//            for (Person person : people) {
//                if (person.getName().equals(data[0])) {
//                    Product product = null;
//                    for (Product p : products) {
//                        if (p.getName().equals(data[1])) {
//                            product = p;
//                            break;
//                        }
//                    }
//
//                    if (product != null) {
//                        try {
//                            person.buyProduct(product);
//                            System.out.printf("%s bought %s%n", person.getName(), product.getName());
//                        } catch (IllegalStateException ex) {
//                            System.out.println(ex.getMessage());
//                        }
//                    }
//                    break;
//                }
//
//            }
//
//            input = scanner.nextLine();
//
//        }
//
//        people.forEach(System.out::println);
//
//    }
//}