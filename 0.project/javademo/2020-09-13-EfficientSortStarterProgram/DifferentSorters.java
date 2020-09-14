
/**
 * Write a description of class DifferentSorters here.
 * 
 * @Grace Jyl
 * @version 1
 */

import java.util.*;

public class DifferentSorters {

    public void sortWithCompareTo() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);

        Collections.sort(list);

        // for(QuakeEntry qe: list) {
        //     System.out.println(qe);
        // }

        int quakeNumber = 50;
        System.out.println("sortWithCompareTo():");
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));

    }    

    public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new MagnitudeComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }

    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }

    public void sortByTitleAndDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);

        Collections.sort(list, new TitleAndDepthComparator());
        System.out.println("sortByTitleAndDepth():");
        int quakeNumber = 10;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }


    public void namsortByLastWordInTitleThenByMagnitudee() {    
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);

        Collections.sort(list, new TitleLastAndMagnitudeComparator());
        System.out.println("TitleLastAndMagnitudeComparator():");
        int quakeNumber = 10;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }
}
