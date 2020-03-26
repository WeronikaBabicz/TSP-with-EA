package algorithms.selection;

import algorithms.EvolutionAlgorithm;
import algorithms.Individual;

import java.util.*;


public class RouletteSelection implements Selection {
    private static final double intervalLength = EvolutionAlgorithm.INTERVAL_LENGTH;
    private static final double startingPoint = EvolutionAlgorithm.STARTING_POINT;

    @Override
    public ArrayList<Individual> select(int numberOfIndividualsForSelection, ArrayList<Individual> population) {
        double bestFitness = (Collections.min(population, Comparator.comparingDouble(Individual::countFitness))).countFitness();
        double worstFitness = (Collections.max(population, Comparator.comparingDouble(Individual::countFitness))).countFitness();

        ArrayList<Individual> selectedIndividuals = new ArrayList<Individual>();
        ArrayList<Double> probabilities = new ArrayList<Double>();
        for (Individual individual : population) {
            probabilities.add(probabilityFunction(individual.countFitness(), bestFitness, worstFitness));
        }
        while (selectedIndividuals.size() < numberOfIndividualsForSelection){
            for(int i = 0 ; i < population.size(); i++){
                if (isBeingDone(probabilities.get(i)))
                    selectedIndividuals.add(new Individual(population.get(i)));
            }
        }
        return selectedIndividuals;
    }

    private boolean isBeingDone(double prob){
        return Math.random() < prob;
    }

    private double probabilityFunction(double x, double bestFitness, double worstFitness){
        double arg =  startingPoint + (x - bestFitness)/(worstFitness - bestFitness) * intervalLength;
        return  Math.pow(2, -arg + startingPoint);
    }

}
