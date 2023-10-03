import lombok.Getter;
@Getter
public class Pyramid implements Shape {
    public double baseLength;
    public double height;

    public Pyramid(double baseLength, double height) {
        this.baseLength = baseLength;
        this.height = height;
    }
    @Override
    public double getVolume() {
        return (1.0 / 3.0) * baseLength * baseLength * height;
    }
}