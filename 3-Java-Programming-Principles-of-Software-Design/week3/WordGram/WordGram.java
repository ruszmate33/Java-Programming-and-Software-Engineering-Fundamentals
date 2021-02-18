import java.util.*;


public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for (int k=0; k<myWords.length; k++) {
            ret += myWords[k] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (other.length() != this.length()) {
            return false;
        }
        for (int k=0; k<myWords.length; k++) {
            if (!other.wordAt(k).equals(this.wordAt(k))) {
                return false;
            }
        }
        return true;

    }

    public WordGram shiftAdd(String word) { 
        String[] shiftedWords = new String[myWords.length];
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        for (int k=0; k<myWords.length-1; k++) {
            shiftedWords[k] = myWords[k+1];
        }
        shiftedWords[myWords.length-1] = word;
        WordGram out = new WordGram(shiftedWords, 0, myWords.length);
        return out;
    }

}