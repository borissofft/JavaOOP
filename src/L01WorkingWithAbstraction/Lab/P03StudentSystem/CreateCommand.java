package L01WorkingWithAbstraction.Lab.P03StudentSystem;

public class CreateCommand implements Command {

    public void execute(StudentRepository repository, String[] args) {
        var name = args[1];
        var age = Integer.parseInt(args[2]);
        var grade = Double.parseDouble(args[3]);
        if (!repository.containsStudentByName(name)) {
            var student = new Student(name, age, grade);
            repository.registerStudent(name, student);
        }
    }

}
