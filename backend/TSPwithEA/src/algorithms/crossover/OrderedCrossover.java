package algorithms.crossover;

import algorithms.Individual;

import java.util.ArrayList;
import java.util.List;

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
        List<Integer> notUsedGenes = new ArrayList<>(parent2.getGenotype());

        notUsedGenes.removeIf((genotype.subList(startIndex, endIndex + 1)::contains));

        for (int i = 0; i < genotype.size(); i++){
            if (i < startIndex || i > endIndex){
                genotype.set(i, notUsedGenes.get(0));
                notUsedGenes.remove(0);
            }
        }
    }


}
