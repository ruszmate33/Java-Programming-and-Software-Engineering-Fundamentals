
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int N;
    private HashMap <String, ArrayList<String>> mapped;
    
    public EfficientMarkovModel(int numCharsToPredict) {
        myRandom = new Random();
        N = numCharsToPredict;
        mapped = new HashMap <String, ArrayList<String>>();
    }
       
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String toString() {
        String order = Integer.toString(N);
        return "EfficientMarkovModel of order " + N;
    }
    
    
    public void buildMap() {
        mapped.clear();
        System.out.println("start to build");
        int k = 0;
        ArrayList<String> follows;
        while (k<myText.length()-N+1) {
            // new key
            String key = myText.substring(k, k+N);
            // following the key array
            
            if (!mapped.keySet().contains(key)) {
                follows = generateFollows(key);
                // System.out.println(key + " -- " + follows);
                mapped.put(key, follows);
            }
            k++; 
        }
        
    }
    
    private ArrayList<String> generateFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        //String phrase = "this is a test yes this is a test.";
        int pos = 0;   
        while(pos<myText.length()){
            int start = myText.indexOf(key,pos);
            if(start == -1){
                break;
            }
            if(start+key.length() >= myText.length()){
                break;
            }
            String next = myText.substring(start+key.length(),start+key.length()+1);
            follows.add(next);
            pos = start + 1;
        }
        return follows;
    }
    
    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        //random key
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index, index+N);
        sb.append(key);
        buildMap();
        printHashMapInfo();
        
        for(int i=0; i< numChars-N; i++){
            
            ArrayList<String> follows = getFollows(key);
            if ((follows == null) || (follows.size()==0)) {
                break;
            }
            
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1,N) + next;
        }
        return sb.toString();
    }
    
    protected ArrayList<String> getFollows(String key){
        ArrayList<String> follows = mapped.get(key);
        return follows;
    }
    
    public void printHashMapInfo() {
        if (mapped.size() < 1000) {
            System.out.println(mapped);
        }
        System.out.println("num of keys " + mapped.keySet().size());
        // the largest value in the HashMap—that is, the size of the largest ArrayList of characters
        int maxElements = 0;
        int numKeys = 0;
        ArrayList <String> keysMaxSize = new ArrayList <String>();
        HashMap <String, Integer> keyToNum = new HashMap <String, Integer>();
        for (String key:mapped.keySet()) {
            ArrayList <String> follows = getFollows(key);
            int currentSize = follows.size();
            keyToNum.put(key, currentSize);
            numKeys++;
            if (currentSize > maxElements) {
                maxElements = currentSize;
            }
        }
        System.out.println("num of keys " + numKeys);
        for (String key:keyToNum.keySet()) {
            if (keyToNum.get(key) == maxElements) {
                keysMaxSize.add(key);            
            }
        }

        System.out.println("size of the largest value " + maxElements);
        System.out.println("keys with largest value " + keysMaxSize);
    }
}
