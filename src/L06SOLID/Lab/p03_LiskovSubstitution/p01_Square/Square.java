package L06SOLID.Lab.p03_LiskovSubstitution.p01_Square;

public class Square extends Shape {

    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return this.side * this.side;
    }

}
