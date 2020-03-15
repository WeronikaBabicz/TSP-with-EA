package problemInfo;

import java.util.ArrayList;

public class ProblemData {
    public ArrayList<Point> points = new ArrayList<Point>();
    public ProblemType problemType;

    public ProblemData(ArrayList<Point> points, ProblemType problemType) {
        this.points = points;
        this.problemType = problemType;
    }
}

