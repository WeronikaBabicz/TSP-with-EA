package algorithms;

import algorithms.crossover.Crossover;
import algorithms.mutation.Mutation;
import algorithms.selection.Selection;
import problems.TSProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EvolutionAlgorithm  implements Algorithm{
    private ArrayList<Individual> population = new ArrayList<Individual>();
    private ArrayList<ArrayList<Individual>> allGenerations = new ArrayList<ArrayList<Individual>>();
    private TSProblem problem;
    private Individual bestIndividual;
    private double mutationProb = 0.1;
    private double crossoverProb = 0.7;
    private int numberOfIndividualsForSelection = 5;
    private Crossover crossoverMethod;
    private Mutation mutationMethod;
    private Selection selectionMethod;

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

    public EvolutionAlgorithm(TSProblem problem, Crossover crossoverMethod, Mutation mutationMethod, Selection selectionMethod) {
        this.problem = problem;
        this.crossoverMethod = crossoverMethod;
        this.mutationMethod = mutationMethod;
        this.selectionMethod = selectionMethod;
    }

    public EvolutionAlgorithm(TSProblem problem, double mutationProb, double crossoverProb, int numberOfIndividualsForSelection, Crossover crossoverMethod, Mutation mutationMethod, Selection selectionMethod, int populationSize, int generations) {
        this.problem = problem;
        this.mutationProb = mutationProb;
        this.crossoverProb = crossoverProb;
        this.numberOfIndividualsForSelection = numberOfIndividualsForSelection;
        this.crossoverMethod = crossoverMethod;
        this.mutationMethod = mutationMethod;
        this.selectionMethod = selectionMethod;
        this.populationSize = populationSize;
        this.generations = generations;
    }

    public void setProblem(TSProblem problem) {
        this.problem = problem;
    }

    public void setCrossoverMethod(Crossover crossoverMethod) {
        this.crossoverMethod = crossoverMethod;
    }

    public void setMutationMethod(Mutation mutationMethod) {
        this.mutationMethod = mutationMethod;
    }

    public void setSelectionMethod(Selection selectionMethod) {
        this.selectionMethod = selectionMethod;
    }

    @Override
    public Individual findBestIndividual() {
        return Collections.min(population, Comparator.comparingDouble(Individual::countFitness));
    }

    @Override
    public void initializePopulation() {
        while (population.size() < populationSize){
            Individual individual = new Individual(problem);
            ArrayList<Integer> genotype = new ArrayList<Integer>();

            fillRange(genotype, problem.allPoints.size());
            Collections.shuffle(genotype);

            individual.setGenotype(genotype);
            population.add(individual);
        }
    }

    @Override
    public void runAlgorithm() {
        initializePopulation();

        for (int i = 0; i < generations; i++){
            ArrayList<Individual> newPopulation = new ArrayList<Individual>();
            ArrayList<Individual> selectedIndividualsToReproduce = selection();

            reproduce(selectedIndividualsToReproduce, newPopulation);
            fillPopulation(newPopulation);
            mutatePopulation(newPopulation);

            allGenerations.add((ArrayList<Individual>) population.clone());
            population = newPopulation;  // TODO: ????

            setBestIndividual(findBestIndividual());
        }
    }

    @Override
    public Individual getResult() {
        return bestIndividual;
    }


    private void mutatePopulation(ArrayList<Individual> newPopulation){
        for (int i = 0; i < newPopulation.size(); i++)
            mutate(newPopulation.get(i));
    }

    private void fillPopulation(ArrayList<Individual> newPopulation){
        while (newPopulation.size() < populationSize){ // TODO: (optimization) if older generations are not needed it doesn't have to be a deep copy
            newPopulation.add(new Individual(population.get((int)(Math.random() * populationSize))));
        }
    }


    private void reproduce(ArrayList<Individual> selectedIndividualsToReproduce, ArrayList<Individual> newPopulation){
        for (int j = 0; j < selectedIndividualsToReproduce.size(); j++){ // TODO: try better?
            for(int k = 0; k < selectedIndividualsToReproduce.size() && k != j; k++){
                newPopulation.add(crossover(selectedIndividualsToReproduce.get(j), selectedIndividualsToReproduce.get(k)));
            }
        }
    }



    private Individual crossover(Individual i1, Individual i2){
        return (isBeingDone(crossoverProb)) ? crossoverMethod.cross(i1, i2) : new Individual(i1);
    }

    private void mutate(Individual individual){
        if (isBeingDone(mutationProb))
            mutationMethod.mutate(individual);
    }

    private ArrayList<Individual> selection(){
        return selectionMethod.select(numberOfIndividualsForSelection, population);
    }

    private boolean isBeingDone(double prob){
        return Math.random() < prob;
    }



    private void setBestIndividual(Individual bestIndividual){
        this.bestIndividual = bestIndividual;
    }

}
