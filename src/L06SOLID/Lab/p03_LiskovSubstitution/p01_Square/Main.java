package L06SOLID.Lab.p03_LiskovSubstitution.p01_Square;

public class Main {
    public static void main(String[] args) {

        Shape shape = new Rectangle(5, 25);
        System.out.println(shape.getArea());
        Shape shape1 = new Square(5);
        System.out.println(shape1.getArea());

    }
}
