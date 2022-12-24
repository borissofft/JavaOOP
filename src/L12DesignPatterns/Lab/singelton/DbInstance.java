package L12DesignPatterns.Lab.singelton;

// This is Singelton object
public class DbInstance {

    private static volatile DbInstance instance = null; // volatile - for thread safe

//    public DbInstance() {
//        // Simulate DB connection(its slow operation)
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    private DbInstance() { // Забраняваме извикването на конструктора навън
        // Simulate DB connection(its slow operation)

        /**
         * Долната if проверка, може да ни защити от използване на Reflection. Веднъж извикан instance, не позволява да бъде създаден обект от отзи конструктор
         */

        if (instance != null) {
            throw new RuntimeException("To use DbInstance call getInstance()");
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static DbInstance getInstance() {
        if (instance == null) {
            synchronized (DbInstance.class) { // For thread safe
                if (instance == null) {
                    instance = new DbInstance(); // Тази инстанция се създава само първия път, когато някой поиска достъп до тази инстанция(lazy loading)
                }
            }
        }
        return instance;
    }

    public int createTable() {
        System.out.println("Table users created");
        return 1;
    }

}
