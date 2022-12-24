package L01WorkingWithAbstraction.Lab.P03StudentSystem;

public class ShowCommand implements Command {

    public void execute(StudentRepository repository, String[] args) {
        var name = args[1];
        if (repository.containsStudentByName(name)) {
            var student = repository.findOneByName(name);
            System.out.println(student);
        }
    }

}
