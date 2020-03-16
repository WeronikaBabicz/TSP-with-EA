package algorithms;

import problemInfo.Point;
import problems.TSProblem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class GreedyAlgorithm implements Algorithm {
    private Individual bestIndividual;
    private TSProblem problem;
    private ArrayList<Individual> population = new ArrayList<Individual>();

    int populationSize = 1;
    int generations = 1;
    private int startPointIndex = 0;

    public GreedyAlgorithm(TSProblem problem) {
        this.problem = problem;
    }

    public GreedyAlgorithm(TSProblem problem, int populationSize, int generations, int startPointIndex) {
        this.problem = problem;
        this.populationSize = populationSize;
        this.generations = generations;
        this.startPointIndex = startPointIndex;

    }

    public GreedyAlgorithm(TSProblem problem, int startPointIndex) {
        this.problem = problem;
        this.startPointIndex = startPointIndex;
    }

    public void setProblem(TSProblem problem) {
        this.problem = problem;
    }

    public void setStartPointIndex(int startPointIndex) {
        this.startPointIndex = startPointIndex;
    }




    @Override
    public Individual findBestIndividual() {
        return Collections.max(population, Comparator.comparingDouble(Individual::countFitness));
    }

    @Override
    public void initializePopulation() {
        for (int i = 0; i < populationSize; i++){
            Individual individual = new Individual(problem);
            ArrayList<Integer> genotype = new ArrayList<Integer>();

            ArrayList<Integer> pointsToVisit = initializePointsToVisit();

            fillFirstGene(genotype, pointsToVisit);
            fillGenotype(genotype, pointsToVisit);

            individual.setGenotype(genotype);
            population.add(individual);
        }
    }

    @Override
    public void runAlgorithm() {
        for (int i = 0; i < generations; i++){
            initializePopulation();
            setBestIndividual(findBestIndividual());
        }
    }



    private void setGene(ArrayList<Integer> genotype, int indexPoint){
        genotype.add(indexPoint);
    }

    private void setBestIndividual(Individual bestIndividual){
        this.bestIndividual = bestIndividual;
    }

    private int findClosestPoint(ArrayList<Integer> genotype, ArrayList<Integer> pointsToVisit){
        int closest = pointsToVisit.get(0);
        Point lastVisited = problem.allPoints.get(genotype.get(genotype.size()));
        Point nextToVisit = problem.allPoints.get(pointsToVisit.get(0));

        double bestDist = problem.countDistanceBetweenPoints(lastVisited, nextToVisit);

        for (int j = 0; j < pointsToVisit.size(); j++){
            nextToVisit = problem.allPoints.get(pointsToVisit.get(j));
            if (bestDist < problem.countDistanceBetweenPoints(lastVisited, nextToVisit)){
                closest = pointsToVisit.get(j);
                bestDist = problem.countDistanceBetweenPoints(lastVisited, nextToVisit);
            }

        }
        return closest;
    }

    private void fillRange(ArrayList<Integer> arrayList, int max){
        for (int i = 0; i < max; i ++)
            arrayList.add(i);
    }

    private void markPointAsVisited(ArrayList<Integer> pointsToVisit ,int pointIndex){
        pointsToVisit.remove(Integer.valueOf(pointIndex));
    }

    private ArrayList<Integer> initializePointsToVisit(){
        ArrayList<Integer> pointsToVisit = new ArrayList<Integer>();
        fillRange(pointsToVisit, problem.allPoints.size());

        return  pointsToVisit;
    }

    private void fillFirstGene(ArrayList<Integer> genotype, ArrayList<Integer> pointsToVisit){
        fillGene(genotype, pointsToVisit, startPointIndex);
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
