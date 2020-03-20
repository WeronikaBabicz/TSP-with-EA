package algorithms.mutation;

import algorithms.Individual;

import java.util.ArrayList;

public class SwapMutation implements Mutation {
    @Override
    public void mutate(Individual individual) {
        ArrayList<Integer> genotype = individual.getGenotype();
        int idx1 = generateRandomIndex(genotype);
        int idx2 = generateRandomIndex(genotype);

        swapGenes(genotype, idx1, idx2);

        individual.setGenotype(genotype); //TODO: necessary? since ArrayList<Integer> genotype = individual.getGenotype();
    }
}


