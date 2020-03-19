package problems;

import problemInfo.Point;

import java.util.ArrayList;

public abstract class TSProblem {
    public ArrayList<Point> allPoints = new ArrayList<>();

    public double countFitness(ArrayList<Integer> genotype){
        ArrayList<Point> visitedPoints = setVisitedPoints(genotype);

        double fitnessScore = 0;
        for (int i = 0; i < visitedPoints.size() - 1; i++){
            fitnessScore += countDistanceBetweenPoints(visitedPoints.get(i), visitedPoints.get(i+1));
        }
        fitnessScore += countDistanceBetweenPoints(visitedPoints.get(visitedPoints.size() - 1), visitedPoints.get(0));
        return fitnessScore;
    }

    private ArrayList<Point> setVisitedPoints(ArrayList<Integer> genotype){
        ArrayList<Point> visitedPoints = new ArrayList<Point>();
        for (int i = 0; i < allPoints.size(); i++)
            visitedPoints.add(allPoints.get(genotype.get(i)));

        return visitedPoints;
    }

    abstract public double countDistanceBetweenPoints(Point p1, Point p2);
}
