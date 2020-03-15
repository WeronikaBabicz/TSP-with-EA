package algorithms;

import problems.TSProblem;

import java.util.ArrayList;

public class Individual {
    ArrayList<Integer> genotype = new ArrayList<>();
    TSProblem problem;


    public double countFitness(){
        return problem.countFitness(genotype);
    }
}
