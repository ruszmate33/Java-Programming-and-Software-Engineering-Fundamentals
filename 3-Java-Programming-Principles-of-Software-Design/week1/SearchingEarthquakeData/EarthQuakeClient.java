import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        //String source ="data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        String where = "any";
        String phrase = "Can";
        ArrayList<QuakeEntry> filtered = filterByPhrase(list, where, phrase);
        for (QuakeEntry qe:filtered) {
            System.out.println(qe);
        }
        System.out.println("Found "+ filtered.size() + " quakes that match "+ phrase +" at " + where);
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> filtered = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            String title = qe.getInfo();
            int phraseLength = phrase.length();
            if (where.equals("start")) {
                title = title.substring(0, phrase.length());
            } else if (where.equals("end")) {
                title = title.substring(title.length() - phrase.length(), title.length());
            }
            if (title.contains(phrase)) {
                    filtered.add(qe);                
            }

        }
        return filtered;
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        //String source ="data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        double min = -4000.0;
        double max = -2000.0;
        System.out.println("Find quakes with depth between "+ min +" and " +max);
        ArrayList<QuakeEntry> filtered = filterByDepth(list, min, max);
        
        for (QuakeEntry qe:filtered) {
            System.out.println(qe);
        }
        System.out.println("Found "+ filtered.size() + " quakes that match that criteria");
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> filtered = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            double depth = qe.getDepth();
            if (depth > minDepth && depth < maxDepth) {
                filtered.add(qe);
            }
        }
        return filtered;
    }
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            double magnitude = qe.getMagnitude();
            if (magnitude > magMin) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            //double distance = from.distanceTo(qe.getLocation());
            double distance = qe.getLocation().distanceTo(from)/1000;
            if (distance < distMax) {
                answer.add(qe);
            }
        }

        return answer;
    }
    

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        String source ="data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> bigOnes = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe:bigOnes) {
            System.out.println(qe);
        }
        System.out.println("Found "+ bigOnes.size() + " quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> closeQuakes = filterByDistanceFrom(list, 1000, city);
        
        for (QuakeEntry qe : closeQuakes) {
            double dist = qe.getLocation().distanceTo(city)/1000;
            System.out.println(dist +" "+ qe.getInfo());   
        }
        System.out.println("Found "+ closeQuakes.size() + " quakes that match that criteria");
        System.out.println("closeToMe finished");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
