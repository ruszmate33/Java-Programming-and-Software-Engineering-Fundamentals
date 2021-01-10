
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    private String name;
    
    public MatchAllFilter() {
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter filter) {
        filters.add(filter);
        name = filter.getName();
    }
    
    public String getName() {
        String name = "";
        for (Filter filter:filters) {
            name = name + " " + filter.getName();
        }
        return name;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        for (Filter filter:filters) {
            if (!filter.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }
}
