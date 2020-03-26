package algorithms;

import problems.TSProblem;

import java.util.ArrayList;

public class Individual implements Cloneable{
    private ArrayList<Integer> genotype;
    private TSProblem problem;

    public Individual(TSProblem problem, ArrayList<Integer> genotype) {
        this.genotype = genotype;
        this.problem = problem;
    }

    public Individual(Individual another) {
        this.problem = another.problem;
        this.genotype = (ArrayList<Integer>) another.genotype.clone();
        //this.genotype = new ArrayList<>(another.genotype);
    }

    public ArrayList<Integer> getGenotype() {
        return genotype;
    }

    public double countFitness(){
        return problem.calculateFitness(genotype);
    }

    public void setGenotype(ArrayList<Integer> genotype) {
        this.genotype = genotype;
    }

    @Override
    public String toString(){
        StringBuilder ret = new StringBuilder();
        for (Integer integer : genotype) {
            ret.append(integer).append(" ");
        }
        return ret.toString();
    }
}
