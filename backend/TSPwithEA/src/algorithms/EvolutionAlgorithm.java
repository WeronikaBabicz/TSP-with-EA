package algorithms;

import problems.TSProblem;

import java.util.ArrayList;

public class EvolutionAlgorithm  implements Algorithm{
    private ArrayList<Individual> population = new ArrayList<Individual>();
    private TSProblem problem;
    private Individual bestIndividual;

    int populationSize = 100;
    int generations = 100;

    public EvolutionAlgorithm(TSProblem problem) {
        this.problem = problem;
    }

    public EvolutionAlgorithm(TSProblem problem, int populationSize, int generations) {
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
        while (population.size() < populationSize){

        }
    }

    @Override
    public void runAlgorithm() {
        /*
        initialize population
        for generations
            selection
            crossover
            mutation
            find best

         */
    }

    @Override
    public Individual getResult() {
        return null;
    }


}
