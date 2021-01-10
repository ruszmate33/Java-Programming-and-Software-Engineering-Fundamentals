
/**
 * Write a description of LargestQuakes here.
 * 
 * @author Mate Rusz 
 * @version (a version number or a date)
 */

import java.util.*;

public class LargestQuakes {
    public void testFindLargestQuakes() {
        findLargestQuakes("data/nov20quakedata.atom");
        //findLargestQuakes("data/nov20quakedatasmall.atom");
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = quakeData;
        
        if (quakeData.size() < howMany) {
            howMany = quakeData.size();
        }
        
        for (int k = 0; k<howMany; k++) {
            int largestIndex = indexOfLargest(copy);
            QuakeEntry largest = copy.get(largestIndex);
            
            
            answer.add(largest);
            copy.remove(largest);
        }

        return answer;
    }
    
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        double max = 0.0;
        int index = 0;
        for (int k=0; k < data.size(); k++) {
            double magnitude = data.get(k).getMagnitude();
            if (magnitude > max) {
                index = k;
                max = magnitude;
            }
        }
        return index;    
    } 
    
    public void findLargestQuakes(String source) {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        // for (QuakeEntry qe : list) {
            // System.out.println(qe);
        // }
        int index = indexOfLargest(list);
        QuakeEntry largest = list.get(index);
        System.out.println("index: "+index+ " " + largest.getInfo() + " "+ largest.getMagnitude());
        
        ArrayList<QuakeEntry> largestOnes = getLargest(list, 50);
        for (QuakeEntry qe:largestOnes) {
            System.out.println(qe);
        }
    }
}
