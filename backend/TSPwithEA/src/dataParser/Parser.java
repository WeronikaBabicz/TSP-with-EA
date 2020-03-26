package dataParser;

import problemInfo.City;
import problemInfo.ProblemData;
import problemInfo.ProblemType;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Parser {

    private ProblemType problemType = ProblemType.PLANAR;
    private ArrayList<City> cities = new ArrayList<>();

    public ProblemData parse(String fileName) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new File(getClass().getClassLoader().getResource("TSPdata/" +fileName).getFile())));

            String line;
            while (!(line = reader.readLine()).equalsIgnoreCase("NODE_COORD_SECTION")) {
                if (line.equalsIgnoreCase("EDGE_WEIGHT_TYPE: GEO"))
                    problemType = ProblemType.GEO;

                else if (line.equalsIgnoreCase("EDGE_WEIGHT_TYPE : EUC_2D") || line.equalsIgnoreCase("EDGE_WEIGHT_TYPE: EUC_2D"))
                    problemType = ProblemType.PLANAR;
            }

            while (!(line = reader.readLine()).equalsIgnoreCase("EOF"))
                loadPoint(line);

            reader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new ProblemData(cities, problemType);
    }


    private void loadPoint(String line){
        StringTokenizer tokenizer = new StringTokenizer(line, "    ");
        tokenizer.nextToken();
        cities.add(new City(Double.parseDouble(tokenizer.nextToken()), Double.parseDouble(tokenizer.nextToken())));
    }
}

