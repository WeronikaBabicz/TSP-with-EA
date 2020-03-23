package algorithms;

import java.util.ArrayList;

public interface Algorithm {
    Individual findBestIndividual();
    void initializePopulation();
    void runAlgorithm();
    Individual getResult();
    ArrayList<Individual> getPopulation();

    default void fillRange(ArrayList<Integer> arrayList, int max){
        for (int i = 0; i < max; i ++)
            arrayList.add(i);
    }
}
