
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.File;
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {
    
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public int findIndexOfMax() {
        int indexOfMaxSoFar = 0;
        int maxSoFar = 0;
        for (int k=0; k<myFreqs.size(); k++) {
            int value = myFreqs.get(k);
            if (value > maxSoFar) {
                maxSoFar = value;
                indexOfMaxSoFar = k;
            }
        }
        
        return indexOfMaxSoFar;
    } 
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
                
        FileResource resource = new FileResource();
        
        for (String s : resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }
    
    public void tester() {
        findUnique();
        System.out.println("# unique words: " + myWords.size());
        // for (int k=0; k<myWords.size(); k++) {
            // System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));        
        // }
        int indexOfMax = findIndexOfMax();
        System.out.println("most frequent word is: " + myWords.get(indexOfMax) + " with " + myFreqs.get(indexOfMax));
    }

}
