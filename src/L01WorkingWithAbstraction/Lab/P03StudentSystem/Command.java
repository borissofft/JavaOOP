package L01WorkingWithAbstraction.Lab.P03StudentSystem;

public interface Command {

    void execute(StudentRepository repository, String[] args);

}
