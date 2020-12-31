
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    private static boolean isStringLowerCase(String str){
        // https://www.javacodeexamples.com/check-if-string-is-lowercase-in-java-example/620
        
        //convert String to char array
        char[] charArray = str.toCharArray();
        
        for(int i=0; i < charArray.length; i++){
            
            //if any character is not in lower case, return false
            if( !Character.isLowerCase( charArray[i] ))
                return false;
        }
        return true;
    }
    
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        if (isStringLowerCase(dna)) {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        
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
        String dna6 = "ATGTAA";
        String dna7 = "atgtaa";
        
        String dnas[] = {dna1, dna2, dna3, dna4, dna5, dna6, dna7};
        String startCodon = "ATG";
        String stopCodon = "TAA";
        for (String dna : dnas) {
            String gene = findSimpleGene(dna, startCodon, stopCodon);
            System.out.println(gene);
        } 
    }

}
