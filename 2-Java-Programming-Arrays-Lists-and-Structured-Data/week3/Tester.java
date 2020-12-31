
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testiPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> daysToIPs = la.iPsForDays();
        ArrayList<String> iPsWithMostOnDay = la.iPsWithMostVisitsOnDay(daysToIPs,"Sep 29");
        System.out.println(iPsWithMostOnDay);
    }
    
    public void testdayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> daysToIPs = la.iPsForDays();
        String dayWithMost = la.dayWithMostIPVisits(daysToIPs);
        System.out.println(dayWithMost);
    }
    
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> daysToIPs = la.iPsForDays();
        System.out.println(daysToIPs);
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> iPsWithMost = la.iPsMostVisits(counts);
        System.out.println(iPsWithMost);
    }
    
    public void testmostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        int max = la.mostNumberVisitsByIP(counts);
        System.out.println(max);
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int count = la.countUniqueIPsInRange(200,299);
        System.out.println(count);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.uniqueIPVisitsOnDay("Sep 24");
        //la.uniqueIPVisitsOnDay("Sep 30");
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAll();
        int numUniqueIPs = la.countUniqueIPs();
        System.out.println("Num unique IP adresses: " + numUniqueIPs);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("short-test_log.txt");
        logAnalyzer.printAll();
    }
}
