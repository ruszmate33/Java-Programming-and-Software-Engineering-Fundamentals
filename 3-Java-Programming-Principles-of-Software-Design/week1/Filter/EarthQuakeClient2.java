import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }
    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 5.0, "magn"));
        Location billund = new Location(55.7308, 9.1153);
        maf.addFilter(new DistanceFilter(billund, 3000000, "dist"));
        maf.addFilter(new PhraseFilter("any", "e", "phrase"));
        
        // maf.addFilter(new DepthFilter(-55000.0, -20000.0, "depth"));
        
        
        // Location tulsa = new Location(36.1314, -95.9372);
        // maf.addFilter(new DistanceFilter(tulsa, 10000000, "dist"));
        // maf.addFilter(new PhraseFilter("any", "Ca", "phrase"));
        
        ArrayList<QuakeEntry> results = filter(list, maf);
        // for (QuakeEntry qe:results) { 
            // System.out.println(qe);
        // }
        System.out.println("Found: " + results.size());
    }
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 2.0, "Magnitude"));
        maf.addFilter(new DepthFilter(-100000.0, -10000.0, "Depth"));
        maf.addFilter(new PhraseFilter("any", "a", "Phrase"));
        
        ArrayList<QuakeEntry> results = filter(list, maf);
        for (QuakeEntry qe:results) { 
            System.out.println(qe);
        }
        System.out.println("Filters used are: " + maf.getName());
        System.out.println("Found: " + results.size());
    } 

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        // Filter f = new MinMagFilter(4.0);
        Filter f2 = new MagnitudeFilter(3.5, 4.5, "magnitude");
        Filter f3 = new DepthFilter(-55000.0, -20000.0, "depth");
        //ArrayList<QuakeEntry> m7  = filter(list, f);
        ArrayList<QuakeEntry> m7  = filter(list, f2);
        m7  = filter(m7, f3);
        
        
        // Location denver = new Location(39.7392, -104.9903);
        // Filter f = new DistanceFilter(denver, 1000000, "distance");
        // Filter f2 = new PhraseFilter("end", "a", "phrase");
        // ArrayList<QuakeEntry> m7  = filter(list, f);
        // m7  = filter(m7, f2);
        
        // Location tokyo = new Location(35.42, 139.43);
        // Filter f = new DistanceFilter(tokyo, 10000000, "distance");
        // Filter f2 = new PhraseFilter("end", "Japan", "phrase");
        // ArrayList<QuakeEntry> m7  = filter(list, f);
        // m7  = filter(m7, f2);
        
        System.out.println(m7.size());
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
