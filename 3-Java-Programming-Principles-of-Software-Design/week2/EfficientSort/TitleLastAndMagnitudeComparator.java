
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String lastWord1 = getLastWord(q1);
        String lastWord2 = getLastWord(q2);
        int diffLastW = lastWord1.compareTo(lastWord2);
        if (diffLastW == 0) {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return diffLastW;
    }
    public String getLastWord(QuakeEntry qe) {
        String[] words = qe.getInfo().split(" ");
        String lastWord = words[words.length-1];
        return lastWord;
    }
}
