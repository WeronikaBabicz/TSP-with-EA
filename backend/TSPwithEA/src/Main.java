import algorithms.EvolutionAlgorithm;
import problemInfo.ProblemData;
import problems.TSProblem;
import problems.TSProblemGeo;
import problems.TSProblemPlanar;
import dataParser.*;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        ProblemData problemData = parser.parse("pr2392.tsp");
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
    }
}