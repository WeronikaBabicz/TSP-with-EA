import algorithms.EvolutionAlgorithm;
import algorithms.GreedyAlgorithm;
import algorithms.RandomAlgorithm;
import algorithms.crossover.Crossover;
import algorithms.crossover.OrderedCrossover;
import algorithms.mutation.InverseMutation;
import algorithms.mutation.Mutation;
import algorithms.mutation.SwapMutation;
import algorithms.selection.BestSelection;
import algorithms.selection.RouletteSelection;
import algorithms.selection.Selection;
import algorithms.selection.TournamentSelection;
import problemInfo.ProblemData;
import problems.TSProblem;
import problems.TSProblemGeo;
import problems.TSProblemPlanar;
import dataParser.*;

public class Main {

    public static final double points = 10.0;

    public static void main(String[] args) {
        Parser parser = new Parser();
        ProblemData problemData = parser.parse("kroA100.tsp");
        TSProblem problem = null;

        switch (problemData.problemType){
            case PLANAR:
                problem = new TSProblemPlanar(problemData.points);
                break;
            case GEO:
                problem = new TSProblemGeo(problemData.points);
                break;
        }
        Crossover crossover = new OrderedCrossover();
        Mutation mutation = new InverseMutation();
        Selection selection = new RouletteSelection();

        EvolutionAlgorithm ea = new EvolutionAlgorithm(problem, crossover, mutation, selection);
        ea.runAlgorithm();

        GreedyAlgorithm ga = new GreedyAlgorithm(problem, 10);
        ga.runAlgorithm();

        RandomAlgorithm ra = new RandomAlgorithm(problem);
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