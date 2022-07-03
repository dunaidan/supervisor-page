package md.dunai;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CsvWriter {
    public void writeToFile (String path, String[] headers, Map<Integer, String[]> dataToExport) {
        File file = new File(path);
        try {
            FileWriter outfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outfile);
            writer.writeNext(headers);

            for(int key : dataToExport.keySet()) {
                writer.writeNext(dataToExport.get(key));
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
