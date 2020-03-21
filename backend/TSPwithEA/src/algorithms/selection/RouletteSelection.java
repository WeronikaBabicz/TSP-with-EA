package algorithms.selection;

import algorithms.Individual;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class RouletteSelection implements Selection {
    @Override
    public ArrayList<Individual> select(int numberOfIndividualsForSelection, ArrayList<Individual> population) {
        double bestFitness = (Collections.min(population, Comparator.comparingDouble(Individual::countFitness))).countFitness();
        double worstFitness = (Collections.max(population, Comparator.comparingDouble(Individual::countFitness))).countFitness();

        ArrayList<Individual> selectedIndividuals = new ArrayList<Individual>();
        //List<Double> probabilities = population.stream().map(Individual::countFitness).collect(Collectors.toList());
        ArrayList<Double> probabilities = new ArrayList<Double>();
        for (int i = 0; i < population.size(); i++)
            probabilities.add(1.0 - (population.get(i).countFitness() - bestFitness)/(worstFitness - bestFitness));

        //double probabilitiesSum = probabilities.stream().mapToDouble(Double::doubleValue).sum();

        while (selectedIndividuals.size() < numberOfIndividualsForSelection){
            for(int i = 0 ; i < population.size(); i++){
                if (isBeingDone(probabilities.get(i)))
                    selectedIndividuals.add(population.get(i));
            }
        }
        return selectedIndividuals;
    }

    private boolean isBeingDone(double prob){
        return Math.random() < prob;
    }


}
