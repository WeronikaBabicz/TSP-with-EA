package algorithms;

public interface Algorithm {
    Individual findBestIndividual();
    void initializePopulation();
    void runAlgorithm();
    Individual getResult();
}
