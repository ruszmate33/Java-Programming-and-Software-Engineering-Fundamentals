
/**
 * Write a description of Parser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class Parser {
    public void testBigExporters() {
        CSVParser parser = tester();
        bigExporters(parser, "$999,999,999,999");
    }
    
    public void bigExporters(CSVParser parser, String dollarThreshold) {
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            boolean isBig = (value.length() > dollarThreshold.length());
            if (isBig) {
                System.out.print(record.get("Country") + " ");
                System.out.println(value);
            }
        }
    }
    
    public void testNumberOfExporters() {
        CSVParser parser = tester();
        System.out.println(numberOfExporters(parser, "cocoa"));
        // System.out.println(numberOfExporters(parser, "gold"));
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int numExporters = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                numExporters++;
            }
        }
        return numExporters;
    }
    
    public void testListExportersTwoProducts() {
        CSVParser parser = tester();
        // listExportersTwoProducts(parser, "gold", "diamonds");
        listExportersTwoProducts(parser, "cotton", "flowers");
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public void testCountryInfo() {
        CSVParser parser = tester();
        System.out.println(countryInfo(parser, "Nauru"));
    
    }
    
    public CSVParser tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        return parser;
    }
    public String countryInfo(CSVParser parser, String countryOfInterest) {
        
        String exports = countryOfInterest+ ": NOT FOUND";
        for (CSVRecord record : parser) {
            String country = record.get("Country");
            if (country.equals(countryOfInterest)) {
                exports = countryOfInterest + ": " + record.get("Exports");
                exports = exports + ": " + record.get("Value (dollars)");
            }
        }
        return exports;
    }
}
