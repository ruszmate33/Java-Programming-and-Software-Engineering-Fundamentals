
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part4 {
    public void printLinks() {
    URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String originalString : ur.words()) {
            System.out.println(originalString);
        }
    }
    
    
    
    // read URLResource word by word
    public void printYouTubeLinks() {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String originalString : ur.words()) {
            String lowerCaseString = originalString.toLowerCase();
            // is youtube in it?
            boolean isYoutubeLink = (lowerCaseString.indexOf("youtube.com") != -1);
            if (isYoutubeLink) {
                // find the double quote to the left and right
                int firstPosition = lowerCaseString.indexOf("\"");
                int lastPosition = lowerCaseString.lastIndexOf("\"");
                String youtubeLink = originalString.substring(firstPosition, lastPosition);
                System.out.println(youtubeLink);
            }
        }
    }
}
