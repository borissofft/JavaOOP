package L05Polymorphism.Lab.P02Shapes.Variant2;

public abstract class Shape {

    /**
     * Това решение не минава с пълни точки, но е добра практика
     * java.lang.AssertionError: Field 'perimeter' access level was not 'private' - не минава защото полетата perimeter и area, тръбва да са protected
     */

    protected Double perimeter;
    protected Double area;

    public abstract Double calculatePerimeter();

    public abstract Double calculateArea();

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
