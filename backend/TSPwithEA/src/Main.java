import algorithms.EvolutionAlgorithm;
import algorithms.GreedyAlgorithm;
import algorithms.RandomAlgorithm;
import problemInfo.ProblemData;
import problems.TSProblem;
import problems.TSProblemGeo;
import problems.TSProblemPlanar;
import dataParser.*;

public class Main {

    public static void main(String[] args) {
        Parser parser = new Parser();
        ProblemData problemData = parser.parse("berlin11_modified.tsp");
        TSProblem problem = null;

        switch (problemData.problemType){
            case PLANAR:
                problem = new TSProblemPlanar(problemData.points);
                break;
            case GEO:
                problem = new TSProblemGeo(problemData.points);
                break;
        }
        EvolutionAlgorithm ea = new EvolutionAlgorithm(problem);
        GreedyAlgorithm ga = new GreedyAlgorithm(problem, 10);
        ga.runAlgorithm();

        RandomAlgorithm ra = new RandomAlgorithm(problem);
        ra.runAlgorithm();

        ga.getResult().printGenotype();
        System.out.println();
        ra.getResult().printGenotype();
    }
}