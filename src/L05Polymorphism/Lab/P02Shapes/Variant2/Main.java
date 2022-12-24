package L05Polymorphism.Lab.P02Shapes.Variant2;

public class Main {
    public static void main(String[] args) {

        Shape rectangle = new Rectangle(2.0, 3.0);
        Shape circle = new Circle(5.0);

        System.out.println(rectangle.calculateArea());
        System.out.println(rectangle.calculatePerimeter());

        System.out.println(circle.calculateArea());
        System.out.println(circle.calculatePerimeter());

    }
}

// 66/100, но е добра практика за решение на такъв проблем. Изчисляваме само веднъж това което н трябва и след това само го връщаме, при поискване