
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location location;
    private double maxDistance;
    private String filterName;
    
    public DistanceFilter(Location loc, double maxDist, String name) {
        location = loc;
        maxDistance = maxDist;
        filterName = name;
    }
    
    public String getName() {
        return filterName;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getLocation().distanceTo(location) < maxDistance);
    }

}
