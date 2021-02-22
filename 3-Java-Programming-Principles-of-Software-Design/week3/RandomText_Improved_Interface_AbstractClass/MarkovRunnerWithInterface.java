
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void testHashMap() {
        EfficientMarkovModel effMarkov = new EfficientMarkovModel(5);
        int seed = 531;
        // String st ="yes-this-is-a-thin-pretty-pink-thistle";
        
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        int size = 10;
        runModel(effMarkov, st, size, seed);
    }
    
    public void compareMethods() {
        EfficientMarkovModel effMarkov = new EfficientMarkovModel(2);
        MarkovModel markov = new MarkovModel(2);
        int seed = 42;
        
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        int size = 1000;
        long start = System.nanoTime();
        runModel(effMarkov, st, size, seed);
        long finish = System.nanoTime();
        System.out.println("efficient markov: " + (finish-start)/1000000 + " milliseconds");
        
        start = System.nanoTime();
        runModel(markov, st, size, seed);
        finish = System.nanoTime();
        System.out.println("markov: " + (finish-start)/1000000 + " seconds");
    }
    
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 1; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov(int seed) {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

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
