package algorithms;

import algorithms.crossover.Crossover;
import algorithms.mutation.Mutation;
import algorithms.selection.BestSelection;
import algorithms.selection.Selection;
import problems.TSProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EvolutionAlgorithm  implements Algorithm{
    private static final double PERCENT_OF_BEST = 0.002;
    private static final double MUTATION_PROB = 0.4;
    private static final double CROSSOVER_PROB = 0.9;
    private static final int INDIVIDUALS_TO_REPRODUCE = 400;
    private static final int GENERATIONS = 1000;
    public static final double INTERVAL_LENGTH = 1000;
    public static final double STARTING_POINT = -5.0;
    public static final int POPULATION_SIZE = 1500;

    private ArrayList<Individual> population = new ArrayList<Individual>();
    private TSProblem problem;
    private Individual bestIndividual;
    private Crossover crossoverMethod;
    private Mutation mutationMethod;
    private Selection selectionMethod;
    private Algorithm algorithmToInitializePopulation;

    public EvolutionAlgorithm(TSProblem problem, Crossover crossoverMethod, Mutation mutationMethod, Selection selectionMethod, Algorithm algorithmToInitializePopulation) {
        this.problem = problem;
        this.crossoverMethod = crossoverMethod;
        this.mutationMethod = mutationMethod;
        this.selectionMethod = selectionMethod;
        this.algorithmToInitializePopulation = algorithmToInitializePopulation;
    }


    @Override
    public Individual findBestIndividual() {
        return Collections.min(population, Comparator.comparingDouble(Individual::countFitness));
    }

    @Override
    public void initializePopulation() {
        algorithmToInitializePopulation.initializePopulation();
        population = algorithmToInitializePopulation.getPopulation();
        int toFill = POPULATION_SIZE - population.size();
        for (int i = 0; i < toFill; i++){
            ArrayList<Integer> genotype = new ArrayList<Integer>();

            fillRange(genotype, problem.allCities.size());
            Collections.shuffle(genotype);

            Individual individual = new Individual(problem, genotype);
            population.add(individual);
        }
    }

    @Override
    public void runAlgorithm() {
        initializePopulation();
        setBestIndividual(findBestIndividual());

        for (int i = 0; i < GENERATIONS; i++){
            ArrayList<Individual> newPopulation = new ArrayList<Individual>();
            ArrayList<Individual> selectedIndividualsToReproduce = selection();

            reproduce(selectedIndividualsToReproduce, newPopulation);
            fillPopulation(newPopulation);
            mutatePopulation(newPopulation);

            population = newPopulation;

            Individual currentBest = findBestIndividual();
            if (currentBest.countFitness() < bestIndividual.countFitness()){
                setBestIndividual(currentBest);
                System.out.println("found best! In generation: "+ i);
                System.out.println(currentBest.countFitness());
            }
        }
    }

    @Override
    public Individual getResult() {
        return bestIndividual;
    }

    @Override
    public ArrayList<Individual> getPopulation() {
        return population;
    }


    private void mutatePopulation(ArrayList<Individual> newPopulation){
        for (int i = 0; i < newPopulation.size(); i++)
            mutate(newPopulation.get(i));
    }

    private void fillPopulation(ArrayList<Individual> newPopulation){
        ArrayList<Individual> best = selectionBest((int) ((POPULATION_SIZE - newPopulation.size()) * PERCENT_OF_BEST));
        newPopulation.addAll(best);
        while (newPopulation.size() < POPULATION_SIZE){
            newPopulation.add(new Individual(population.get((int)(Math.random() * POPULATION_SIZE))));
        }
    }


    private void reproduce(ArrayList<Individual> selectedIndividualsToReproduce, ArrayList<Individual> newPopulation){
        for (Individual ind : selectedIndividualsToReproduce){
            int other = (int) (Math.random() * selectedIndividualsToReproduce.size());
            if (other != selectedIndividualsToReproduce.indexOf(ind))
                newPopulation.add(crossover(ind, selectedIndividualsToReproduce.get(other)));
        }
    }



    private Individual crossover(Individual i1, Individual i2){
        return (isBeingDone(CROSSOVER_PROB)) ? crossoverMethod.cross(i1, i2) : new Individual(i1);
    }

    private void mutate(Individual individual){
        if (isBeingDone(MUTATION_PROB))
            mutationMethod.mutate(individual);
    }

    private ArrayList<Individual> selection(){
        return selectionMethod.select(INDIVIDUALS_TO_REPRODUCE, population);
    }

    private boolean isBeingDone(double prob){
        return Math.random() < prob;
    }

    private ArrayList<Individual> selectionBest(int n){
        Selection bestSelection = new BestSelection();
        return bestSelection.select(n, population);
    }


    private void setBestIndividual(Individual bestIndividual){
        this.bestIndividual = bestIndividual;
    }

}
