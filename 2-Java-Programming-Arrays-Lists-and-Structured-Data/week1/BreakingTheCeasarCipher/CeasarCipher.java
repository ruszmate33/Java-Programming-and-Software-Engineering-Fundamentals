
/**
 * Write a description of CeasarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CeasarCipher {
    public void testEncryptTwoKeys() {
        System.out.println("Can you imagine life WITHOUT the internet AND computers in your pocket?");
        System.out.println(encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21, 8));

        
        // if (!encryptTwoKeys("First Legion", 23, 17).equals("Czojq Ivdzle")) {
            // System.out.println("Some issue with First Legion, 23, 17 not Czojq Ivdzle but " + encryptTwoKeys("First Legion", 23, 17));
        // } else {
            // System.out.println("encryptTwoKeys looks good!");
        // }
        // System.out.println("At noon be in the conference room with your hat on for a surprise party. YELL LOUD! 8 21");
        // System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
        // System.out.println("Top ncmy qkff vi vguv vbg ycpx 2 20");
        // System.out.println(encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 24, 6));
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        // StringBuilder encrypted = new StringBuilder("");
        String encrypted = "";
        for (int i=0; i<input.length(); i++) {
            String currCharAsString = input.substring(i, i+1);
            String newChar = "";
            if (i % 2 == 0) {
                newChar = encrypt(currCharAsString, key1);
            } else {
                newChar = encrypt(currCharAsString, key2);
            }
            // encrypted.append(newChar);
            //System.out.println("iteration "+ i + "currCharAsString: " + currCharAsString + "newChar: " + newChar);
            encrypted = encrypted + newChar;
        }
        return encrypted.toString();
    }
    
    public void testCaesar() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 7;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);  
    }
    
    public void testEncrypt() {
        System.out.println("testEntrypt");
        System.out.println("Can you imagine life WITHOUT the internet AND computers in your pocket?");
        System.out.println(encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15));
        
        // if (encrypt("FIRST LEGION ATTACK EAST FLANK!", 23).equals("CFOPQ IBDFLK XQQXZH BXPQ CIXKH!")) {
            // System.out.println("all capital is fine");
        // } else {
            // System.out.println("all capital has issues");
            // System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
        // }
        // if (!encrypt( "First Legion", 17).equals("Wzijk Cvxzfe")) {
            // System.out.println("issues with First Legion, 17 " + encrypt( "First Legion", 17));
        // } else if (!encrypt("First Legion", 23).equals("Cfopq Ibdflk")) {
            // System.out.println("issues with First Legion, 23 " + encrypt( "First Legion", 23));
        // } else {
            // System.out.println("all is good");
        // }
        
        // System.out.println("At noon be in the conference room with your hat on for a surprise party. YELL LOUD! 15");
        // System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
        
    }
    
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String upperLowerAlphabet = upperAlphabet + lowerAlphabet;
        String shiftedAlphabet = upperAlphabet.substring(key) + upperAlphabet.substring(0, key) 
        + lowerAlphabet.substring(key) + lowerAlphabet.substring(0, key);
        for (int i=0; i<encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = upperLowerAlphabet.indexOf(currChar);
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }   
        }
        return encrypted.toString();        
    }
}
