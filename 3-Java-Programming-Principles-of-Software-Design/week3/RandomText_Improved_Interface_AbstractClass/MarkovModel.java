
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.ArrayList;

public class MarkovModel extends AbstractMarkovModel {
    
    private int N;
    
    public MarkovModel(int numCharsToPredict) {
    	myRandom = new Random();
    	N = numCharsToPredict;
    }
       
    public void setTraining(String s){
    	myText = s.trim();
    }
    
    public String toString() {
        String order = Integer.toString(N);
        return "MarkovModel of order " + N;
    }
        
    
    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        //random key
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index, index+N);
        sb.append(key);
        for(int i=0; i< numChars-N; i++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size()==0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1,N) + next;
        }
        return sb.toString();
    }
    
    
}
