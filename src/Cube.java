public class Cube implements Shape {
    private double sideLen;

    public Cube(double sideLen) {
        this.sideLen = sideLen;
    }

    public double getSideLength() {
        return sideLen;
    }

    public void setSideLength(double sideLen) {
        this.sideLen = sideLen;
    }

    public double getVolume() {
        return Math.pow(sideLen, 3);
    }
}