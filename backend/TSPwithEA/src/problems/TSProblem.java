package problems;

import problemInfo.City;

import java.util.ArrayList;

public abstract class TSProblem {
    public ArrayList<City> allCities = new ArrayList<>();

    public double calculateFitness(ArrayList<Integer> genotype){

        double fitnessScore = 0;
        for (int i = 0; i < genotype.size() - 1; i++){
            fitnessScore += countDistanceBetweenPoints(allCities.get(genotype.get(i)), allCities.get(genotype.get(i+1)));
        }
        fitnessScore += countDistanceBetweenPoints(allCities.get(genotype.get(genotype.size() -1)), allCities.get(genotype.get(0)));
        return fitnessScore;
    }


    abstract public double countDistanceBetweenPoints(City p1, City p2);
}
