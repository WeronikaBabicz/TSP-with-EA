package algorithms;

import problems.TSProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Algorithm {
    ArrayList<Individual> population = new ArrayList<>();
    Individual bestIndividual;
    TSProblem problem;
    int populationSize;

    abstract void initializePopulation();
    public abstract void runAlgorithm();


    Individual findBestIndividual(){
        return Collections.min(population, Comparator.comparingDouble(Individual::countFitness));
    }

    Individual findWorstIndividual(){
        return Collections.max(population, Comparator.comparingDouble(Individual::countFitness));
    }

    public double getBestFitness(){
        return findBestIndividual().countFitness();
    }

    public double getWorstFitness(){
        return findWorstIndividual().countFitness();
    }

    public double getAverageFitness(){
        List<Double> fitness = population.stream().map(Individual::countFitness).collect(Collectors.toList());
        return fitness.stream().mapToDouble(Double::doubleValue).sum()/population.size();
    }

    public Individual getResult() {
        return bestIndividual;
    }

    public ArrayList<Individual> getPopulation() {
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
}
