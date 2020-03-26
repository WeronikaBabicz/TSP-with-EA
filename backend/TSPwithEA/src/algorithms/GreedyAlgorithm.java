package algorithms;

import problemInfo.City;
import problems.TSProblem;

import java.util.ArrayList;

public class GreedyAlgorithm extends Algorithm {
    private int startCityIndex;

    public GreedyAlgorithm(TSProblem problem, int populationSize) {
        this.problem = problem;
        this.populationSize = populationSize;
    }


    @Override
    public void initializePopulation() {
        ArrayList<Integer> startingCities = initializeCitiesToVisit();
        int min = Math.min(populationSize, problem.allCities.size());
        for (int i = 0; i < min; i++){

            ArrayList<Integer> genotype = new ArrayList<Integer>();
            ArrayList<Integer> citiesToVisit = initializeCitiesToVisit();

            startCityIndex = startingCities.get((int) (Math.random() * startingCities.size()));

            addFirstGene(genotype, citiesToVisit, startingCities);
            fillGenotype(genotype, citiesToVisit);

            Individual individual = new Individual(problem, genotype);
            population.add(individual);
        }
    }

    @Override
    public void runAlgorithm() {
        initializePopulation();
        setBestIndividual(findBestIndividual());
    }

    private int findClosestCity(ArrayList<Integer> genotype, ArrayList<Integer> citiesToVisit){
        int closest = citiesToVisit.get(0);

        City lastVisited = problem.allCities.get(genotype.get(genotype.size() - 1));
        City nextToVisit = problem.allCities.get(citiesToVisit.get(0));

        double bestDist = problem.countDistanceBetweenCities(lastVisited, nextToVisit);

        for (int j = 0; j < citiesToVisit.size(); j++){
            nextToVisit = problem.allCities.get(citiesToVisit.get(j));
            if (bestDist > problem.countDistanceBetweenCities(lastVisited, nextToVisit)){
                closest = citiesToVisit.get(j);
                bestDist = problem.countDistanceBetweenCities(lastVisited, nextToVisit);
            }
        }
        return closest;
    }

    private ArrayList<Integer> initializeCitiesToVisit(){
        ArrayList<Integer> citiesToVisit = new ArrayList<Integer>();
        fillRange(citiesToVisit, problem.allCities.size());
        return  citiesToVisit;
    }

    private void fillGenotype(ArrayList<Integer> genotype, ArrayList<Integer> citiesToVisit){
        while (citiesToVisit.size() > 0){
            int closestPoint = findClosestCity(genotype, citiesToVisit);
            addGeneAndMarkAsVisited(genotype, citiesToVisit, closestPoint);
        }
    }

    private void addGeneAndMarkAsVisited(ArrayList<Integer> genotype, ArrayList<Integer> citiesToVisit, int cityIndex){
        addGene(genotype, cityIndex);
        markCityAsVisited(citiesToVisit, cityIndex);
    }

    private void markCityAsVisited(ArrayList<Integer> citiesToVisit , int cityIndex){
        citiesToVisit.remove(Integer.valueOf(cityIndex));
    }

    private void addFirstGene(ArrayList<Integer> genotype, ArrayList<Integer> citiesToVisit, ArrayList<Integer> startingCities){
        addGeneAndMarkAsVisited(genotype, citiesToVisit, startCityIndex);
        markCityAsVisited(startingCities, startCityIndex);
    }

    private void addGene(ArrayList<Integer> genotype, int cityIndex){
        genotype.add(cityIndex);
    }
}