package algorithms.selection;

import algorithms.Individual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TournamentSelection implements Selection {
    @Override
    public ArrayList<Individual> select(int numberOfIndividualsForSelection, ArrayList<Individual> population) {
        ArrayList<Individual> selectedIndividuals = new ArrayList<Individual>();
        for (int i = 0; i < numberOfIndividualsForSelection; i++)
            selectedIndividuals.add(selectIndividual(numberOfIndividualsForSelection, population));
        return selectedIndividuals;
    }

    @Override
    public Individual selectIndividual(int among, ArrayList<Individual> population){
        ArrayList<Individual> randomIndividuals = new ArrayList<Individual>();
        for (int i = 0; i < among; i++)
            randomIndividuals.add(population.get((int)(Math.random() * population.size())));
        return Collections.min(randomIndividuals, Comparator.comparingDouble(Individual::countFitness));
    }
}
