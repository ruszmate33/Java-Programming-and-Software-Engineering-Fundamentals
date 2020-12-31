
/**
 * Write a description of BreakingCeasar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class BreakingCeasar {
    
    public String decrypt(String encrypted) {
        CeasarCipher cc = new CeasarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4-maxDex);
        }
        
        return cc.encrypt(encrypted, 26-dkey);
    
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
        for (int i=0; i < counts.length; i++) {
            System.out.println(i + ": " + counts[i]);
        }
        return counts;
    }   
    
    public void textFingerPrint(String s) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counters = new int[26];
        for (int k=0; k < s.length(); k++) {
            char ch = s.charAt(k);
            int index = alphabet.indexOf(Character.toLowerCase(ch));
            if (index != -1) {
                counters[index] += 1;
            }
        }
        for (int i=0; i < counters.length; i++) {
            System.out.println(alphabet.charAt(i)+"\t"+counters[i]);
        }
    }

}