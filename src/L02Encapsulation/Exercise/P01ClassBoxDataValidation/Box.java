package L02Encapsulation.Exercise.P01ClassBoxDataValidation;

public class Box {

    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    private void setLength(double length) {
//        if (length <= 0) {
//            throw new IllegalArgumentException("Length cannot be zero or negative.");
//        }
        validateValue(length, "Length");
        this.length = length;
    }

    private void setWidth(double width) {
//        if (width <= 0) {
//            throw new IllegalArgumentException("Width cannot be zero or negative.");
//        }
        validateValue(width, "Width");
        this.width = width;
    }

    private void setHeight(double height) {
//        if (height <= 0) {
//            throw new IllegalArgumentException("Height cannot be zero or negative.");
//        }
        validateValue(height, "Height");
        this.height = height;
    }

    private void validateValue(double value, String dimension) {
        if (value <= 0) {
            throw new IllegalArgumentException(dimension + " cannot be zero or negative.");
        }
    }

    public double getLength() {
        return this.length;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public double calculateSurfaceArea() {
        return 2 * getLength() * getWidth() + 2 * getLength() * getHeight() + 2 * getWidth() * getHeight();
    }

    public double calculateLateralSurfaceArea() {
        return 2 * getLength() * getHeight() + 2 * getWidth() * getHeight();
    }

    public double calculateVolume() {
        return getLength() * getWidth() * getHeight();
    }

}
