package L06SOLID.Lab.demo;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Верният алгоритъм за проверка на две пароли дали са верни е:
 * Истинската парола веднъж вече е хеширана, при проба за логване въвеждаме паролата, след което тя се хешира и чак тогава се сравнява със запазената оригинална
 * Не се използва декриптиране!!!!!
 */

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);

        String password = "my-password";

        PasswordHasher passwordHasher = new PasswordHasher(new Base64Algorithm());
//        String hash = passwordHasher.hash(password, "base64");
//        String hash2 = passwordHasher.hash(password, "sha256");
//        System.out.println(hash);
//        System.out.println(hash2);

        String hash = passwordHasher.hash(password);
//        System.out.println(hash);

        System.out.print("Enter password: ");
        String loginInfo = scanner.nextLine();

        while (!passwordHasher.hash(loginInfo).equals(hash)) { // докато не си въведе вярно паролата, въведи нова парола

            System.out.print(System.lineSeparator() + "Enter password: ");
            loginInfo = scanner.nextLine();

        }

        System.out.println("Welcome!");

    }

    public static String decodeBase64Hash(String hash, PasswordDecoder passwordDecoder) {
        return passwordDecoder.decode(hash);
    }

}
