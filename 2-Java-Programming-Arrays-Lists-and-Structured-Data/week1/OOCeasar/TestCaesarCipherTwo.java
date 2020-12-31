
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.File;
import edu.duke.*;

public class TestCaesarCipherTwo {
    public String breakCaesarCipher(String encrypted) {
        String oneHalf = halfOfString(encrypted, 0);
        String otherHalf = halfOfString(encrypted, 1);
             
        int key1 = getKey(oneHalf);
        int key2 = getKey(otherHalf);
        System.out.println("key1: " + key1 + " key2: " + key2);
        
        CaesarCipherTwo ccTwo = new CaesarCipherTwo(key1, key2);
        return ccTwo.decrypt(encrypted);
            
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String contents = fr.asString();
        System.out.println("original content: " + contents);
        CaesarCipherTwo ccTwo = new CaesarCipherTwo(12, 2);
        String encrypted = ccTwo.encrypt(contents);
        System.out.println("encrypted: " + encrypted);
        System.out.println("decrypted with method: " + ccTwo.decrypt(encrypted));
        System.out.println("breakCaesarCipher " + breakCaesarCipher(contents));
    }
    
    public String halfOfString(String message, int start) {
        StringBuilder halfString = new StringBuilder();
        for (int i=start; i<message.length(); i+=2) {
            halfString.append(message.charAt(i));
        }
        return halfString.toString();
    }
    
    public int[] countLetters(String message) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k=0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alphabet.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        // for (int i=0; i < counts.length; i++) {
            // System.out.println(i + ": " + counts[i]);
        // }
        return counts;
    }
    public int getKey(String s) {
        
        int maxDex = maxIndex(countLetters(s));
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4-maxDex);
        }        
        
        // maxIndex(countLetters(s))
        return dkey;
    } 
    
    public int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int k=0; k < vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;            
            }
        }
        return maxDex;
    }
    

}
