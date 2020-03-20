package algorithms.selection;

import algorithms.Individual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BestSelection implements Selection {
    @Override
    public ArrayList<Individual> select(int numberOfIndividualsForSelection, ArrayList<Individual> population) {
        ArrayList<Individual> selectFrom = (ArrayList<Individual>) population.clone();
        ArrayList<Individual> selectedIndividuals = new ArrayList<Individual>();
        for (int i = 0; i < numberOfIndividualsForSelection; i++){
            selectedIndividuals.add(selectIndividual(numberOfIndividualsForSelection, selectFrom));
            selectFrom.removeIf(selectedIndividuals::contains);
        }
        return selectedIndividuals;
    }

    @Override
    public Individual selectIndividual(int among, ArrayList<Individual> population) {
        return Collections.min(population, Comparator.comparingDouble(Individual::countFitness));
    }
}
