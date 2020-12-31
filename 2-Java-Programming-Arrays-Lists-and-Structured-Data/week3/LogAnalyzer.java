
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     private ArrayList<String> uniqueIPs;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>(); 
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             LogEntry logEntry = WebLogParser.parseEntry(line);
             records.add(logEntry);
         }
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> daysToIPs, String monthDay) {
         ArrayList<String> iPsWithMostOnDay = new ArrayList<String>();
         ArrayList<String> iPsOnDay = daysToIPs.get(monthDay);
         HashMap<String,Integer> iPsVisitsOnDay = new HashMap<String,Integer>();
         for (String iP:iPsOnDay) {
             for (LogEntry le : records) {
                 Date date = le.getAccessTime();
                 String ipAddr = le.getIpAddress();
                 String day = date.toString().substring(4,10);
                 if (iP.equals(ipAddr) && monthDay.equals(day)) {
                     if (!iPsVisitsOnDay.containsKey(ipAddr)) {
                         iPsVisitsOnDay.put(ipAddr, 1);
                     } else {
                         iPsVisitsOnDay.put(ipAddr, iPsVisitsOnDay.get(ipAddr)+1);
                     }                
                 }
             }
         }
         iPsWithMostOnDay = iPsMostVisits(iPsVisitsOnDay);
         return iPsWithMostOnDay;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> daysToIPs) {
         int maxVisits = 0;
         String dayWithMost = "";
         for (String monthDay : daysToIPs.keySet()) {
             ArrayList<String> iPs = daysToIPs.get(monthDay);
             int currVisits = iPs.size();
             if (currVisits > maxVisits) {
                 maxVisits = currVisits;
                 dayWithMost = monthDay;
             }
         }
         return dayWithMost;         
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> daysToIPs = new HashMap<String, ArrayList<String>>();
         ArrayList<String> iPs;
         for (LogEntry le : records) {
             Date date = le.getAccessTime();
             String ipAddr = le.getIpAddress();
             String monthDay = date.toString().substring(4,10);
             if (!daysToIPs.containsKey(monthDay)) {
                 iPs = new ArrayList<String>();                 
             } else {
                 iPs = daysToIPs.get(monthDay);
             }
             iPs.add(ipAddr);
             daysToIPs.put(monthDay, iPs);
         }
         return daysToIPs;
         
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
         ArrayList<String> iPsWithMost = new ArrayList<String>();
         int max = mostNumberVisitsByIP(counts);
         for (String key : counts.keySet()) {
             int currValue = counts.get(key);
             if (max == currValue) {
                 iPsWithMost.add(key);
             }            
         }
         return iPsWithMost;
     }
     
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts)  {
         int max = 0;
         for (String key : counts.keySet()) {
             int currValue = counts.get(key);
             if (max < currValue) {
                 max = currValue;
             }            
         }
         return max;
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for (LogEntry le : records) {
             String ipAddr = le.getIpAddress();
             if (!counts.containsKey(ipAddr)) {
                 counts.put(ipAddr, 1);
             } else {
                 counts.put(ipAddr, counts.get(ipAddr)+1);
             }
         }
         return counts;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            String ipAddr = le.getIpAddress();
            if (statusCode >= low && statusCode <= high && !uniqueIPs.contains(ipAddr)) {
                System.out.println(le);
                uniqueIPs.add(ipAddr);
            }
         }
         return uniqueIPs.size();
     }
     
     public void uniqueIPVisitsOnDay(String someday) {      
         uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             Date date = le.getAccessTime();
             String ipAddr = le.getIpAddress();
             String monthDay = date.toString().substring(4,10);
             if (monthDay.equals(someday) && !uniqueIPs.contains(ipAddr)) {
                 System.out.println(monthDay);
                 uniqueIPs.add(ipAddr);
             }
         }
         System.out.println(uniqueIPs.size());
     }
     
     public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode > num) {
                System.out.println(le);
            }
        }
     }
     
     public int countUniqueIPs() {
         storeUniqueIPs();
         return uniqueIPs.size();
     }
     
     private void storeUniqueIPs() {
         uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String ipAddr = le.getIpAddress();
             if (!uniqueIPs.contains(ipAddr)) {
                 uniqueIPs.add(ipAddr);
             }
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
