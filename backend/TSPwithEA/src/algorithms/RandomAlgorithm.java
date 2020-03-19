package algorithms;

import problems.TSProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RandomAlgorithm implements Algorithm {
    private Individual bestIndividual;
    private TSProblem problem;
    private ArrayList<Individual> population = new ArrayList<Individual>();

    int populationSize = 1;
    int generations = 1;

    public RandomAlgorithm(TSProblem problem) {
        this.problem = problem;
    }

    public RandomAlgorithm(TSProblem problem, int populationSize, int generations) {
        this.problem = problem;
        this.populationSize = populationSize;
        this.generations = generations;
    }

    public void setProblem(TSProblem problem) {
        this.problem = problem;
    }

    public Individual getBestIndividual(){
        return bestIndividual;
    }




    @Override
    public Individual findBestIndividual() {
        return Collections.min(population, Comparator.comparingDouble(Individual::countFitness));
    }

    @Override
    public void initializePopulation() {
        for (int i = 0; i < populationSize; i++){
            Individual individual = new Individual(problem);
            ArrayList<Integer> genotype = new ArrayList<Integer>();

            fillRange(genotype, problem.allPoints.size());
            Collections.shuffle(genotype); // randomize elements

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

    @Override
    public Individual getResult() {
        System.out.println(bestIndividual.countFitness());
        return bestIndividual;
    }


    private void setBestIndividual(Individual bestIndividual){
        this.bestIndividual = bestIndividual;
    }



}
