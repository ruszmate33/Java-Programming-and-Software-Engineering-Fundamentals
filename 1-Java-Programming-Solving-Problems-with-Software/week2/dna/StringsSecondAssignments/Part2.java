
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int reps = 0;
        int searchPosition = 0;
        int where = 0;
        while (stringb.indexOf(stringa, searchPosition) != -1) {
            reps++;
            int genePosition = stringb.indexOf(stringa, searchPosition);
            searchPosition = genePosition + stringa.length();
            System.out.println("reps: " + reps + ", genePosition: " + genePosition + ", searchPosition: " + searchPosition);
        }
        return reps;
    }
    public void testHowMany() {
        //                  0123456789012345
        if (howMany("GAA", "ATGAACGAATTGAATC") != 3) {
            System.out.println("test1 "+howMany("GAA", "ATGAACGAATTGAATC")); 
        }
        //                 012345
        if (howMany("AA", "ATAAAA") != 2) {
            System.out.println("test2 " + howMany("AA", "ATAAAA")); 
        }
            System.out.println("tests are done");
    }

}
