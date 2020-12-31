
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        int firstPosition = stringb.indexOf(stringa);
        if (firstPosition != -1) {
            int secondPosition = stringb.indexOf(stringa, firstPosition+1);
            if (secondPosition != -1) {
                return true;
            }
        };
        return false;
    }
    public void testing() {
            System.out.println("a"+" "+"banana"+":"+twoOccurrences("a", "banana"));
            System.out.println("by"+" "+"A story by Abby Long"+":"+twoOccurrences("by", "A story by Abby Long"));
            System.out.println("atg"+" "+"ctgtatgta"+":"+twoOccurrences("atg", "ctgtatgta"));            
    }
    public String lastPart(String stringa, String stringb) {
        int firstPosition = stringb.indexOf(stringa);
        if (firstPosition != -1) {
            return stringb.substring(firstPosition+stringa.length());
        }
        return stringb;
    }
    public void testingLastPart() {
        System.out.println("an, " + "banana, " + lastPart("an", "banana"));
        System.out.println("zoo, " + "forest, " + lastPart("zoo", "forest"));
    }
}
