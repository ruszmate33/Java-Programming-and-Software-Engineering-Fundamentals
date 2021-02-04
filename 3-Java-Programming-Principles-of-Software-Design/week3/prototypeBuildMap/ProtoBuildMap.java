
/**
 * Write a description of ProtoBuildMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class ProtoBuildMap {
    private HashMap <String, ArrayList<String>> mapped;
    
    private String myText = "yes-this-is-a-thin-pretty-pink-thistle";
    private int N=2;
    
    public ProtoBuildMap() {
        mapped = new HashMap <String, ArrayList<String>>();
    }
    
    public void buildMap() {
        mapped.clear();
        System.out.println("start to build");
        int k = 0;
        
        // generate key
        while (k<myText.length()) {
            String key = myText.substring(k, k+N);
            System.out.println("key: " + key);
            // following the key array
            ArrayList<String> follows = generateFollows(key);
            if (!mapped.keySet().contains(key)) {
                mapped.put(key, follows);
            }
            k++;  
        }
        for (String key:mapped.keySet()) {
            System.out.println("key " + key + " values: " + mapped.get(key));
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
            pos = start+key.length();
        }
        return follows;
    }
    
    
    public void testBuildMap() {
        buildMap();
    }
}
