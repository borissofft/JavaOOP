package L04InterfacesAndAbstraction.Exercise.P05Telephony;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> numbers = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());
        List<String> sites = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());

        Smartphone smartphone = new Smartphone(numbers, sites);

        System.out.println(smartphone.call());
        System.out.println(smartphone.browse());

    }
}


//        Smartphone smartphone = new Smartphone(numbers, sites);
//        boolean invalid = false;

//        for (String number : smartphone.getNumbers()) {
//            invalid = false;
//            for (char symbol : number.toCharArray()) {
//                if (!Character.isDigit(symbol)) {
//                    System.out.println("Invalid number!");
//                    invalid = true;
//                    break;
//                }
//            }
//            if (!invalid) {
//                System.out.println(smartphone.call() + number);
//            }
//        }
//
//        for (String url : smartphone.getUrls()) {
//            invalid = false;
//            for (char symbol : url.toCharArray()) {
//                if (Character.isDigit(symbol)) {
//                    System.out.println("Invalid URL!");
//                    invalid = true;
//                    break;
//                }
//            }
//            if (!invalid) {
//                System.out.println(smartphone.browse() + url + "!");
//            }
//        }


// 50/100 -> Трябва имплементацията да е в самите методи call(),browse()