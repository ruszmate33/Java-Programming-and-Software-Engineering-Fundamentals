
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text) {
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            //System.out.println("key: " + key1 + " " + key2);
            //System.out.println("follows: " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int index = 0;
        while (index < myText.length-2) {
            index = indexOf(myText, key1, key2, index);
            if (index == -1) {
                break;
            }
            if (index == myText.length-2) {
                break;
            }
            String next = myText[index+2];
            follows.add(next);
            index++;
        }
        return follows;
    }
    
    private int indexOf(String[] words, String target1, String target2, int start) {
        for (int k=start; k<words.length-1; k++) {
            if (target1.equals(words[k]) && target2.equals(words[k+1])) {
                return k;
            }
        }
        return -1;
    }
    
    public void testIndexOf() {
        String testSentence = "this is just a test yes this is a simple test";
        String[] words = testSentence.split(" ");
        System.out.println(indexOf(words, "this", "is", 0));
        System.out.println(indexOf(words, "this", "is", 3));
        System.out.println(indexOf(words, "frog", "mad", 0));
        System.out.println(indexOf(words, "frog", " ", 5));
        System.out.println("simple test " + indexOf(words, "simple", "test", 2));
        System.out.println("test yes " + indexOf(words, "test", "yes", 1));
        System.out.println("test yes " + indexOf(words, "test", "yes", 5));
    }
}
