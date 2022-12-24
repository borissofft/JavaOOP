package L02Encapsulation.Lab.P02SalaryIncrease;

import java.text.DecimalFormat;

public class Person {

    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }

    public Person(String firstName, String lastName, int age) { // Тhe constructor with fewer parameters must reuse the one with more parameters
        this(firstName, lastName, age, 0);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getAge() {
        return this.age;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

//    public void increaseSalary(double percentBonus) {
//        if (this.getAge() < 30) {
//            this.setSalary(this.getSalary() + (this.getSalary() * percentBonus / 200));
//        } else {
//            this.setSalary(this.getSalary() + (this.getSalary() * percentBonus / 100));
//        }
//    }


//    @Override
//    public String toString() {
//        return String.format("%s %s gets %s leva",
//                this.getFirstName(),
//                this.getLastName(),
//                this.getSalary());
//    }

    DecimalFormat formatter = new DecimalFormat("####.0####");

    public void increaseSalary(double increment) {
        if (this.getAge() < 30) {
            increment = increment / 2;
        }
        this.setSalary(this.getSalary() * (1.00 + increment / 100));

    }

    @Override
    public String toString() {
        return String.format("%s %s gets %s leva",
                this.getFirstName(),
                this.getLastName(),
                formatter.format(this.getSalary()));
    }

}
