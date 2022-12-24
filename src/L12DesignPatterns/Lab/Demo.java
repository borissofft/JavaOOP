package L12DesignPatterns.Lab;

/**
 * Useful site to understand design-patterns
 * https://refactoring.guru/design-patterns/catalog
 */

public class Demo {
    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime(); // this object is singelton
        System.out.println(runtime); // принтираме адреса на обекта

        runtime.gc(); // вика garbage collector - а за да почисти, но няма гаранция кога ще го направи, виж документацията

        Runtime secondRuntime = Runtime.getRuntime();
        System.out.println(secondRuntime); // принтираме адреса на обекта

        /**
         * Виждаме че адресите и на двата обекта са едни и същи. Това потвърждава дефиницията за singelton, че можем да създадем само един обект от този клас(една инстанция)
         */
    }
}
