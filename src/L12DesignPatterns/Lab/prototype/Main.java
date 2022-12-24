package L12DesignPatterns.Lab.prototype;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {

        // results 1000000

        /**
         * Клонирането е много по-бърза операция, отколото всеки път да трябва да се създава нов обект с new
         */

        Register register = new Register();
        Item book = register.getItem("Book"); // Prototyping... - Това е копие на истинският обект, който седи в класа Register

        book.setName("The hobbit");

        System.out.println(book.getName());

    }
}
