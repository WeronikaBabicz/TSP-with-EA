package algorithms;

import problemInfo.Point;

public interface Algorithm {
    Individual findBestIndividual();
    void initializePopulation();
    void runAlgorithm();
}
