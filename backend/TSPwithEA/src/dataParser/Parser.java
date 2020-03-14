package dataParser;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Parser {

    private ProblemType problemType = ProblemType.PLANAR; // Should it stay as a default?
    ArrayList<Point> points = new ArrayList<>();

    public ProblemData parse(String fileName) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new File(getClass().getClassLoader().getResource("TSPdata/"+fileName).getFile())));

            String line;
            while (!(line = reader.readLine()).equalsIgnoreCase("NODE_COORD_SECTION")){
                if (line.equalsIgnoreCase("EDGE_WEIGHT_TYPE: GEO"))
                    problemType = ProblemType.GEO;

                else if (line.equalsIgnoreCase("EDGE_WEIGHT_TYPE: EUC_2D"))
                    problemType = ProblemType.PLANAR;
            }


            while (!(line = reader.readLine()).equalsIgnoreCase("EOF")){
                loadPoint(line);
            }



            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return new ProblemData(points, problemType);
    }


    private void loadPoint(String line){
        StringTokenizer tokenizer = new StringTokenizer(line, "    ");
        tokenizer.nextToken();
        points.add(new Point(Double.parseDouble(tokenizer.nextToken()), Double.parseDouble(tokenizer.nextToken())));
    }
}

