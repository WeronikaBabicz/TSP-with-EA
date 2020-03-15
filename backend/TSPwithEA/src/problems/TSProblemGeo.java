package problems;

import problemInfo.Point;
import java.util.ArrayList;

public class TSProblemGeo extends TSProblem {

    public TSProblemGeo(ArrayList<Point> allPoints) {
        this.allPoints = allPoints;
    }


    @Override
    public double countDistanceBetweenPoints(Point p1, Point p2) {
        double p1xinRad = Math.toRadians(p1.getX());
        double p1yinRad = Math.toRadians(p1.getY());
        double p2xinRad = Math.toRadians(p2.getX());
        double p2yinRad = Math.toRadians(p2.getY());

        double xdiff = p2xinRad - p1xinRad;
        double ydiff = p2yinRad - p1yinRad;

        double a = Math.pow(Math.sin(xdiff/2),2) + Math.cos(p1xinRad) * Math.cos(p2xinRad) * Math.pow(Math.sin(ydiff/2), 2);

        return (6371 * 2 * Math.asin(Math.sqrt(a)));
    }


}
