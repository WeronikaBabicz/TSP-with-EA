package algorithms.crossover;

import algorithms.Individual;

public interface Crossover {
    Individual cross(Individual parent1, Individual parent2);
}
