package L05Polymorphism.Lab.P02Shapes.Variant2;

public class Rectangle extends Shape {

    private final Double height; // веднъж сетнати тези стойности в конструктра при създаване на нов обект, няма да могат да бъдат променяни
    private final Double width; // това гарантира че този обект ще има една единствена стойност на периметъра и лицето си, изчислени по-долу

    public Rectangle(Double height, Double width) {
        this.height = height;
        this.width = width;
    }

    public final Double getHeight() {
        return this.height;
    }

    public final Double getWidth() {
        return this.width;
    }

    /**
     * Lazy initialization / evaluation
     */


    @Override
    public Double calculatePerimeter() {
        if (this.perimeter == null) { // Ако периметъра до сега не е бил изчисляван
            return 2 * this.height + 2 * this.width; // изчисли го и го запиши в полето
        }
        return perimeter; // всеки път когато някой извика стойността на вече веднъж изчисления периметър, го върни
    }

    @Override
    public Double calculateArea() {
        if (this.area == null) {
            return this.width * this.height;
        }
        return this.area;
    }

}
