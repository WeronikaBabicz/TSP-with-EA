package algorithms;

import problems.TSProblem;

import java.util.ArrayList;

public class Individual implements Cloneable{
    private ArrayList<Integer> genotype = new ArrayList<>();
    private TSProblem problem;

    public Individual(TSProblem problem, ArrayList<Integer> genotype) {
        this.genotype = genotype;
        this.problem = problem;
    }

    public Individual(Individual another) {
        this.problem = another.problem;
        this.genotype = (ArrayList<Integer>) another.genotype.clone(); // Hope it's a deep copy
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
        String ret = "";
        for (int i = 0; i < genotype.size(); i++){
            ret += genotype.get(i) + " ";
        }
        return ret;
    }
}
