
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.ArrayList;

public class MarkovOne {private String myText;
    private Random myRandom;
    
    public MarkovOne() {
    	myRandom = new Random();
    }
    
    public void setRandom(int seed){
    	myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
    	myText = s.trim();
    }
    
       
    // public String getRandomText(int numChars){
        // StringBuffer sb = new StringBuffer();
        // //random key from the file provided.
        // int index = myRandom.nextInt(myText.length());
        // System.out.println("index: " + index);
        // String key = myText.substring(index, index+1);
        // System.out.println("key: " + key);
        // sb.append(key);
        // for(int i=0; i< numChars-1; i++){
            // ArrayList<String> follows = getFollows(key);
            // System.out.println("follows: " + follows);
            // if(follows.size()==0){
                // break;
            // }
            // index = myRandom.nextInt(follows.size());
            // String next = follows.get(index);
            // System.out.println("next: " + next);
            // sb.append(next);
            // key = next;
        // }
        // return sb.toString();
    // }
    
    // public ArrayList<String> getFollows(String key) {
        // ArrayList<String> follows = new ArrayList<String>();
        // // find occurence of key
        // int pos = 0;
        // int lastPosition = myText.length() - key.length() -1;
        // while (pos < lastPosition) {
            // myText = myText.substring(pos);
            // pos = myText.indexOf(key);
            // if (pos == -1) {
                // break;
            // }
            // String letter = myText.substring(pos+key.length(), pos+key.length()+1);
            // //System.out.println(letter);
            // follows.add(letter);
            // pos++;
        // }
        // return follows;
    // }
    
    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        //random key
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index+1);
        sb.append(key);
        for(int i=0; i< numChars-1; i++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size()==0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key){
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
            pos = start+key.length();
        }
        return follows;
    }

}
