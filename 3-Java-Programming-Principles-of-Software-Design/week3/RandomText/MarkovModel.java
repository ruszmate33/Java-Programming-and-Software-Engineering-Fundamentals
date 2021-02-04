
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.ArrayList;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int N;
    
    public MarkovModel(int numCharsToPredict) {
    	myRandom = new Random();
    	N = numCharsToPredict;
    }
    
    public void setRandom(int seed){
    	myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
    	myText = s.trim();
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
