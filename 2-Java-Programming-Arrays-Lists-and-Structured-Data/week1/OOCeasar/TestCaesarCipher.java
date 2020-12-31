
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.File;
import edu.duke.*;

public class TestCaesarCipher {
    public String breakCaesarCipher(String input) {
        
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4-maxDex);
        }
        //CeasarCipher cc = new CeasarCipher(26-dkey);
        //String solution = cc.encrypt(input);
        // altrenatively 
        CeasarCipher cc = new CeasarCipher(dkey);
        String solution = cc.decrypt(input);

        return solution;
    }
    
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String contents = fr.asString();
        System.out.println("original: " + contents);
        CeasarCipher cc = new CeasarCipher(15);
        String encrypted = cc.encrypt(contents);
        System.out.println("encrypted: " + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("decrypted: " + decrypted); 
        System.out.println("test breakCaesarCipher: " + breakCaesarCipher(encrypted));
    }
    private int[] countLetters(String message) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k=0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alphabet.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        for (int i=0; i < counts.length; i++) {
            System.out.println(i + ": " + counts[i]);
        }
        return counts;
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
