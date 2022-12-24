package L06SOLID.Lab.p04_InterfaceSegregation.p02_identity.interfaces;

public interface User {

    String getEmail();

    String getPasswordHash();

    void changePassword(String oldPass, String newPass);

}
