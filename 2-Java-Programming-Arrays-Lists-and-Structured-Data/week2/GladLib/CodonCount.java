import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String,Integer> codonCountMap;
    
    public CodonCount() {
        codonCountMap = new HashMap<String,Integer>();
    }
    
    public void tester() {
        codonCountMap.clear();
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.trim();
        dna = dna.toUpperCase();
        for (int readingFrame=0; readingFrame < 3; readingFrame++) {
            buildCodonMap(readingFrame, dna);
            System.out.println("Reading frame starting with "+ readingFrame +" results in " + codonCountMap.size() +" unique codons");
            System.out.println(" and most common codon is " + getMostCommonCodon() + "with " + codonCountMap.get(getMostCommonCodon()));
            System.out.println("Counts of codons between" + 7 +  " and " +7+ "inclusive are:");
            printCodonCounts(7, 7);
        }
    }
    
    private void printCodonCounts(int start, int end) {
        for (String codon : codonCountMap.keySet()) {
            int currentCount = codonCountMap.get(codon);
            if (currentCount >= start && end >= currentCount) {
                System.out.println(codon + "\t" + currentCount);
            }
        }
    }
    
    private String getMostCommonCodon() {
        String mostCommon = "";
        int max = 0;
        for (String codon : codonCountMap.keySet()) {
            int currentCount = codonCountMap.get(codon);
            if (currentCount > max) {
                max = currentCount;
                mostCommon = codon;
            }
        }
        return mostCommon;
    }
    
    public void testBuildCodonMap() {
        buildCodonMap(0, "CGTTCAAGTTCAA");
        for (String codon : codonCountMap.keySet()) {
            System.out.println("readingframe 0");
            System.out.println(codon + "\t" + codonCountMap.get(codon));
        }
        
    }
    
    
    private void buildCodonMap(int start, String dna) {
        codonCountMap.clear();
        for (int k = start; k+3 < dna.length(); k=k+3) {
            String codon = dna.substring(k, k+3);
            if (codonCountMap.containsKey(codon)) {
                codonCountMap.put(codon, codonCountMap.get(codon)+1);
            } else {
                codonCountMap.put(codon, 1);
            }
        }
    }
        
    }


