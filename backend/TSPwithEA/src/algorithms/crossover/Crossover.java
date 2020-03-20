package algorithms.crossover;

import algorithms.Individual;

import java.util.ArrayList;

public interface Crossover {
    Individual cross(Individual parent1, Individual parent2);
    default int generateRandomIndex(ArrayList<Integer> genotype){
        return (int) (Math.random() * genotype.size());
    }
}
