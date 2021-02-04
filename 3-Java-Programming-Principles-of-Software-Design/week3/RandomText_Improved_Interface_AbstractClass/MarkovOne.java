
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.ArrayList;

public class MarkovOne extends AbstractMarkovModel {
    
    public MarkovOne() {
        myRandom = new Random();
    }
       
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String toString() {
        return "MarkovModel of order 1";
    }
       
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
    

}
