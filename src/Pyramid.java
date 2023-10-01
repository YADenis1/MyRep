public class Pyramid implements Shape {
    private double baseLength;
    private double height;

    public Pyramid(double baseLength, double height) {
        this.baseLength = baseLength;
        this.height = height;
    }

    public double getBaseLength() {
        return baseLength;
    }

    public void setBaseLength(double baseLength) {
        this.baseLength = baseLength;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double getVolume() {
        return (1.0 / 3.0) * baseLength * baseLength * height;
    }
}