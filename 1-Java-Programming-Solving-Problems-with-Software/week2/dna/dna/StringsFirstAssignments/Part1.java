
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        String startCodon = "ATG";
        String stopCodon = "TAA";
        int startPosition = dna.indexOf(startCodon);
        if (startPosition == -1) {
            return "";
        }
        int stopPosition = dna.indexOf(stopCodon, startPosition+3);
        if ((stopPosition == -1) || ((stopPosition - startPosition) % 3 != 0)) {
            return "";
        }
        String gene = dna.substring(startPosition, stopPosition+3);
        return gene;
    }
    public void testSimpleGene() {
        String dna1 = "ATGCCCAAATTTGGGTAA";
        String dna2 = "ATGCCCAAATTTGGTAA";
        String dna3 = "ATGCCCAAATTTGGG";
        String dna4 = "CCCAAATTTGGGTAA";
        String dna5 = "CCCAAATTTGGG";
        String dnas[] = {dna1, dna2, dna3, dna4, dna5};
        for (String dna : dnas) {
            String gene = findSimpleGene(dna);
            System.out.println(gene);
        } 
    }
   
}
