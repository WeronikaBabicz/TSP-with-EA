package problems;

import problemInfo.Point;

import java.util.ArrayList;

public class TSProblemPlanar extends TSProblem {

    public TSProblemPlanar(ArrayList<Point> allPoints) {
        this.allPoints = allPoints;
    }

    @Override
    public double countDistanceBetweenPoints(Point p1, Point p2){
        return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
    }

}
