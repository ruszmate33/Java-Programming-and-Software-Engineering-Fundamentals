
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private String oneHalf;
    private String otherHalf;
    private int mainKey1;
    private int mainKey2;
    
    
    public CaesarCipherTwo(int key1, int key2) {
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        alphabet = upperAlphabet + lowerAlphabet;
        shiftedAlphabet1 = upperAlphabet.substring(key1) + upperAlphabet.substring(0, key1) 
        + lowerAlphabet.substring(key1) + lowerAlphabet.substring(0, key1);
        
        shiftedAlphabet2 = upperAlphabet.substring(key2) + upperAlphabet.substring(0, key2) 
        + lowerAlphabet.substring(key2) + lowerAlphabet.substring(0, key2);
        
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String decrypt(String input) {
        oneHalf = halfOfString(input, 0);
        otherHalf = halfOfString(input, 1);
        
        CaesarCipherTwo ccTwo = new CaesarCipherTwo(26-mainKey1, 26-mainKey2);
        return ccTwo.encrypt(input);
    }
    
    // private int getKey(String s) {
        
        // int maxDex = maxIndex(countLetters(s));
        // int dkey = maxDex - 4;
        // if (maxDex < 4) {
            // dkey = 26 - (4-maxDex);
        // }        
        
        // // maxIndex(countLetters(s))
        // return dkey;
    // }
    
    private String halfOfString(String input, int start) {
        StringBuilder halfString = new StringBuilder();
        for (int i=start; i<input.length(); i+=2) {
            halfString.append(input.charAt(i));
        }
        return halfString.toString();
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i=0; i<encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if (idx != -1 ) {
                char newChar;
                if ((i % 2 == 0)) {
                    newChar = shiftedAlphabet1.charAt(idx);
                } else {
                    newChar = shiftedAlphabet2.charAt(idx);
                }
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
}
