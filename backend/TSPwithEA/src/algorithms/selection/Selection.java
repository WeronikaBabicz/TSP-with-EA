package algorithms.selection;

import algorithms.Individual;

import java.util.ArrayList;

public interface Selection {
    ArrayList<Individual> select(int numberOfIndividualsForSelection, ArrayList<Individual> population);
    Individual selectBestIndividual(int among, ArrayList<Individual> population);
}
