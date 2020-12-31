import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList> myMap;
    private ArrayList<String> usedList;
    private ArrayList<String> usedLabels;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String,ArrayList>();
        usedLabels = new ArrayList<String>(); 
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
        myMap = new HashMap<String,ArrayList>();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"country", "noun", "animal", "adjective", "name", "color", "timeframe", "fruit", "verb"};
        
        
        for (String label : labels) {
            ArrayList<String> list = readIt(source+"/"+label+".txt");
            myMap.put(label, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        String element = source.get(index);
        return element;
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (!usedLabels.contains(label)) {
            usedLabels.add(label);
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String label = w.substring(first+1,last);
        String sub = getSubstitute(label);
        while (usedList.contains(sub)) {
            sub = getSubstitute(label);         
        }
        usedList.add(sub);
        
        
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        usedList = new ArrayList<String>();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n Words replaced: " + usedList.size());
        System.out.println("Total words to choose from: "+totalWordsInMap());
        System.out.println("Total words considered: "+totalWordsConsidered());
    }
    private int countWordsInLists(ArrayList<String> keyWords) {
        int total = 0;
        for (String key : keyWords) {
            ArrayList list = myMap.get(key);
            total += list.size();
        }
        return total;
    }
    private int totalWordsInMap() {
        Set<String> keySet = myMap.keySet();
        ArrayList list = new ArrayList<String>(keySet);
        int totalWords = countWordsInLists(list);
        return totalWords;
    }
    private int totalWordsConsidered() {
        int totalWordsConsider = 0;
        totalWordsConsider = countWordsInLists(usedLabels);
        return totalWordsConsider;
    }
    
}
