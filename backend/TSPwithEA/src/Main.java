import algorithms.EvolutionAlgorithm;
import algorithms.TSPProblem;
import algorithms.TSPProblemGeo;
import algorithms.TSPProblemPlanar;
import dataParser.*;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        ProblemData x = parser.parse("pr2392.tsp");
        TSPProblem problem = null;

        switch (x.problemType){
            case PLANAR:
                problem = new TSPProblemPlanar(x.points);
                break;
            case GEO:
                problem = new TSPProblemGeo(x.points);
                break;
        }

        EvolutionAlgorithm ea = new EvolutionAlgorithm(problem);
    }
}