public class Pyramid implements Shape {
    private double baseLength;
    private double height;

    public Pyramid(double baseLength, double height) {
        this.baseLength = baseLength;
        this.height = height;
    }
    @Override
    public double getVolume() {
        return (1.0 / 3.0) * baseLength * baseLength * height;
    }
}