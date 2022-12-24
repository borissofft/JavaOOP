package L06SOLID.Lab.p05_DependencyInversion.p02_Worker;

public class Manager {

    private  Worker worker;

    public Manager(Worker worker) {
        this.worker = worker;
        this.init();  // Винаги трябва функциите само да се извикват в конструктора, тъй като може да искаме да добавим още функционалности
        // и ще трябва да променим само самата функция/метод
    }

    private void init() {
        this.worker.work();
        // може да добавим нова функционалност...
    }

}
