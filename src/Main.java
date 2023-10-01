// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Main {

    public static void main(String[] args){
        Cube cube = new Cube(5.0);
        double cubeVolume = cube.getVolume();
        System.out.println("Cube volume: "+cubeVolume);

        Pyramid pyramid = new Pyramid(2.0, 6.0);
        double pyramidVolume = pyramid.getVolume();
        System.out.println("Pyramid volume: "+pyramidVolume);
    }

;
}
