package algorithms;

import java.util.ArrayList;

public class Individual {
    ArrayList<Integer> genotype = new ArrayList<>();
    TSPProblem problem;


    public double countFitness(){
        return problem.countFitness(genotype);
    }
}
