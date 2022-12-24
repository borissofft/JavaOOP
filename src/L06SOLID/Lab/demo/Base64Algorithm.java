package L06SOLID.Lab.demo;

import java.util.Base64;

/**
 * Interface Segregation
 * С този алгоритъм можем например да кодираме някакво изображение и след това да го декодираме
 */

public class Base64Algorithm implements Encrypt, Decrypt { // В този клас имаме два метода правщи различни операции, но не нарушаваме Single-Responsibility, защото е отговорност
    // на този криптиращ алгоритъм да може да криптира и декриптира!!!

    @Override
    public String encode(String password) {
        return new String(Base64.getEncoder().encode(password.getBytes()));
    }

    @Override
    public String decode(String hash) {
        return new String(Base64.getDecoder().decode(hash.getBytes()));
    }

}
