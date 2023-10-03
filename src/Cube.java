import lombok.Getter;
@Getter
public class Cube implements Shape {
    public double sideLen;
    public Cube(double sideLen) {
        this.sideLen = sideLen;
    }

    @Override
    public double getVolume() {
        return Math.pow(sideLen, 3);
    }
}