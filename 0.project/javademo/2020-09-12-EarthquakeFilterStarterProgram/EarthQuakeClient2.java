
/**
 *
 *
 * @Grace Jyl
 * @version 1
 */


import java.util.*;
import lib.edu.duke.*;

public class EarthQuakeClient2 {

    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }


    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            if (f.satisfies(qe)) {
                answer.add(qe);
                // System.out.println("pass");
            }
        }
        return answer;
    }


    public void quakesWithFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> quakeData  = parser.read(source);
        System.out.println("read data for " + quakeData.size() + " quakes");

        // Filter f = new MinMagFilter(4.0);
        // ArrayList<QuakeEntry> m7  = filter(quakeData, f);
        // for (QuakeEntry qe: m7) {
        //     System.out.println(qe);
        // }

        // ---------------------------------------------------
        // to filter earthquakes using two criteria,
        // those with magnitude between 4.0 and 5.0
        // and depth between -35000.0 and -12000.0
        // ---------------------------------------------------
        // Filter f1 = new FilterMagnitude(4.0, 5.0);
        // Filter f2 = new FilterDepth(-35000.0, -12000.0);
        // ArrayList<QuakeEntry> output1  = filter(quakeData, f1);
        // ArrayList<QuakeEntry> output2  = filter(output1, f2);
        // System.out.println("after FilterDepth(), the total quakes is: " + output2.size());
        // for (QuakeEntry qe: output2) {
        //     System.out.println(qe);
        // }

        // ---------------------------------------------------
        // to filter earthquakes using two criteria,
        // those that are 10,000,000 meters (10,000 km) from Tokyo, Japan (35.42, 139.43),
        // and that are in Japan (this means "Japan" should be the last word in the title)
        // ---------------------------------------------------
        // Filter f1 = new FilterDistance(35.42, 139.43, 10000000);
        // Filter f2 = new FilterPhrase("end", "Japan");
        // ArrayList<QuakeEntry> output1  = filter(quakeData, f1);
        // ArrayList<QuakeEntry> output2  = filter(output1, f2);
        // System.out.println("after FilterDepth(), the total quakes is: " + output2.size());
        // for (QuakeEntry qe: output2) {
        //     System.out.println(qe);
        // }
    }



    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> quakeData  = parser.read(source);
        System.out.println("read data for " + quakeData.size() + " quakes");

        int count = 0;
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new FilterMagnitude(0.0, 2.0);
        Filter f2 = new FilterDepth(-100000.0, -10000.0);
        Filter f3 = new FilterPhrase("any", "a");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        // for(Filter f : matchAllList)
        ArrayList<QuakeEntry> quakes = filter(quakeData, maf);

        for(QuakeEntry qe : quakes){
            System.out.println(qe);
            count++;
        }
        System.out.println("count = " + count);
        // System.out.println("Filters used are: " + maf.getName() + " and count = " + count);
    }

    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> quakeData  = parser.read(source);
        System.out.println("read data for " + quakeData.size() + " quakes");

        int count = 0;
        MatchAllFilter maf = new MatchAllFilter();
        
        // Filter f1 = new FilterMagnitude(0.0, 3.0);
        // Filter f2 = new FilterDistance(36.1314, -95.9372, 10000000);
        // Filter f3 = new FilterPhrase("any", "Ca");
        // maf.addFilter(f1);
        // maf.addFilter(f2);
        // maf.addFilter(f3);
        // ArrayList<QuakeEntry> quakes = filter(quakeData, maf);


        // quiz
        // Filter f2 = new FilterDistance(39.7392, -104.9903, 1000000);
        // Filter f3 = new FilterPhrase("end", "a");
        // maf.addFilter(f2);
        // maf.addFilter(f3);
        // ArrayList<QuakeEntry> quakes = filter(quakeData, maf);

        // Filter f1 = new FilterMagnitude(3.5, 4.5);
        // Filter f2 = new FilterDepth(-55000.0, -20000.0);
        // maf.addFilter(f1);
        // maf.addFilter(f2);
        // ArrayList<QuakeEntry> quakes = filter(quakeData, maf);


        // Filter f1 = new FilterMagnitude(1.0, 4.0);
        // Filter f2 = new FilterDepth(-180000.0, -30000.0);
        // Filter f3 = new FilterPhrase("any", "o");
        // maf.addFilter(f1);
        // maf.addFilter(f2);
        // maf.addFilter(f3);
        // ArrayList<QuakeEntry> quakes = filter(quakeData, maf);

        
        Filter f1 = new FilterMagnitude(0.0, 5.0);
        Filter f2 = new FilterDistance(55.7308, 9.1153, 3000000);
        Filter f3 = new FilterPhrase("any", "e");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> quakes = filter(quakeData, maf);

        for(QuakeEntry qe : quakes){
            System.out.println(qe);
            count++;
        }
        System.out.println("count = " + count);
        System.out.println("Filters used are: " + maf.getFilterName() + " and count = " + count);
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> quakeData  = parser.read(source);
        dumpCSV(quakeData);
        System.out.println("# quakes read: "+quakeData.size());
    }


    public void dumpCSV(ArrayList<QuakeEntry> quakeData) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : quakeData){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }


    public static void main(String[] args) {
        EarthQuakeClient2 pr = new EarthQuakeClient2();
        pr.quakesWithFilter();
    }

}
