
package algorithms;

import problemInfo.City;
import problems.TSProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GreedyAlgorithm implements Algorithm {
    private Individual bestIndividual;
    private TSProblem problem;
    private ArrayList<Individual> population = new ArrayList<Individual>();

    private int populationSize;
    private int startPointIndex;


    public GreedyAlgorithm(TSProblem problem, int populationSize) {
        this.problem = problem;
        this.populationSize = populationSize;
    }


    @Override
    public Individual findBestIndividual() {
        return Collections.min(population, Comparator.comparingDouble(Individual::countFitness));
    }

    @Override
    public void initializePopulation() {
        ArrayList<Integer> startingPoints = initializeCitiesToVisit();
        int min = Math.min(populationSize, problem.allCities.size());
        for (int i = 0; i < min; i++){

            ArrayList<Integer> genotype = new ArrayList<Integer>();
            ArrayList<Integer> pointsToVisit = initializeCitiesToVisit();

            startPointIndex = startingPoints.get((int) (Math.random() * startingPoints.size()));

            addFirstGene(genotype, pointsToVisit, startingPoints);
            fillGenotype(genotype, pointsToVisit);

            Individual individual = new Individual(problem, genotype);
            population.add(individual);
        }
    }

    @Override
    public void runAlgorithm() {
        initializePopulation();
        setBestIndividual(findBestIndividual());
    }

    @Override
    public Individual getResult() {
        return bestIndividual;
    }

    @Override
    public ArrayList<Individual> getPopulation() {
        return population;
    }

    private void setBestIndividual(Individual bestIndividual){
        this.bestIndividual = bestIndividual;
    }

    private int findClosestPoint(ArrayList<Integer> genotype, ArrayList<Integer> citiesToVisit){
        int closest = citiesToVisit.get(0);

        City lastVisited = problem.allCities.get(genotype.get(genotype.size() - 1));
        City nextToVisit = problem.allCities.get(citiesToVisit.get(0));

        double bestDist = problem.countDistanceBetweenPoints(lastVisited, nextToVisit);

        for (int j = 0; j < citiesToVisit.size(); j++){
            nextToVisit = problem.allCities.get(citiesToVisit.get(j));
            if (bestDist > problem.countDistanceBetweenPoints(lastVisited, nextToVisit)){
                closest = citiesToVisit.get(j);
                bestDist = problem.countDistanceBetweenPoints(lastVisited, nextToVisit);
            }
        }
        return closest;
    }

    private ArrayList<Integer> initializeCitiesToVisit(){
        ArrayList<Integer> citiesToVisit = new ArrayList<Integer>();
        fillRange(citiesToVisit, problem.allCities.size());
        return  citiesToVisit;
    }

    private void fillGenotype(ArrayList<Integer> genotype, ArrayList<Integer> citiesToVisit){
        while (citiesToVisit.size() > 0){
            int closestPoint = findClosestPoint(genotype, citiesToVisit);
            addGeneAndMarkAsVisited(genotype, citiesToVisit, closestPoint);
        }
    }

    private void addGeneAndMarkAsVisited(ArrayList<Integer> genotype, ArrayList<Integer> citiesToVisit, int indexPoint){
        addGene(genotype, indexPoint);
        markPointAsVisited(citiesToVisit, indexPoint);
    }

    private void markPointAsVisited(ArrayList<Integer> citiesToVisit ,int pointIndex){
        citiesToVisit.remove(Integer.valueOf(pointIndex));
    }

    private void addFirstGene(ArrayList<Integer> genotype, ArrayList<Integer> citiesToVisit, ArrayList<Integer> startingPoints){
        addGeneAndMarkAsVisited(genotype, citiesToVisit, startPointIndex);
        markPointAsVisited(startingPoints, startPointIndex);
    }

    private void addGene(ArrayList<Integer> genotype, int indexPoint){
        genotype.add(indexPoint);
    }
}