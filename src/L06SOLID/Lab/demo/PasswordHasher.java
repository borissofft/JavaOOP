package L06SOLID.Lab.demo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHasher {

    /**
     * Single responsibility - този клас трябва да има алгоритъм който само хешира паролата
     * Не трябва да се прави нищо друго в този клас(да се save - ва паролата или нещо друго)
     */

    private Encrypt encrypt;

    public PasswordHasher() {
        this.encrypt = new SHA256Algorithm(); // Тук имаме директна зависимост и винаги трябва да подаваме с какъв алгоритъм да криптираме / декриптираме
    }

    public PasswordHasher(Encrypt encrypt) {
        this.encrypt = encrypt;
    }

    /**
     * Dependency Inversion - ще си направим нов конструктор, като през конструктора ще инжектираме зависимостите
     * Инжектирането попринцип може да стане и през сетър или някакъв друг метод
     */



    public String hash(String password, String hashAlgorithm) throws NoSuchAlgorithmException {

        String hash;
        /**
         * Нарушаване на open / close принципа - при добавяне на нов алгоритъм за хеширане ни се налага да променяме кода на метода като добавяме нова
         * if - else клауза...
         * Трябва да създадем абстрактен модел(с класове и интерфейси) където да се извършват всички опрации - interface Encrypt, class Base64Algorithm
         * Трябва в конструктора на този клас  PasswordHasher(), да си зададем кой хеширащ алгоритъм искаме да използваме, без да пипаме логиката в тялото на целия клас
         */

        if (hashAlgorithm.equals("base64")) {
            hash = new String(Base64.getEncoder().encode(password.getBytes()));
        } else if (hashAlgorithm.equals("sha256")) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(
                    password.getBytes());
            hash = new String(encodedHash);
        } else if (hashAlgorithm.equals("sha128")) {
            MessageDigest digest = MessageDigest.getInstance("SHA-128");
            byte[] encodedHash = digest.digest(
                    password.getBytes());
            hash = new String(encodedHash);
        } else {
            throw new IllegalArgumentException();
        }
        return hash;
    }

    /**
     * Liskov - базовият клас е Encrypt(нищо че в случая е интерфейс - това е идеята) и децата му (Base64Algorithm, SHA256Algorithm), не чупят работата на програмата,
     * не чупят бащиния клас!
     */

    public String hash(String password) throws NoSuchAlgorithmException {

        return this.encrypt.encode(password);

    }

}
