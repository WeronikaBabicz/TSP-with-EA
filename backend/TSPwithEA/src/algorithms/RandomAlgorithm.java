package algorithms;

import problems.TSProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RandomAlgorithm extends Algorithm {

    public RandomAlgorithm(TSProblem problem, int populationSize) {
        this.problem = problem;
        this.populationSize = populationSize;
    }


    @Override
    public void initializePopulation() {
        fillPopulationWithRandoms(populationSize);
    }

    @Override
    public void runAlgorithm() {
        initializePopulation();
        setBestIndividual(findBestIndividual());
        addGenerationInfoToGenerationScores();
    }

}
