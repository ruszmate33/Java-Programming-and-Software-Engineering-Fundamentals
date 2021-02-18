import java.util.*;

public class WordGramTester {
    public void testWordGram(){
        String source = "this is a test this is a test this is a test of words";
        String[] words = source.split("\\s+");
        int size = 4;
        for(int index = 0; index <= words.length - size; index += 1) {
            WordGram wg = new WordGram(words,index,size);
            System.out.println(index+"\t"+wg.length()+"\t"+wg);
        }
    }
    
    public void testWordGramEquals(){
        String source = "this is a test this is a test this is a test of words";
        String[] words = source.split("\\s+");
        ArrayList<WordGram> list = new ArrayList<WordGram>();
        int size = 4;
        for(int index = 0; index <= words.length - size; index += 1) {
            WordGram wg = new WordGram(words,index,size);
            list.add(wg);
        }
        WordGram first = list.get(0);
        System.out.println("checking "+first);
        for(int k=0; k < list.size(); k++){
            //if (first == list.get(k)) {
              if (first.equals(list.get(k))) {
                System.out.println("matched at "+k+" "+list.get(k));
            }
        }
    }
    
    public void testWordGramGetFollows(){
        String source = "this is a test this is a test this is a test of words";
        System.out.println("source: " + source);
	String[] words = source.split("\\s+");
	int order = 1;
	MarkovWord markovWord = new MarkovWord(1);
	markovWord.setTraining(source); 
        markovWord.setRandom(42);
        for (int k=0; k<words.length-order; k++) {
            WordGram key = new WordGram(words, k, order);
            System.out.println("key " + key);
            ArrayList<String> follows = markovWord.getFollows(key);
            System.out.println("follows: "+follows);
        }
    }
    
    public void testIndexOf(){
        String source = "this is a test this is a test this is a test of words";
        System.out.println("source: " + source);
	String[] words = source.split("\\s+");
	int order = 1;
	MarkovWord markovWord = new MarkovWord(2);
	markovWord.setTraining(source); 
        markovWord.setRandom(42);
        WordGram target = new WordGram(words, 0, 2);
        System.out.println("target: " + target);
        int index=0;
        while (index < words.length-2) {
            index = markovWord.indexOf(words, target, index);
            if (index ==-1) {
                break;
            }
            System.out.println("index: " + index);
            index++;
        }
    }
}
