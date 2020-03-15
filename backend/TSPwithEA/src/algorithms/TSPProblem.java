package algorithms;

import dataParser.Point;

import java.util.ArrayList;

public abstract class TSPProblem {
    ArrayList<Point> allPoints = new ArrayList<>();
    private ArrayList<Point> visitedPoints = new ArrayList<>();


    double countFitness(ArrayList<Integer> genotype){
        this.setVisitedPoints(genotype);
        double fitnessScore = 0;
        for (int i = 0; i < visitedPoints.size() - 1; i++){
            fitnessScore += countDistanceBetweenPoints(visitedPoints.get(i), visitedPoints.get(i+1));
        }
        fitnessScore += countDistanceBetweenPoints(visitedPoints.get(visitedPoints.size()), visitedPoints.get(0));
        return fitnessScore;
    };

    private void setVisitedPoints(ArrayList<Integer> genotype){
        visitedPoints = allPoints;
        for (int i = 0; i < allPoints.size(); i++){
            visitedPoints.set(genotype.get(i), allPoints.get(i));
        }
    }

    abstract public double countDistanceBetweenPoints(Point p1, Point p2);
}
