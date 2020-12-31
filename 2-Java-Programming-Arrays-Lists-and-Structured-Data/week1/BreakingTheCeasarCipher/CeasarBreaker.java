
/**
 * Write a description of CeasarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.File;
import edu.duke.*;

public class CeasarBreaker {
    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource("quiz6.txt");
        String encrypted = fr.asString();
        System.out.println(encrypted);
        System.out.println(decryptTwoKeys(encrypted));
    }
    
    public String decryptTwoKeys(String encrypted) {
        CeasarCipher cc = new CeasarCipher();
        String oneHalf = halfOfString(encrypted, 0);
        String otherHalf = halfOfString(encrypted, 1);
             
        int key1 = getKey(oneHalf);
        int key2 = getKey(otherHalf);
        System.out.println("key1: " + key1 + " key2: " + key2);
        return cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);
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
    
    public void testHalfString() {
        if (!halfOfString("Qbkm Zgis", 0).equals("Qk gs"))  {
            System.out.println("problem with Qbkm Zgis, 0 " + halfOfString("Qbkm Zgis", 0) + " vs " + "Qk gs");        
        } 
        if (!halfOfString("Qbkm Zgis", 1).equals("bmZi"))  {
            System.out.println("problem with Qbkm Zgis, 1");        
        } 
        System.out.println("halfOfString tested");
        
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
    
    public void testDecrypt() {
        System.out.println("Rpc ndj xbpvxct axut LXIWDJI iwt xcitgcti PCS rdbejitgh xc ndjg edrzti?");
        System.out.println(decrypt("Rpc ndj xbpvxct axut LXIWDJI iwt xcitgcti PCS rdbejitgh xc ndjg edrzti?"));
    }
    
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

}
