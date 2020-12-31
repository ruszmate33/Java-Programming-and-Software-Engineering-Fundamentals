
/**
 * Write a description of CeasarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CeasarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CeasarCipher(int key) {
        mainKey = key;
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        alphabet = upperAlphabet + lowerAlphabet;
        shiftedAlphabet = upperAlphabet.substring(key) + upperAlphabet.substring(0, key) 
        + lowerAlphabet.substring(key) + lowerAlphabet.substring(0, key);
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i=0; i<encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input) {
        
        CeasarCipher cc = new CeasarCipher(mainKey);
        return cc.encrypt(input);
    }
    
    

}
