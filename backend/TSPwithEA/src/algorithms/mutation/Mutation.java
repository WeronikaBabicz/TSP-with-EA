package algorithms.mutation;

import algorithms.Individual;

import java.util.ArrayList;

public interface Mutation {
    void mutate(Individual individual);
    default void swapGenes(ArrayList<Integer> genotype, int idx1, int idx2){
        int tempGene = genotype.get(idx1);
        genotype.set(idx1, genotype.get(idx2));
        genotype.set(idx2, tempGene);
    }

    default int generateRandomIndex(ArrayList<Integer> genotype){
        return (int) (Math.random() * genotype.size());
    }
}
