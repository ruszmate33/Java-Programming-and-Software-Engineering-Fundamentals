import edu.duke.*;
import java.util.*;
import java.io.File;

public class WordsInFiles {
    private HashMap<String,ArrayList> wordsInFilesMap;
    private ArrayList<String> wordsInNumFiles;
    private int maxNumber;
    
    public WordsInFiles() {
        wordsInFilesMap = new HashMap<String,ArrayList>();
    }
    public void quiz() {
        WordsInFiles wif = new WordsInFiles();
        wif.buildWordFileMap();
        System.out.println("tree");
        wif.printFilesIn("tree");
        
        // maxNumber = wif.getMaxNumber();
        // System.out.println("5 times  ");
        // wordsInNumFiles = wif.getWordsInNumFiles(4);
        // int num=1;
        // for (String word : wordsInNumFiles) {
            // System.out.println("\"" + word + "\" appears in the files: ");
            // wif.printFilesIn(word);
            // System.out.println("\n");
            // num++;
        // }
        // System.out.println(num + "words");
    
    }
    
    public void tester() {
        WordsInFiles wif = new WordsInFiles();
        wif.buildWordFileMap();
        maxNumber = wif.getMaxNumber();
        System.out.println("The greatest number of files a word appears in is " + maxNumber);
        System.out.println("words that are in 4 number of files");
        wordsInNumFiles = wif.getWordsInNumFiles(4);
        int num=0;
        for (String word : wordsInNumFiles) {
            System.out.println("\"" + word + "\" appears in the files: ");
            wif.printFilesIn(word);
            System.out.println("\n");
            num++;
        }
        System.out.println(num);
        // System.out.println("\nThe complete map: ");
        // for (String word : wif.wordsInFilesMap.keySet()) {
            // System.out.println("\n" + word);
            // wif.printFilesIn(word);
        // }        
    } 
    
    private void printFilesIn(String word) {
        ArrayList<String> fileNames = wordsInFilesMap.get(word);
        for (int k = 0; k<fileNames.size(); k++) {
            System.out.print(fileNames.get(k) + " ");
        }  
    }
    
    private ArrayList getWordsInNumFiles(int number) {
        wordsInNumFiles = new ArrayList<String>();
        for (String word : wordsInFilesMap.keySet()) {
            ArrayList<String> wordWithFiles = wordsInFilesMap.get(word);
            int currentNumber = wordWithFiles.size();
            if (currentNumber == number) {
                wordsInNumFiles.add(word);                
            }            
        }
        return wordsInNumFiles;                
    } 
    
    private int getMaxNumber() {
        maxNumber = 0;
        
        for (String word : wordsInFilesMap.keySet()) {
            ArrayList<String> wordWithFiles = wordsInFilesMap.get(word);
            int currentNumber = wordWithFiles.size();
            if (maxNumber < currentNumber) {
                maxNumber = currentNumber;                
            }
        }
        return maxNumber;
    }
    
    private void buildWordFileMap() {
        wordsInFilesMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    private void addWordsFromFile(File f) {
        // add all the words from f into the map
        FileResource fr = new FileResource(f);
        String filename = f.getName();
        for (String word : fr.words()) {
            if (wordsInFilesMap.containsKey(word)) {
                ArrayList<String> wordWithFiles = wordsInFilesMap.get(word);
                if (!wordWithFiles.contains(filename)) {
                    wordWithFiles.add(filename);
                    wordsInFilesMap.put(word, wordWithFiles); 
                }
            } else {
                ArrayList<String> wordWithFiles = new ArrayList<String>();
                wordWithFiles.add(f.getName());
                wordsInFilesMap.put(word, wordWithFiles);
            }      
        }
    
    }
}
