package L06SOLID.Lab.demo;

import java.util.Base64;

public class PasswordDecoder {

    public String decode(String hash) {
        return new String(Base64.getDecoder().decode(hash.getBytes()));
    }

}
