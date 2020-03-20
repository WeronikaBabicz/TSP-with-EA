package algorithms.crossover;

import algorithms.Individual;

import java.util.ArrayList;

public class OrderedCrossover implements Crossover{

    @Override
    public Individual cross(Individual parent1, Individual parent2) {
        Individual offspring = new Individual(parent1);
        ArrayList<Integer> genotype = offspring.getGenotype();
        int idx1 = generateRandomIndex(parent1.getGenotype());
        int idx2 = generateRandomIndex(parent1.getGenotype());

        changeGenes(genotype, Math.min(idx1, idx2), Math.max(idx1, idx2), parent2);

        offspring.setGenotype(genotype);
        return offspring;
    }

    private void changeGenes(ArrayList<Integer> genotype, int startIndex, int endIndex, Individual parent2){
        ArrayList<Integer> notUsedGenes = ((ArrayList<Integer>) parent2.getGenotype().clone());
        notUsedGenes.removeIf(n -> genotype.subList(startIndex, endIndex + 1).contains(n)); // [1 2 3 4] sub(1,3): [2 3]

        for (int i = 0; i < genotype.size() && (i < startIndex || i > endIndex); i++){
            genotype.set(i, notUsedGenes.get(0)); //TODO: works?
            notUsedGenes.remove(0);
        }
    }


}
