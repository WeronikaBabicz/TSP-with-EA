package algorithms;

import algorithms.crossover.Crossover;
import algorithms.mutation.Mutation;
import algorithms.selection.BestSelection;
import algorithms.selection.Selection;
import problems.TSProblem;

import java.util.ArrayList;

public class EvolutionAlgorithm  extends Algorithm{
    private static final double PERCENT_OF_BEST = 0.001;
    private static final double MUTATION_PROB = 0.2;
    private static final double CROSSOVER_PROB = 0.9;
    private static final int INDIVIDUALS_TO_REPRODUCE = 400;
    private static final int GENERATIONS = 1000;
    public static final double INTERVAL_LENGTH = 100;
    public static final double STARTING_POINT = -5.0;
    private static final int POPULATION_SIZE = 1500;

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
        this.populationSize = POPULATION_SIZE;
    }


    @Override
    public void initializePopulation() {
        algorithmToInitializePopulation.initializePopulation();
        population = algorithmToInitializePopulation.getPopulation();
        fillPopulationWithRandoms(POPULATION_SIZE - population.size());
    }

    @Override
    public void runAlgorithm() {
        initializePopulation();
        setBestIndividual(findBestIndividual());

        for (int i = 0; i < GENERATIONS; i++){
            generation = i;
            ArrayList<Individual> newPopulation = new ArrayList<Individual>();
            ArrayList<Individual> selectedIndividualsToReproduce;

            selectedIndividualsToReproduce = selection();
            reproduce(selectedIndividualsToReproduce, newPopulation);
            fillPopulation(newPopulation);
            mutatePopulation(newPopulation);

            population = newPopulation;

            addGenerationInfoToGenerationScores();
            updateBestIndividual(findBestIndividual());
        }
    }

    private void updateBestIndividual(Individual currentBest){
        if (currentBest.countFitness() < bestIndividual.countFitness()){
            setBestIndividual(currentBest);
            System.out.println("found best! In generation: "+ generation);
            System.out.println(currentBest.countFitness());
        }
    }

    private void mutatePopulation(ArrayList<Individual> newPopulation){
        for (Individual individual : newPopulation)
            mutate(individual);
    }

    private void fillPopulation(ArrayList<Individual> newPopulation){
        ArrayList<Individual> best = selectionBest((int) ((POPULATION_SIZE - newPopulation.size()) * PERCENT_OF_BEST));
        newPopulation.addAll(best);
        while (newPopulation.size() < POPULATION_SIZE){
            int rand = (int)(Math.random() * POPULATION_SIZE);
            //if (!newPopulation.contains(population.get(rand))) //TODO: check
                newPopulation.add(new Individual(population.get(rand)));
        }
    }


    private void reproduce(ArrayList<Individual> selectedIndividualsToReproduce, ArrayList<Individual> newPopulation){
        for (Individual ind : selectedIndividualsToReproduce){
            int other = (int) (Math.random() * selectedIndividualsToReproduce.size());
            if (other != selectedIndividualsToReproduce.indexOf(ind))
                newPopulation.add(crossover(ind, selectedIndividualsToReproduce.get(other)));
        }
    }


    private ArrayList<Individual> selection(){
        return selectionMethod.select(INDIVIDUALS_TO_REPRODUCE, population);
    }

    private Individual crossover(Individual i1, Individual i2){
        return (isBeingDone(CROSSOVER_PROB)) ? crossoverMethod.cross(i1, i2) : new Individual(i1);
    }

    private void mutate(Individual individual){
        if (isBeingDone(MUTATION_PROB))
            mutationMethod.mutate(individual);
    }

    private boolean isBeingDone(double prob){
        return Math.random() < prob;
    }

    private ArrayList<Individual> selectionBest(int n){
        Selection bestSelection = new BestSelection();
        return bestSelection.select(n, population);
    }
}
