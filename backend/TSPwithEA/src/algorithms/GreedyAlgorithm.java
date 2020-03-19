package algorithms;

import problemInfo.Point;
import problems.TSProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class GreedyAlgorithm implements Algorithm {
    private Individual bestIndividual;
    private TSProblem problem;
    private ArrayList<Individual> population = new ArrayList<Individual>();

    private int numberOfAlgorithmRuns = 10;
    private int startPointIndex;

    public GreedyAlgorithm(TSProblem problem) {
        this.problem = problem;
    }


    public GreedyAlgorithm(TSProblem problem, int numberOfAlgorithmRuns) {
        this.problem = problem;
        this.numberOfAlgorithmRuns = numberOfAlgorithmRuns;
    }

    public void setProblem(TSProblem problem) {
        this.problem = problem;
    }

    public void setStartPointIndex(int startPointIndex) {
        this.numberOfAlgorithmRuns = numberOfAlgorithmRuns;
    }




    @Override
    public Individual findBestIndividual() {
        return Collections.min(population, Comparator.comparingDouble(Individual::countFitness));
    }

    @Override
    public void initializePopulation() {
        ArrayList<Integer> startingPoints = initializePointsToVisit();
        for (int i = 0; i < numberOfAlgorithmRuns; i++){
            Individual individual = new Individual(problem);
            ArrayList<Integer> genotype = new ArrayList<Integer>();
            ArrayList<Integer> pointsToVisit = initializePointsToVisit();

            startPointIndex = startingPoints.get((int) (Math.random() * startingPoints.size()));

            fillFirstGene(genotype, pointsToVisit, startingPoints);
            fillGenotype(genotype, pointsToVisit);

            individual.setGenotype(genotype);
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
        System.out.println(bestIndividual.countFitness());
        return bestIndividual;
    }


    private void setGene(ArrayList<Integer> genotype, int indexPoint){
        genotype.add(indexPoint);
    }

    private void setBestIndividual(Individual bestIndividual){
        this.bestIndividual = bestIndividual;
    }

    private int findClosestPoint(ArrayList<Integer> genotype, ArrayList<Integer> pointsToVisit){
        int closest = pointsToVisit.get(0);

        Point lastVisited = problem.allPoints.get(genotype.get(genotype.size() - 1));
        Point nextToVisit = problem.allPoints.get(pointsToVisit.get(0));

        double bestDist = problem.countDistanceBetweenPoints(lastVisited, nextToVisit);

        for (int j = 0; j < pointsToVisit.size(); j++){
            nextToVisit = problem.allPoints.get(pointsToVisit.get(j));
            if (bestDist > problem.countDistanceBetweenPoints(lastVisited, nextToVisit)){
                closest = pointsToVisit.get(j);
                bestDist = problem.countDistanceBetweenPoints(lastVisited, nextToVisit);
            }

        }
        return closest;
    }


    private void markPointAsVisited(ArrayList<Integer> pointsToVisit ,int pointIndex){
        pointsToVisit.remove(Integer.valueOf(pointIndex));
    }

    private ArrayList<Integer> initializePointsToVisit(){
        ArrayList<Integer> pointsToVisit = new ArrayList<Integer>();
        fillRange(pointsToVisit, problem.allPoints.size());
        return  pointsToVisit;
    }

    private void fillFirstGene(ArrayList<Integer> genotype, ArrayList<Integer> pointsToVisit, ArrayList<Integer> startingPoints){
        fillGene(genotype, pointsToVisit, startPointIndex);
        markPointAsVisited(startingPoints, startPointIndex);
}

    private void fillGene(ArrayList<Integer> genotype, ArrayList<Integer> pointsToVisit, int indexPoint){
        setGene(genotype, indexPoint);
        markPointAsVisited(pointsToVisit, indexPoint);
    }

    private void fillGenotype(ArrayList<Integer> genotype, ArrayList<Integer> pointsToVisit){
        while (pointsToVisit.size() > 0){
            int closestPoint = findClosestPoint(genotype, pointsToVisit);
            fillGene(genotype, pointsToVisit, closestPoint);
        }
    }
}
