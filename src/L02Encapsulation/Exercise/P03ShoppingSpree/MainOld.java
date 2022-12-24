//package L02Encapsulation.Exercise.P03ShoppingSpree;
//
//import java.util.*;
//
//public class MainOld {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        Map<String, Person> people = new LinkedHashMap<>();
//        Map<String, Product> products = new LinkedHashMap<>();
//
//        try {
//
//            String[] peoplesAndMoney = scanner.nextLine().split(";");
//            for (String str : peoplesAndMoney) {
//                int indexToSplit = str.indexOf("=");
//                String name = str.substring(0, indexToSplit);
//                double money = Double.parseDouble(str.substring(indexToSplit + 1));
//                Person person = new Person(name, money);
//                people.put(name, person);
//            }
//
//            String[] productsAndCost = scanner.nextLine().split(";");
//            for (String str : productsAndCost) {
//                int indexToSplit = str.indexOf("=");
//                String name = str.substring(0, indexToSplit);
//                double cost = Double.parseDouble(str.substring(indexToSplit + 1));
//                Product product = new Product(name, cost);
//                products.put(name, product);
//            }
//
//        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        String input = scanner.nextLine();
//
//        while (!"END".equals(input)) {
//            String[] data = input.split("\\s+");
//            String name = data[0];
//
//            if (products.size() > 0) {
//                String product = data[1];
//
//                Person currentPerson = people.get(name);
//                Product currentProduct = products.get(product);
//
//                try {
//
//                    currentPerson.buyProduct(currentProduct);
//                    System.out.printf("%s bought %s%n", currentPerson.getName(), currentProduct.getName());
//
//                } catch (IllegalStateException ex) {
//                    System.out.println(ex.getMessage());
//                }
//
//            }
//
//            input = scanner.nextLine();
//
//        }
//
//
////        people.entrySet().stream().forEach(person -> {
////
////            boolean haveBoughtProducts = person.getValue().getProducts().size() > 0;
////
////            if (haveBoughtProducts) {
////                String allProducts = person.getValue().getProducts().toString().replaceAll("[\\[\\]]", "");
////                System.out.printf("%s - %s%n", person.getKey(), allProducts);
////            } else {
////                System.out.printf("%s - Nothing bought%n", person.getKey());
////            }
////
////        });
//
////        for (Map.Entry<String, Person> entry : people.entrySet()) {
////            System.out.println(entry.getValue());
////        }
//
//        people.forEach((key, value) -> System.out.println(value));
//
//    }
//
//}
//
//// 90/100