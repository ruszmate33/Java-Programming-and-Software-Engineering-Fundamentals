
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.ArrayList;

public class MarkovFour extends AbstractMarkovModel {
    
    
    public MarkovFour() {
    	myRandom = new Random();
    }
       
    public void setTraining(String s){
    	myText = s.trim();
    }
    
    public String toString() {
        return "MarkovModel of order 4";
    }
        
    
    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        //random key
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index, index+4);
        sb.append(key);
        for(int i=0; i< numChars-4; i++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size()==0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1,4) + next;
        }
        return sb.toString();
    }
    
    
}
