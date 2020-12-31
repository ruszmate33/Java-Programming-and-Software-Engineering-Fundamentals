
/**
 * Write a description of WeatherAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class WeatherAnalyzer {
    void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTempHighHum = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (Double.isNaN(avgTempHighHum)) {
            System.out.println("No temperatures with that humidity");
        } else {
        System.out.println("Average Temp when high Humidity is " + avgTempHighHum);
        }
    }
    double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double avgTempHighHum;
        double entries = 0;
        double sum = 0;
        for (CSVRecord currentRow : parser) {
            if (!isMissingValue(currentRow.get("TemperatureF"), "-9999") && Double.parseDouble(currentRow.get("Humidity")) >= value) {
                double currentValue = Double.parseDouble(currentRow.get("TemperatureF"));
                entries++;
                sum = sum + currentValue;
            
            }
        }
        avgTempHighHum = sum / entries;
        return avgTempHighHum;
    }
    
    void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + average);
    }
    
    double averageTemperatureInFile(CSVParser parser) {
        double average;
        double entries = 0;
        double sum = 0;
        for (CSVRecord currentRow : parser) {
            if (!isMissingValue(currentRow.get("TemperatureF"), "-9999")) {
                double currentValue = Double.parseDouble(currentRow.get("TemperatureF"));
                entries++;
                sum = sum + currentValue;
            
            }
        }
        average = sum/entries;
        return average;
    
    }
    
    void testLowestHumidityInManyFiles() {
        CSVRecord lowestRow = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestRow.get("Humidity") + " at " + lowestRow.get("DateUTC"));
    }
    
    CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHumidity = Double.parseDouble(lowestSoFar.get("Humidity"));   
                if (currentTemp < lowestHumidity) {
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;    
    }
    void testLowestHumidityInFile() {
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       CSVRecord csv = lowestHumidityInFile(parser);
       System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumidity = getMinimum(parser, "Humidity", "N/A");
        return lowestHumidity;
    }
    
    void testFileWithColdestTemperature() {
        String fileNameColdestTemp = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + fileNameColdestTemp);
    }
    
    
    String fileWithColdestTemperature() {
        String fileNameColdestTemp = null;
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coolestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));   
                if (currentTemp < coolestTemp) {
                    coldestSoFar = currentRow;
                    fileNameColdestTemp = f.getName();
                }
            }
        }
        System.out.println("Coldest temperature on that day was " + coldestSoFar.get("TemperatureF"));
        return fileNameColdestTemp;
    }
    
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("All the info in the coldest row");
        for (String info : coldest) {
            System.out.println(info);
        
        }
        System.out.println("Coldest temp[F]: " + coldest.get("TemperatureF"));
    }
    
    
    CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestRow = getMinimum(parser, "TemperatureF", "-9999");
        return coldestRow;
    }
    
    CSVRecord getMinimum(CSVParser parser, String columnName, String missingValue) {
        CSVRecord lowestRow = null;
        for (CSVRecord currentRow : parser) {
            if (lowestRow == null && (!isMissingValue(currentRow.get(columnName), missingValue))) {
                lowestRow = currentRow;
            }
            else {
                    if (!isMissingValue(currentRow.get(columnName), missingValue)) {
                    double currentParam = Double.parseDouble(currentRow.get(columnName));
                    double lowestParam = Double.parseDouble(lowestRow.get(columnName));   
                        if (!isMissingValue(currentRow.get(columnName), missingValue) && currentParam < lowestParam) {
                            lowestRow = currentRow;
                        }
                    }
            }
        }
        return lowestRow;
    }
    boolean isMissingValue(String value, String missingValue) {
        return value.equals(missingValue);
    }
}
