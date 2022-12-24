package L12DesignPatterns.Lab.prototype;

import java.util.HashMap;
import java.util.Map;

public class Register { // Регистъра пази основните прототипи

    private Map<String, Item> registry;

    public Register() {
        this.registry = new HashMap<>();
        loadTypes();
    }

    private void loadTypes() {
        Book book = new Book("Lord of the rings", "lord//img", 29.99); // прототип 1
        book.setAuthor("J. R. R. Tolkien");
        registry.put("Book", book);

        Music music = new Music("Slear", "slair//img", 9.99); // прототип 2
        music.setDuration(3.14);
        registry.put("Music", music);
    }

    public Item getItem(String type) {

        try {
            return registry.get(type).clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }
    }

}
