package algorithms;

import problems.TSProblem;

import java.util.ArrayList;

public class GreedyAlgorithm implements Algorithm {
    private Individual bestIndividual;
    private TSProblem problem;
    private ArrayList<Individual> population = new ArrayList<Individual>();

    int populationSize = 1;
    int generations = 1;

    public GreedyAlgorithm(TSProblem problem) {
        this.problem = problem;
    }

    public GreedyAlgorithm(TSProblem problem, int populationSize, int generations) {
        this.problem = problem;
        this.populationSize = populationSize;
        this.generations = generations;
    }

    public void setProblem(TSProblem problem) {
        this.problem = problem;
    }




    @Override
    public Individual findBestIndividual() {
        return null;
    }

    @Override
    public void initializePopulation() {

    }

    @Override
    public void runAlgorithm() {

    }
}
