package L05Polymorphism.Lab.P02Shapes.Variant2;

public class Circle extends Shape {

    private final Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    public final Double getRadius() {
        return this.radius;
    }

    @Override
    public Double calculatePerimeter() {
        if (this.perimeter == null) {
            return 2 * Math.PI * this.radius;
        }
        return this.perimeter;
    }

    @Override
    public Double calculateArea() {
        if (this.area == null) {
            return Math.PI * Math.pow(this.radius, 2);
        }
        return this.area;
    }

}
