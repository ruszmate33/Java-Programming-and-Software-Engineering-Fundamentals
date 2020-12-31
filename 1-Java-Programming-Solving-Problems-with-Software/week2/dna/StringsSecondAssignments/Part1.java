
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public void testFindGene() {
        if (findGene("ATGXXXYYYZZZTAA", 0) != "ATGXXXYYYZZZTAA") {
            System.out.println("ATGXXXYYYZZZTAA, basic case issue");
        }
        if (findGene("XXXYYYZZZTAA", 0) != "") {
            System.out.println("XXXYYYZZZTAA");
        }
        //                                        ATGXXXYYYZZZTAA
        if (findGene("ATGXXXYYYZZZTAATGATAG",0) != "ATGXXXYYYZZZTAA") {
            String gene = findGene("ATGXXXYYYZZZTAATGATAG", 0);
            System.out.println(gene+" ATGXXXYYYZZZTAATGATAG, early stop codon issue");
        }
        if (findGene("ATGXXXYYYZZZ", 0) != "") {
            System.out.println("ATGXXXYYYZZZ, no stop codon issue");
        }
        if (findGene("ATGXXXYYYZZZ0TAA0TGA0TAG", 0) != "ATGXXXYYYZZZ0TAA0TGA0TAG") {
            System.out.println("ATGXXXYYYZZZ0TAA0TGA0TAG");
        }
        System.out.println("tests are through");
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String gene = findGene(dna, startIndex);
            if (gene == "") {
                break;
            } else {
                System.out.println(gene);
                startIndex = dna.indexOf(gene, startIndex) + gene.length();
            }
        }
    }
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        // get the min
        int stopIndex = Math.min(Math.min(taaIndex, tagIndex), tgaIndex);
        if (stopIndex == dna.length()) {
            return "";
        } else {
            return dna.substring(startIndex, stopIndex+3);
        }
    }
    
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        // returns the index of the first occurrence of stopCodon
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        //that appears past startIndex and is a multiple of 3 away from startIndex
        if (stopIndex != 1 && ((stopIndex - startIndex) % 3 == 0)) {
            return stopIndex;
        } else {
        // no such stopCodon, this method returns the length of the dna strand
            return dna.length();
        }
    }
    public void testFindStopCodon() {
        //                             0123456789
        int stopIndex = findStopCodon("XXXYYYZZZTAA", 0, "TAA");
        if (stopIndex != 9) {
            System.out.println("stopIndex: " + stopIndex);
        } else {
            System.out.println("fine!");
        }
        //                         0123456789
        stopIndex = findStopCodon("XXXYYYZZTAA", 0, "TAA");
        if (stopIndex != "XXXYYYZZTAA".length()) {
            System.out.println("stopIndex: " + stopIndex);
        } else {
            System.out.println("fine!");
        }
        //                         0123456789
        stopIndex = findStopCodon("", 0, "TAA");
        if (stopIndex != "".length()) {
            System.out.println("stopIndex: " + stopIndex);
        } else {
            System.out.println("fine!");
        }
        //                         0123456789
        stopIndex = findStopCodon("XXSSSYYYTAA", 2, "TAA");
        if (stopIndex != 8) {
            System.out.println("stopIndex: " + stopIndex);
        } else {
            System.out.println("fine!");
        }
    }
}
