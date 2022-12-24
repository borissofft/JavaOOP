package L06SOLID.Lab.p05_DependencyInversion.p03_Database;


// DependencyInversion се постига чрез подаване на зависимостите през конструктора или през сетъри!

public class Courses {

    private Data database;

    public Courses(Data database) {
        this.database = database;
    }

    public void printAll() {
//        Data database = new Data();
        Iterable<String> courses = this.database.courseNames();

        //print courses
    }

    public void printIds() {
//        Data database = new Data();
        Iterable<Integer> coursesIds = this.database.courseIds();

        //print course ids
    }

    public void printById(int id) {
//        Data database = new Data();
        String course = this.database.getCourseById(id);

        // print course
    }

    public void search(String substring) {
//        Data database = new Data();
        Iterable<String> courses = this.database.search(substring);

        // print courses
    }

}
