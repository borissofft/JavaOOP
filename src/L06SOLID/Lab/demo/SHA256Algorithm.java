package L06SOLID.Lab.demo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Algorithm implements Encrypt {
    @Override
    public String encode(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] encodedHash = digest.digest(password.getBytes());
        return new String(encodedHash);
    }

}
