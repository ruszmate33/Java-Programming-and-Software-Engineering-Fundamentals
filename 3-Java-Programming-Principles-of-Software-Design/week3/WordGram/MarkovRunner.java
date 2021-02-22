
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(5); 
        runModel(markovWord, st, 200, 844); 
    }
    
    public void testHashMap() {
        //String st = "this is a test yes this is really a test yes a test this is wow"; 
        FileResource fr = new FileResource(); 
        String st = fr.asString();
        st = st.replace('\n', ' '); 
        EfficientMarkovWord effmarkovWord = new EfficientMarkovWord(2); 
        runModel(effmarkovWord, st, 200, 65); 
        effmarkovWord.printHashMapInfo();
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        MarkovWord markovWord = new MarkovWord(2); 
        long start = System.nanoTime();
        runModel(markovWord, st, 100, 42);
        long end = System.nanoTime();
        System.out.println("markovWord: " + (end-start)/1000000+ " milliseconds");
        
        EfficientMarkovWord effmarkovWord = new EfficientMarkovWord(2); 
        start = System.nanoTime();
        runModel(effmarkovWord, st, 100, 42);
        end = System.nanoTime();
        System.out.println("efficient markovWord: " + (end-start)/1000000 + " milliseconds");
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    }

}
