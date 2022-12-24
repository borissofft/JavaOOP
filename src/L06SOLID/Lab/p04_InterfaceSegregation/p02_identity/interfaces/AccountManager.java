package L06SOLID.Lab.p04_InterfaceSegregation.p02_identity.interfaces;

public interface AccountManager {

    Iterable<User> getAllUsersOnline();

    Iterable<User> getAllUsers();

    User getUserByName(String name);

}
