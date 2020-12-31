
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class CaesarCipher {
    public void testCaesar() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 7;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);  
    }
    
    public void testEncrypt() {
        System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
        System.out.println(encrypt("First Legion", 17));
    }
    
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        for (int i=0; i<encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            currChar = toUpperCase(currChar); 
            int idx = alphabet.indexOf(currChar);
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                if (isLowerCase(encrypted.charAt(i))) {
                    toLowerCase(newChar);
                }
                encrypted.setCharAt(i, newChar);
            }   
        }
        return encrypted.toString();        
    }

}
