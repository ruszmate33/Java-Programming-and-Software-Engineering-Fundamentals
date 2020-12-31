import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int max = 0;
        String langHit = "";
        for (String language:languages.keySet()) {
            HashSet<String> wordsInLanguage = languages.get(language);
            String decrypted = breakForLanguage(encrypted, wordsInLanguage);
            int count = countWords(decrypted, wordsInLanguage);
            if (count > max) {
                max = count;
                langHit = language;
            }
        }
        System.out.println("language: " + langHit);
        HashSet<String> wordsInLanguage = languages.get(langHit);
        String decrypted = breakForLanguage(encrypted, wordsInLanguage);
        System.out.println(decrypted);
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> charFreq = new HashMap<Character, Integer>();
        for (String word:dictionary) {
            for (int i=0; i<word.length(); i++) {
                char letter = word.charAt(i);
                if (charFreq.containsKey(letter)) {
                    charFreq.put(letter, charFreq.get(letter)+1);
                } else {
                    charFreq.put(letter, 1);
                }
            }
        }
        int max = 0;
        char mostCommon = 'e';
        for (char letter:charFreq.keySet()) {
            int currFreq = charFreq.get(letter);
            if (currFreq > max) {
                max = currFreq;
                mostCommon = letter;
            }
        }
        return mostCommon;
    }
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i=whichSlice; i<message.length(); i += totalSlices) {
            sb.append(message.substring(i, i+1));        
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i=0; i<klength; i++) {
            String sliced = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(sliced);
        }
        return key;
    }

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        //FileResource fr2 = new FileResource("./dictionaries/");
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource frDict = new FileResource(f);
            String langName = f.getName();
            HashSet<String> wordsInLang = readDictionary(frDict);
            languages.put(langName, wordsInLang);
        }
        breakForAllLangs(encrypted, languages);
        
    }   
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        for (String line:fr.lines()) {
            line = line.trim();
            line = line.toLowerCase();
            dictionary.add(line);
        }
        return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        String[] words = message.split("\\W+");
        //System.out.println("total words: " + words.length);
        int count = 0;
        for (String word:words) {
            word = word.trim();
            word = word.toLowerCase();
            if (dictionary.contains(word)) {
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int[] bestKey = new int[100];
        System.out.println("total words: " + encrypted.split("\\W+").length);
        char mostCommon = mostCommonCharIn(dictionary);
        int max = 0;
        for (int i=1; i<100; i++) {
            int[] key = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String solution = vc.decrypt(encrypted);
            int currCount = countWords(solution, dictionary);
            if (currCount > max) {
                max = currCount;
                bestKey = key;
            }
        }
        VigenereCipher vc = new VigenereCipher(bestKey);
        System.out.println("max: " + max);
        System.out.println("keylength: " + bestKey.length);
        for (int k:bestKey) {
            System.out.println(k);
        }
        return vc.decrypt(encrypted);
    }
    
    public String silly(String encrypted, HashSet<String> dictionary) {
        int[] bestKey = new int[100];
        System.out.println("total words: " + encrypted.split("\\W+").length);
        int max = 0;
        
            int[] key = tryKeyLength(encrypted, 38, 'e');
            VigenereCipher vc = new VigenereCipher(key);
            String solution = vc.decrypt(encrypted);
            int currCount = countWords(solution, dictionary);


        System.out.println("max: " + max);
        System.out.println("keylength: " + bestKey.length);
        System.out.println("currCount: " + currCount);
        for (int k:bestKey) {
            System.out.println(k);
        }
        return vc.decrypt(encrypted);
    }
    
    public void breakSilly() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        FileResource fr2 = new FileResource("./dictionaries/english");
        HashSet<String> dictionary = readDictionary(fr2);
        String solution = silly(encrypted, dictionary);
        System.out.println(solution);
    }
}