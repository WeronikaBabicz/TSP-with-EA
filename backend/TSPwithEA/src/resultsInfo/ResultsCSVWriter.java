package resultsInfo;

import java.io.FileWriter;
import java.util.*;

public class ResultsCSVWriter {
    private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

    public ResultsCSVWriter(ArrayList<ArrayList<String>> data) {
        this.data = data;
    }

    public void saveDataToSCV(){
        try{
            FileWriter csvWriter = new FileWriter("scores.csv");
            csvWriter.append("Generation");
            csvWriter.append(",");
            csvWriter.append("Best");
            csvWriter.append(",");
            csvWriter.append("Average");
            csvWriter.append(",");
            csvWriter.append("Worst");
            csvWriter.append(",");
            csvWriter.append("Standard deviation");
            csvWriter.append("\n");

            for (List<String> rowData : data) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
