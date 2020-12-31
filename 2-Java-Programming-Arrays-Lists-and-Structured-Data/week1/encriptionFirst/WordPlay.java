
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;

public class WordPlay {
    public void testEmphasize() {
        String testString = "dna ctgaaactga";
        char testChar = 'a';
        System.out.println(testString + " " + emphasize(testString, testChar));
        String testString2 = "Mary Bella Abracadabra";
        char testChar2 = 'a';
        System.out.println(testString + " " + emphasize(testString2, testChar2));
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder sb = new StringBuilder("");
        for (int i=0; i<phrase.length(); i++) {
            char c = phrase.charAt(i);
            if (c == ch) {
                if (i % 2 == 0) {
                    c = '*';
                } else {
                    c = '+';
                }            
            }
            sb.append(c);
        }
        String newPhrase = sb.toString();
        return newPhrase;
    }
    
    
    public void testReplaceVowels() {
        String testString = "Hello World ";
        System.out.println(testString + replaceVowels(testString, '*'));
    }   
    public String replaceVowels(String phrase, char ch) {
        
        StringBuilder sb = new StringBuilder("");
        for (int i=0; i<phrase.length(); i++) {
            char c = phrase.charAt(i);
            if (isVowel(c)) {
                c = ch;            
            }
            sb.append(c);
        }
        String newPhrase = sb.toString();
        return newPhrase;
    }
    
    public void testIsVowel() {
        if (isVowel('e')) {
            System.out.println("e looks good!");
        }
        if (!isVowel('m')) {
            System.out.println("m looks good!");
        }
        
    }
    
    public boolean isVowel(char ch) {
        String vowels = "aeiou";
        for (int i=0; i<vowels.length(); i++) {
            if (vowels.charAt(i) == ch) {
                return true;
            }
        }
        return false;
    }

}
