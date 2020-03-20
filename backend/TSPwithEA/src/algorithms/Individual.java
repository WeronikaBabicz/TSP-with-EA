package algorithms;

import problems.TSProblem;

import java.util.ArrayList;

public class Individual implements Cloneable{
    private ArrayList<Integer> genotype = new ArrayList<>();
    private TSProblem problem;

    public Individual(TSProblem problem) {
        this.problem = problem;
    }

    public Individual(Individual another) {
        this.problem = another.problem;
        this.genotype = new ArrayList<>(another.genotype);
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
