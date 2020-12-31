
/**
 * Write a description of WordLength here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLength {
    public int indexOfMax(int[] values) {
        int indexOfHighest = 0;
        // returns the index position of the largest element in values
        for (int i=0; i<values.length; i++){
            if (values[i] > values[indexOfHighest]) {
                indexOfHighest = i;
            }                        
        }
        return indexOfHighest;
    }
    
    public void testCountWordLengths() {
        FileResource resource = new FileResource("manywords.txt");
        int[] counts = new int[31];
        countWordLengths(resource, counts);
        System.out.println("Index of max: " + indexOfMax(countWordLengths(resource, counts)));
    }
    
    public void testGetRidFirstLastNonAlpha() {
        System.out.println("Hello, " + getRidFirstLastNonAlpha("Hello,"));
        System.out.println("blue-jeans " + getRidFirstLastNonAlpha("blue-jeans"));
        System.out.println("?why? " + getRidFirstLastNonAlpha("?why?"));
    }
    
    public String getRidFirstLastNonAlpha(String word) {
        // StringBuilder wordSB = new StringBuilder(word);
        
        char firstChar = word.charAt(0);
        if (!Character.isLetter(firstChar)) {
            word = word.substring(1);
        }
                
        if (word.length() <= 0) {
            return "";        
        }
        
        char lastChar = word.charAt(word.length()-1);
        
        if (!Character.isLetter(lastChar)) {
            word = word.substring(0, word.length()-1);
        }
        return word;
    }
    
    public int[] countWordLengths(FileResource resource, int[] counts) {
        // read in the words from resource
        //FileResource fr = new FileResource();
        
        // group all words of length 30
        //int[] counts = new int[31];
        
        // count the number of words of each length for all the words
        for (String word : resource.words()) {
            word = getRidFirstLastNonAlpha(word);
            int length = word.length();
            if (length > 30) {
                length = 30;
            }
            counts[length] += 1;        
        }
        for (int i=1; i<counts.length; i++) {
            System.out.println("length " + i + ": " + counts[i]);
        }
        return counts;
    } 

}
