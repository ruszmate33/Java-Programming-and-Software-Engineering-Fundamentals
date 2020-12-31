
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class BabyBirths {
    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn > 100) {
                System.out.println("Name " + rec.get(0) +
                                " Gender " + rec.get(1) +
                                " number born " + rec.get(2));
            }
        }
    }
    public void testTotalBirths() {
        FileResource fr = new FileResource("us_babynames_by_year/yob1905.csv");
        totalBirths(fr);
    }
    
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            } else {
                totalGirls += numBorn;
            }
        }
        System.out.println("Total: " + totalBirths + " girls: " + totalGirls + " boys: " + totalBoys);    
    }
    public void testGetRank() {
        //System.out.println("Mason, M in 2012: " + getRank(2012, "Mason", "M"));
        System.out.println("Rank Emily, F in 1960: " + getRank(1960, "Emily", "F"));
        System.out.println("Rank Frank, M in 1971: " + getRank(1971, "Frank", "M"));
    }
    
    public int getRank(int year, String name, String gender) {
        // returns the rank of the name in the file for the given gender
        int rank = 1;
        // String fileName = "us_babynames_test/yob"+ year + "short.csv";
        String fileName = "us_babynames_by_year/yob"+ year + ".csv";
        //String fileName = "us_babynames_by_year/yob"+ year + ".csv";
        FileResource fr = new FileResource(fileName);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                if  (rec.get(0).equals(name)) {
                    return rank;
                } else {
                    rank++;
                }           
            }
        }
        System.out.println("not found: " + name + ", " + gender + " in " + year );
        return -1;
    }
    public int getRankInFileResource(String name, String gender, FileResource fr) {
        // returns the rank of the name in the file for the given gender
        int rank = 1;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                if  (rec.get(0).equals(name)) {
                    return rank;
                } else {
                    rank++;
                }           
            }
        }
        //System.out.println("not found: " + name + ", " + gender + " in ");
        return -1;
    }
    
    void testGetName() {
        System.out.println("getName() with 1980, 350, F returns: " + getName(1980, 350, "F"));
        System.out.println("getName() with 1982, 450, M returns: " + getName(1982, 450, "M"));
        // System.out.println("getName() with 2012, 2, M returns: " + getName(2012, 2, "M"));
        // System.out.println("getName() with 2012, 2, F returns: " + getName(2012, 2, "F"));
        // System.out.println("getName() with 2012, 20, F returns: " + getName(2012, 20, "F"));
    }
    
    public String getName(int year, int rank, String gender)  {
        //String fileName = "us_babynames_test/yob"+ year + "short.csv";
        String fileName = "us_babynames_by_year/yob"+ year + ".csv";
        //String fileName = "us_babynames_by_year/yob"+ year + ".csv";
        int currentRank = 1;
        FileResource fr = new FileResource(fileName);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            // System.out.println("Name: " + rec.get(0) + "gender: " + rec.get(1) + "currentRank: " + currentRank);
            if (rec.get(1).equals(gender)) {
                if (currentRank == rank) {
                    String name = rec.get(0);
                    return name;
                } else {
                    currentRank++;
                }
            }
        }
        return "NO NAME";
    }
    public void testWhatIsNameInYear() {
        String name = "Owen";
        int year = 1974;
        int newYear = 2014;
        String gender = "M";
        System.out.println(name + " from " + year + " in " + newYear + " is " + whatIsNameInYear(name, year, newYear, gender));
    }
    
    public String whatIsNameInYear(String name, int  year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        return newName;
    }
    public void testYearOfHighestRank() {
        System.out.println("year of highest rank, Mich, M: " + yearOfHighestRank("Mich", "M"));
    }
    
    public String yearOfHighestRank(String name, String gender) {
        //int year = 0;
        String year = "";
        int highestRank = 0;
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {           
            FileResource fr = new FileResource(f);
            int currentRank = getRankInFileResource(name, gender, fr);
            if (highestRank == 0 && currentRank != -1) {
                highestRank = currentRank;
                year = f.getName();
                //year = Integer.parseInt(f.getName());
            } else {
                if (currentRank < highestRank && currentRank != -1) {
                    highestRank = currentRank;
                    //year = Integer.parseInt(f.getName());
                    year = f.getName();
                }
            }
        }
        if (highestRank == 0) {
            year = "-1";
        }
        //selects a range of files 
        return year;
    }
    public void testGetAverageRank() {
        // System.out.println("Mason" + " " + "M" + " " +getAverageRank("Mason", "M"));
        System.out.println("Robert" + " " + "M" + " " +getAverageRank("Robert", "M"));
    }
    
    public Double getAverageRank(String name, String gender) {
        Double sum = 0.0;
        int numElements = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {           
            FileResource fr = new FileResource(f);
            int currentRank = getRankInFileResource(name, gender, fr);
            if (currentRank != -1) {
                sum += currentRank;
                numElements++;
            }
        }
        return sum/numElements;
    }
    public void testGetTotalBirthsRankedHigher() {
        System.out.println("1990 " + "Emily " + "F: " + getTotalBirthsRankedHigher(1990, "Emily", "F"));
        System.out.println("1990 " + "Drew " + "M: " + getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {  
        String fileName = "us_babynames_by_year/yob"+ year + ".csv";
        //String fileName = "us_babynames_by_year/yob"+ year + ".csv";
        int totalBirthsRankedHigher = 0;
        FileResource fr = new FileResource(fileName);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            // System.out.println("Name: " + rec.get(0) + "gender: " + rec.get(1) + "currentRank: " + currentRank);
            if (rec.get(1).equals(gender)) {
                if (name.equals(rec.get(0))) {
                    return totalBirthsRankedHigher;
                } else {
                    totalBirthsRankedHigher += Integer.parseInt(rec.get(2));
                }
            }
        }
        return totalBirthsRankedHigher;
    }
}
