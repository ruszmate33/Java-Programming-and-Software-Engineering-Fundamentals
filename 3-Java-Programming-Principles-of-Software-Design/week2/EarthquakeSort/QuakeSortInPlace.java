
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int maxIdx = from;
        for (int i=from+1; i<quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    private boolean checkInSortedOrder(ArrayList<QuakeEntry> in) {
        for (int i=0; i< in.size()-1; i++) {
            QuakeEntry qe = in.get(i);
            QuakeEntry qeFollowing = in.get(i+1);
            if (qe.getMagnitude() > qeFollowing.getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> in, int numSorted) {
        int lastUnsorted = in.size() - numSorted - 1;
        for (int i=0; i< lastUnsorted; i++) {
            QuakeEntry qe = in.get(i);
            QuakeEntry qeFollowing = in.get(i+1);
            if (qe.getMagnitude() > qeFollowing.getMagnitude()) {
                in.set(i, qeFollowing);
                in.set(i+1, qe);
            }
        }
        // System.out.println("printing quakes after pass " + numSorted);
        // for (QuakeEntry entry:in) {
            // System.out.println(entry.getMagnitude());
        // }
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        for (int i=0; i< in.size(); i++) {
            if (checkInSortedOrder(in)) {
                System.out.println("passes: "+i);
                break;
            }                
        
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
    }
    
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int passes = 0;
        for (int i=0; i< in.size()-1; i++) {
            passes++;
            if (checkInSortedOrder(in)) {
                break;
            } else {
                onePassBubbleSort(in, i);
            }
        }
        System.out.println("passes: " + passes);
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        int passes = 0;
        for (int i=0; i< in.size(); i++) {
            onePassBubbleSort(in, i);
            passes++;
        }
        System.out.println("passes: " + passes);
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for (int i=0; i< 70; i++) {
        //for (int i=0; i< in.size(); i++) {
            int maxIdx = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i, qmax);
            in.set(maxIdx, qi);
        }
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        // String source = "data/nov20quakedatasmall.atom";
        String source ="data/earthQuakeDataWeekDec6sample2.atom";
        
        // String source ="data/earthquakeDataSampleSix1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        // sortByMagnitude(list);
        // sortByLargestDepth(list);
        // sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        // sortByMagnitudeWithCheck(list);
        
        
        // for (QuakeEntry qe: list) { 
            // System.out.println(qe);
        // } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}