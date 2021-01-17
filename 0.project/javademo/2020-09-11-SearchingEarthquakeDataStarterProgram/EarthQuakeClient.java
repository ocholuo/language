
/**
 * EarthQuakeClient
 * 
 * @Grace JyL
 * @version 1.0, Sep 11 2015
 */

import java.util.*;
import lib.edu.duke.*;

public class EarthQuakeClient {

    private EarthQuakeParser parser;
    private String source;
    private ArrayList<QuakeEntry> quakeData;


    public EarthQuakeClient() {
        parser = new EarthQuakeParser();
        source = "data/nov20quakedatasmallsmall.atom";
        // source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        quakeData  = parser.read(source);
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

    // ------------------------------------------------------------
    // EarthQuakeParser.read(source) = ArrayList<QuakeEntry> list {QuakeEntry1, QuakeEntry2, QuakeEntry3, ...}
    // dumpCSV(list) to CSV
    // ------------------------------------------------------------
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmallsmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        for(QuakeEntry qe : list){
            System.out.println(qe);
        }
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }


    // ------------------------------------------------------------
    // ArrayList<QuakeEntry> list {QuakeEntry1, QuakeEntry2, QuakeEntry3, ...}
    // for each QuakeEntry if QuakeEntry.getMagnitude() > magMin
    // return ArrayList<QuakeEntry>
    // ------------------------------------------------------------
    // This method should return an ArrayList of type QuakeEntry of all the earthquakes from quakeData that have a magnitude larger than magMin.
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }
        return answer;
    }


    // ------------------------------------------------------------
    // EarthQuakeParser.read(source) = ArrayList<QuakeEntry> list {QuakeEntry1, QuakeEntry2, QuakeEntry3, ...}
    // for each QuakeEntry return QuakeEntry.getMagnitude() > 5.0
    // ------------------------------------------------------------
    // this method reads data on earthquakes from a file, stores a QuakeEntry for each earthquake read in the ArrayList named list, and prints out the number of earthquakes read in. 
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for(QuakeEntry qe : listBig){
            System.out.println(qe);
        }
    }


    // ------------------------------------------------------------
    // ArrayList<QuakeEntry> quakeData {QuakeEntry1, QuakeEntry2, QuakeEntry3, ...}
    // call filterByDistance to calculate each QuakeEntry distance, 
    // print out the earthquakes within 1000 Kilometers to a specified city
    // ------------------------------------------------------------
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            Location loc = qe.getLocation();
            double dis = loc.distanceTo(from);
            if(dis < distMax){
                answer.add(qe);
            }
        }
        // System.out.println(answer.size() + " QuakeEntry has been founded.");
        return answer;
    }


    // ------------------------------------------------------------
    // EarthQuakeParser.read(source) = ArrayList<QuakeEntry> list {QuakeEntry1, QuakeEntry2, QuakeEntry3, ...}
    // call filterByDistance to calculate each QuakeEntry distance, 
    // print out the earthquakes within 1000 Kilometers to a specified city
    // ------------------------------------------------------------
    // For each earthquake found, print the distance from the earthquake to the specified city, followed by the information about the city (use getInfo()
    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // System.out.println("This location is Durham, NC");
        // Location city = new Location(35.988, -78.907);
        System.out.println("This location is Bridgeport, CA");
        Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> qeCloseToCity = filterByDistanceFrom(list, 1000000, city);
        for(QuakeEntry qe : qeCloseToCity){
            System.out.println(qe);
            System.out.println((qe.getLocation().distanceTo(city) / 1000) + " Kilometers, " + qe.getInfo());
        }
        System.out.println("Found " + qeCloseToCity.size() + " that match that criteria");
    }


    // ------------------------------------------------------------
    // EarthQuakeParser.read(source) = ArrayList<QuakeEntry> list {QuakeEntry1, QuakeEntry2, QuakeEntry3, ...}
    // call filterByDepth to calculate each QuakeEntry Depth
    // ------------------------------------------------------------
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> qeByDepth = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            double dep = qe.getDepth();
            if(minDepth < dep && dep < maxDepth){
                qeByDepth.add(qe);
                System.out.println(qe);
            }
        }
        return qeByDepth;
    }


    
    // ------------------------------------------------------------
    // EarthQuakeParser.read(source) = ArrayList<QuakeEntry> list {QuakeEntry1, QuakeEntry2, QuakeEntry3, ...}
    // call filterByDepth to calculate each QuakeEntry Depth, 
    // print out the earthquakes within Depth range
    // ------------------------------------------------------------
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> quakeData  = parser.read(source);
        System.out.println("read data for "+ quakeData.size()+" quakes");

        ArrayList<QuakeEntry> qeByDepth = filterByDepth(quakeData, -12000.0, -10000.0);
        for(QuakeEntry qe : qeByDepth){
            System.out.println(qe);
        }
        System.out.println("the number of earthquakes found: " + qeByDepth.size());
    }



    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> qeByPhrase = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (where.equals("start")) {
                if (qe.getInfo().startsWith(phrase)) {
                    qeByPhrase.add(qe);
                }
            } else if (where.equals("end")) {
                if (qe.getInfo().endsWith(phrase)) {
                    qeByPhrase.add(qe);
                }
            } else if (where.equals("any")) {
                if (qe.getInfo().contains(phrase)) {
                    qeByPhrase.add(qe);
                }
            }
        }
        return qeByPhrase;
    }

    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> quakeData  = parser.read(source);
        System.out.println("read data for "+ quakeData.size()+" quakes");

        // String where = "end"; 
        // String phrase = "California";
        // ArrayList<QuakeEntry> qeByPhrase = filterByPhrase(quakeData, where, phrase);
        // for(QuakeEntry qe : qeByPhrase){
        //     System.out.println(qe);
        // }
        // System.out.println("Found " + qeByPhrase.size() + " quakes that match " + phrase + " at " + where);

        
        // where = "any"; 
        // phrase = "Creek";
        // qeByPhrase = filterByPhrase(quakeData, where, phrase);
        // for(QuakeEntry qe : qeByPhrase){
        //     System.out.println(qe);
        // }
        // System.out.println("Found " + qeByPhrase.size() + " quakes that match " + phrase + " at " + where);

        
        // where = "start"; 
        // phrase = "Explosion";
        // qeByPhrase = filterByPhrase(quakeData, where, phrase);
        // for(QuakeEntry qe : qeByPhrase){
        //     System.out.println(qe);
        // }
        // System.out.println("Found " + qeByPhrase.size() + " quakes that match " + phrase + " at " + where);

        String where = "start"; 
        String phrase = "Quarry Blast";
        ArrayList<QuakeEntry> qeByPhrase = filterByPhrase(quakeData, where, phrase);
        for(QuakeEntry qe : qeByPhrase){
            System.out.println(qe);
        }
        System.out.println("Found " + qeByPhrase.size() + " quakes that match " + phrase + " at " + where);

        where = "end"; 
        phrase = "Alaska";
        qeByPhrase = filterByPhrase(quakeData, where, phrase);
        for(QuakeEntry qe : qeByPhrase){
            System.out.println(qe);
        }
        System.out.println("Found " + qeByPhrase.size() + " quakes that match " + phrase + " at " + where);

        
        where = "any"; 
        phrase = "Can";
        qeByPhrase = filterByPhrase(quakeData, where, phrase);
        for(QuakeEntry qe : qeByPhrase){
            System.out.println(qe);
        }
        System.out.println("Found " + qeByPhrase.size() + " quakes that match " + phrase + " at " + where);
    }
    

    public static void main(String[] args) {
        EarthQuakeClient pr = new EarthQuakeClient();
        
        System.out.println("quakesOfDepth()--------------");
        pr.quakesOfDepth();

        // pr.quakesByPhrase();

    }
}
