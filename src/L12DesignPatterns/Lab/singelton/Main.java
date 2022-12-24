package L12DesignPatterns.Lab.singelton;

import L12DesignPatterns.Lab.singelton.DbInstance;

public class Main {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

//        DbInstance dbInstance = new DbInstance();
//        dbInstance.createTable();
        DbInstance dbInstance = DbInstance.getInstance(); // Singelton
        dbInstance.createTable();

        long end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();

//        DbInstance dbInstanceTwo = new DbInstance(); // създаването на "нов" обект забавя процеса отново
//        dbInstanceTwo.createTable();

//        dbInstance.createTable(); // тук извикваме първоначално създадения обект и пестим време

        DbInstance dbInstanceTwo = DbInstance.getInstance(); // Singelton, тук се използва вече създадената инстанция при извикване на getInstance()
        dbInstanceTwo.createTable();


        end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
