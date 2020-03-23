package algorithms;

import problems.TSProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RandomAlgorithm implements Algorithm {
    private Individual bestIndividual;
    private TSProblem problem;
    private ArrayList<Individual> population = new ArrayList<Individual>();

    int populationSize;

    public RandomAlgorithm(TSProblem problem, int populationSize) {
        this.problem = problem;
        this.populationSize = populationSize;
    }



    @Override
    public Individual findBestIndividual() {
        return Collections.min(population, Comparator.comparingDouble(Individual::countFitness));
    }

    @Override
    public void initializePopulation() {
        for (int i = 0; i < populationSize; i++){
            ArrayList<Integer> genotype = new ArrayList<Integer>();

            fillRange(genotype, problem.allCities.size());
            Collections.shuffle(genotype); // randomize elements

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
}
