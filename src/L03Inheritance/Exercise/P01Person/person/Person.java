package L03Inheritance.Exercise.P01Person.person; // have to be commented

//package person; // "Original" package - for Judge

public class Person {

    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

}
