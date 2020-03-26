package algorithms;

import problems.TSProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Algorithm {
    ArrayList<Individual> population = new ArrayList<>();
    private ArrayList<ArrayList<String>> generationScores = new ArrayList<ArrayList<String>>();
    Individual bestIndividual;
    int generation;
    TSProblem problem;
    int populationSize;

    public ArrayList<ArrayList<String>> getGenerationScores() {
        return generationScores;
    }

    abstract void initializePopulation();
    public abstract void runAlgorithm();



    Individual findBestIndividual(){
        return Collections.min(population, Comparator.comparingDouble(Individual::countFitness));
    }

    private Individual findWorstIndividual(){
        return Collections.max(population, Comparator.comparingDouble(Individual::countFitness));
    }

    private double getBestFitness(){
        return findBestIndividual().countFitness();
    }

    private double getWorstFitness(){
        return findWorstIndividual().countFitness();
    }

    private double getAverageFitness(){
        List<Double> fitness = population.stream().map(Individual::countFitness).collect(Collectors.toList());
        return fitness.stream().mapToDouble(Double::doubleValue).sum()/populationSize;
    }

    private double getStandardDeviation(){
        double average = getAverageFitness();
        List<Double> fitness = population.stream().map(Individual::countFitness).collect(Collectors.toList());
        for (int i = 0; i < fitness.size(); i++)
            fitness.set(i, Math.pow(fitness.get(i) - average, 2)/populationSize);

        return Math.sqrt(fitness.stream().mapToDouble(Double::doubleValue).sum());
    }

    public Individual getResult() {
        return bestIndividual;
    }

    ArrayList<Individual> getPopulation() {
        return population;
    }

    void fillRange(ArrayList<Integer> arrayList, int max){
        for (int i = 0; i < max; i ++)
            arrayList.add(i);
    }

    void setBestIndividual(Individual bestIndividual){
        this.bestIndividual = bestIndividual;
    }

    void fillPopulationWithRandoms(int toFill){
        for (int i = 0; i < toFill; i++){
            ArrayList<Integer> genotype = new ArrayList<Integer>();

            fillRange(genotype, problem.allCities.size());
            Collections.shuffle(genotype);

            Individual individual = new Individual(problem, genotype);
            population.add(individual);
        }
    }

    void addGenerationInfoToGenerationScores(){
        ArrayList<String> scores = new ArrayList<>();
        scores.add(Double.toString(generation));
        scores.add(Double.toString(getBestFitness()));
        scores.add(Double.toString(getAverageFitness()));
        scores.add(Double.toString(getWorstFitness()));
        scores.add(Double.toString(getStandardDeviation()));
        generationScores.add(scores);
    }
}
