
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String where;
    private String phrase;
    private String filterName;
    
    public PhraseFilter(String whr, String phr, String name) {
        where = whr;
        phrase = phr;
        filterName = name;
    }
    
    public String getName() {
        return filterName;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        int phraseLength = phrase.length();
        int titleLength = title.length();
        
        if (where.equals("start")) {
            title = title.substring(0, phraseLength);
        } else if (where.equals("end")) {
            title = title.substring(titleLength - phraseLength, titleLength);
        }
        return (title.contains(phrase));
    }
        
    
}
