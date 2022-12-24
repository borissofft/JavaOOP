package L01WorkingWithAbstraction.Lab.P03StudentSystem;


public class StudentSystem {
    private StudentRepository repository;

    public StudentSystem() {
        this.repository = new StudentRepository();
    }

    @Deprecated
    public StudentRepository getRepository() {
        return this.repository;
    }

    public void parseCommand(String[] args) {

        Command command = CommandFactory.createCommand(args[0]);
        command.execute(this.repository, args);

    }

}
