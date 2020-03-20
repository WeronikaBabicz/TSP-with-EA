package algorithms.mutation;

import algorithms.Individual;

import java.util.ArrayList;

public class InverseMutation implements Mutation {
    @Override
    public void mutate(Individual individual) {
        ArrayList<Integer> genotype = individual.getGenotype();
        int idx1 = generateRandomIndex(genotype);
        int idx2 = generateRandomIndex(genotype);

        inverseGenes(Math.min(idx1, idx2), Math.max(idx1, idx2), genotype);

        individual.setGenotype(genotype);
    }

    private void inverseGenes(int startIndex, int endIndex, ArrayList<Integer> genotype){
        while (startIndex < endIndex){
            swapGenes(genotype, startIndex, endIndex);
            startIndex++;
            endIndex--;
        }
    }



}
