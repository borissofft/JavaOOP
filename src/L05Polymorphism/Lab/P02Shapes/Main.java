package L05Polymorphism.Lab.P02Shapes;

public class Main {
    public static void main(String[] args) {

        Shape rectangle = new Rectangle(2.0, 3.0);
        Shape circle = new Circle(5.0);

        rectangle.calculateArea();
        System.out.println(rectangle.getArea());
        rectangle.calculatePerimeter();
        System.out.println(rectangle.getPerimeter());

        circle.calculateArea();
        System.out.println(circle.getArea());
        circle.calculatePerimeter();
        System.out.println(circle.getPerimeter());

    }
}
