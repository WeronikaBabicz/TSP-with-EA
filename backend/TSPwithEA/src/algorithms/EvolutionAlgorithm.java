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

    int populationSize = 1200;
    int generations = 1000;

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
        setBestIndividual(findBestIndividual());

        for (int i = 0; i < generations; i++){
            ArrayList<Individual> newPopulation = new ArrayList<Individual>();
            ArrayList<Individual> selectedIndividualsToReproduce = selection();

            reproduce(selectedIndividualsToReproduce, newPopulation);
            fillPopulation(newPopulation);
            mutatePopulation(newPopulation);

            allGenerations.add((ArrayList<Individual>) population.clone());
            population = newPopulation;  // TODO: ????

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


    private void mutatePopulation(ArrayList<Individual> newPopulation){
        for (int i = 0; i < newPopulation.size(); i++)
            mutate(newPopulation.get(i));
    }

    private void fillPopulation(ArrayList<Individual> newPopulation){
        ArrayList<Individual> best = selectionBest(populationSize - newPopulation.size());
        for (Individual individual : best) {
            newPopulation.add(new Individual(individual));
        }
    }


    private void reproduce(ArrayList<Individual> selectedIndividualsToReproduce, ArrayList<Individual> newPopulation){
        for (int i = 0; i < selectedIndividualsToReproduce.size(); i++){ // TODO: try better?
            for(int j = 0; j < selectedIndividualsToReproduce.size(); j++){
                if (i != j)
                    newPopulation.add(crossover(selectedIndividualsToReproduce.get(i), selectedIndividualsToReproduce.get(j)));
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

    private ArrayList<Individual> selectionBest(int n){
        Selection bestSelection = new BestSelection();
        return bestSelection.select(n, population);
    }


    private void setBestIndividual(Individual bestIndividual){
        this.bestIndividual = bestIndividual;
    }

}
