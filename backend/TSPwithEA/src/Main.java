import algorithms.Algorithm;
import algorithms.EvolutionAlgorithm;
import algorithms.GreedyAlgorithm;
import algorithms.RandomAlgorithm;
import algorithms.crossover.Crossover;
import algorithms.crossover.OrderedCrossover;
import algorithms.mutation.InverseMutation;
import algorithms.mutation.Mutation;
import algorithms.selection.RouletteSelection;
import algorithms.selection.Selection;
import problemInfo.ProblemData;
import problems.TSProblem;
import problems.TSProblemGeo;
import problems.TSProblemPlanar;
import dataParser.*;

public class Main {

    public static final int POPULATION_TO_INITIALIZE = 20;

    public static void main(String[] args) {
        Parser parser = new Parser();
        ProblemData problemData = parser.parse("fl417.tsp");
        TSProblem problem = null;

        switch (problemData.problemType){
            case PLANAR:
                problem = new TSProblemPlanar(problemData.cities);
                break;
            case GEO:
                problem = new TSProblemGeo(problemData.cities);
                break;
        }

        Crossover crossover = new OrderedCrossover();
        Mutation mutation = new InverseMutation();
        Selection selection = new RouletteSelection();
        Algorithm algorithmToInitializePopulation = new GreedyAlgorithm(problem, POPULATION_TO_INITIALIZE);

        EvolutionAlgorithm ea = new  EvolutionAlgorithm(problem,
                                                        crossover,
                                                        mutation,
                                                        selection,
                                                        algorithmToInitializePopulation);
        ea.runAlgorithm();

        GreedyAlgorithm ga = new GreedyAlgorithm(problem, problem.allCities.size());
        ga.runAlgorithm();

        RandomAlgorithm ra = new RandomAlgorithm(problem, POPULATION_TO_INITIALIZE);
        ra.runAlgorithm();


        System.out.println();
        System.out.println("EVOLUTION:");
        System.out.println(ea.getResult().countFitness());
        System.out.println(ea.getResult());

        System.out.println("GREEDY:");
        System.out.println(ga.getResult().countFitness());
        System.out.println(ga.getResult());

        System.out.println("RANDOM:");
        System.out.println(ra.getResult().countFitness());
        System.out.println(ra.getResult());

    }
}