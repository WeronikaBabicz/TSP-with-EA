package algorithms;

import problems.TSProblem;

import java.util.ArrayList;

public class Individual {
    private ArrayList<Integer> genotype = new ArrayList<>();
    private TSProblem problem;

    public Individual(TSProblem problem) {
        this.problem = problem;
    }




    public double countFitness(){
        return problem.countFitness(genotype);
    }

    public void setGenotype(ArrayList<Integer> genotype) {
        this.genotype = genotype;
    }

    public void printGenotype(){
        for (int i = 0; i < genotype.size(); i++){
            System.out.print(genotype.get(i) + " ");
        }
    }
}
