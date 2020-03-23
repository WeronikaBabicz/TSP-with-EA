package problems;

import problemInfo.City;

import java.util.ArrayList;

public class TSProblemPlanar extends TSProblem {

    public TSProblemPlanar(ArrayList<City> allCities) {
        this.allCities = allCities;
    }

    @Override
    public double countDistanceBetweenPoints(City p1, City p2){
        return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
    }

}
