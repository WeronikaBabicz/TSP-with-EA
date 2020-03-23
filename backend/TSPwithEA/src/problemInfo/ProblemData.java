package problemInfo;

import java.util.ArrayList;

public class ProblemData {
    public ArrayList<City> cities = new ArrayList<City>();
    public ProblemType problemType;

    public ProblemData(ArrayList<City> cities, ProblemType problemType) {
        this.cities = cities;
        this.problemType = problemType;
    }
}

