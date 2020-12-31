import edu.duke.*;

public class test {
    public void testQuizSilly() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakSilly();    
    }
    
    public void testBreakVigenere() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();    
    }
    
    public void testTryKeyLength() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        String text = fr.asString();
        int[] keys = vb.tryKeyLength(text, 38, 'e');
        for (int key:keys) {
            System.out.println(key);
        }
        VigenereCipher vc = new VigenereCipher(keys);
        String decrypted = vc.decrypt(text);
        
    }
    
    public void testSliceString() {
        VigenereBreaker vb = new VigenereBreaker();
        if (!vb.sliceString("abcdefghijklm",0,3).equals("adgjm")) {
            System.out.println("abcdefghijklm,0,3 error");
        }
        else if (!vb.sliceString("abcdefghijklm",1,3).equals("behk")) {
            System.out.println("abcdefghijklm,1,3 error");
        }
        else if (!vb.sliceString("abcdefghijklm",3,5).equals("di")) {
            System.out.println("abcdefghijklm,3,5 error");
        } else {
            System.out.println("all good");
        }
    }
    
    public void testVigenere() {
        int key[] = {17,14,12,4};
        VigenereCipher vc = new VigenereCipher(key);
        FileResource fr = new FileResource("titus-small.txt");
        String text = fr.asString();
        System.out.println(vc.encrypt(text));
    }
    
    public void testCaesar() {
        CaesarCipher cc = new CaesarCipher(5);
        FileResource fr = new FileResource("titus-small.txt");
        String text = fr.asString();
        System.out.println("text: " + text);
        String encrypted = cc.encrypt(text);
        System.out.println("encrypted: " + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("decrypted: " + decrypted);
    }
    
    public void testCaesarCracker() {
        CaesarCracker caesCrack = new CaesarCracker();
        FileResource fr = new FileResource("titus-small_key5.txt");
        String text = fr.asString();
        System.out.println("text: " + text);
        System.out.println(caesCrack.decrypt(text));
        
        CaesarCracker cc = new CaesarCracker('a');
        FileResource fr2 = new FileResource("oslusiadas_key17.txt");
        String text2 = fr2.asString();
        System.out.println("text: " + text2);
        System.out.println(cc.decrypt(text2));
    }
}
