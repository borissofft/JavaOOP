package L05Polymorphism.Lab.P02Shapes;

public abstract class Shape {

    private Double perimeter;
    private Double area;

    public abstract void calculatePerimeter();
    public abstract void calculateArea();

    public Double getPerimeter() {
        return this.perimeter;
    }

    protected void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    public Double getArea() {
        return this.area;
    }

    protected void setArea(Double area) {
        this.area = area;
    }

}
