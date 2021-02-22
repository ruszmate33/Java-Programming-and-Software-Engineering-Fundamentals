
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<Integer, ArrayList<String>> followsMap;
    
    public EfficientMarkovWord(int numOrder) {
        myOrder = numOrder;
        myRandom = new Random();
        followsMap = new HashMap<Integer, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text) {
        myText = text.split("\\s+");
        buildMap();
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key);
        sb.append(" ");
        for (int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);            
            if (follows == null || follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            //System.out.println("next: "+key);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    public ArrayList<String> getFollows(WordGram  key) {
        ArrayList<String> follows = new ArrayList<String>();
        
        Integer keyInt = key.hashCode();
        // System.out.println("keyInt: "+keyInt);
        follows = followsMap.get(keyInt);

        return follows;
    }
    
    public int indexOf(String[] words, WordGram target, int start) {
        for (int k=start; k<words.length-myOrder; k++) {
            WordGram other = new WordGram(myText, k, myOrder);
            if (target.equals(other)) {
                return k;
            }
        }
        return -1;
    }
    
    private void buildMap() {
        for (int index = 0; index < myText.length - myOrder; index += 1) {
            WordGram wg = new WordGram(myText, index, myOrder);
            // System.out.println("keyStr: " + wg);
            int hash = wg.hashCode();
            if (followsMap.containsKey(hash)) {
                ArrayList<String> followsCurrent = followsMap.get(hash);
                followsCurrent.add(myText[index+myOrder]);
                // System.out.println("value: " + followsMap.get(hash));
            } else {
                ArrayList<String> followsCurrent = new ArrayList<String>();
                followsCurrent.add(myText[index+myOrder]);
                followsMap.put(hash, followsCurrent);
                // System.out.println("value: " + followsMap.get(hash));
            }
            
        }
        // take care of last wordGram with "" character
        WordGram wgLast = new WordGram(myText, myText.length-myOrder, myOrder);
        // System.out.println("last wordGram "+ wgLast);
        
        int hash = wgLast.hashCode();
        if (!followsMap.containsKey(hash)) {
                ArrayList<String> followsCurrent = new ArrayList<String>();
                followsCurrent.add("");
                followsMap.put(hash, followsCurrent);
                // System.out.println("last followsCurrent"+ followsCurrent);
        }
        // System.out.println("value: " + followsMap.get(hash));
    }
        
    public void printHashMapInfo() {
        if (followsMap.size() < 50) {
            for (int key:followsMap.keySet()) {
                System.out.println("key: " + key);
                System.out.println("value: " + followsMap.get(key));
            }
        }
        System.out.println("num of keys: " + followsMap.size());
        HashMap<Integer, Integer> keyToSize = new HashMap<Integer, Integer>();
        for (int key:followsMap.keySet()) {
            int size = followsMap.get(key).size();
            keyToSize.put(key, size);
        }
        ArrayList<Integer> maxSizeKeys = new ArrayList<Integer>();
        int maxValue = 0;
        for (int value:keyToSize.values()) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        System.out.println("maxSizeValue: " + maxValue);
        for (int key:keyToSize.keySet()) {
            if (keyToSize.get(key) == maxValue) {
                maxSizeKeys.add(key);
            }
        }
        System.out.println("maxSizeKeys: " + maxSizeKeys);
        // for (int key:maxSizeKeys) {
            // System.out.println("key: " + key);
            // System.out.println("value: " + keyToSize.get(key));
        // }

    }
}