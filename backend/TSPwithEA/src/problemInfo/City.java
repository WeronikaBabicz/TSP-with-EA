package problemInfo;

public class City {
    private double x;
    private double y;

    public City(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString(){
        return "(" + x + "," + y + ")";
    }
}