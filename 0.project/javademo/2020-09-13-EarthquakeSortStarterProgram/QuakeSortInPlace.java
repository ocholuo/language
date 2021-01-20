
/**
 * QuakeSortInPlace
 * 
 * @Grace Jyl
 * @version 1
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {

    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   

    // get minIndex the has minValue in ArrayList<QuakeEntry> quakes 
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIndex = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIndex).getMagnitude()) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    // for each i index, find the smallest put it to i index
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       for (int i=0; i< in.size(); i++) {
            int minIndex = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry minValue = in.get(minIndex);
            in.set(i,minValue);
            in.set(minIndex,qi);
        }
    }

    // This method returns an integer representing the index position of the QuakeEntry with the largest depth considering only those QuakeEntry’s from position from to the end of the ArrayList.
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int indexLargestDep = from;
        Double largestDep = quakeData.get(from).getDepth();
        for(int i = from; i < quakeData.size(); i++){
            Double currDep = quakeData.get(i).getDepth();
            // System.out.println(currDep);
            if(largestDep > currDep){
                // System.out.println(largestDep + "<" + currDep);
                indexLargestDep = i;
                largestDep = currDep;
            }
        }
        // System.out.println("getLargestDepth() -> LargestDep is: " + largestDep + " index: " + indexLargestDep);
        // System.out.println("getLargestDepth() finished ");
        return indexLargestDep;
    }

    // 0, maxindex, in.set[0, max]
    // 1, maxindex, in.set[1, max]
    // .....
    // This method sorts the QuakeEntry’s in the ArrayList by depth using the selection sort algorithm, but in reverse order from largest depth to smallest depth (the QuakeEntry with the largest depth should be in the 0th position in the ArrayList). 
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        // for(int i = 0; i < in.size(); i++){
        for(int i = 0; i < 50; i++){
            int indexLargestDep = getLargestDepth(in, i);
            QuakeEntry currQe = in.get(i);
            QuakeEntry largestQe = in.get(indexLargestDep);
            in.set(i, largestQe);
            in.set(indexLargestDep,currQe);
            System.out.println(i + " " + in.get(i));
            // System.out.println(indexLargestDep + " " + in.get(indexLargestDep));     
        }
    }


    //   bubblesort
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        QuakeEntry temp;
        for(int i = 0; i < quakeData.size() - 1 - numSorted; i++){
            QuakeEntry currQe = quakeData.get(i);
            QuakeEntry nextQe = quakeData.get(i+1);
            if(currQe.getMagnitude() > nextQe.getMagnitude()){
                quakeData.set(i, nextQe);
                quakeData.set(i+1, currQe);
            }
        }
    }
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> quakeData) {
        for (int i = 0; i < quakeData.size(); i++) {
            onePassBubbleSort(quakeData, i);
        }
    }


    // This method returns true if the earthquakes are in sorted order by magnitude from smallest to largest. 
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakeData) {
        for(int i = 0; i < quakeData.size()-2; i++){
            if( quakeData.get(i).getMagnitude() > quakeData.get(i+1).getMagnitude() ){
                return false;
            }
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> quakeData) {
        for (int i = 0; i < quakeData.size(); i++) {
            if( checkInSortedOrder(quakeData) ){
                System.out.println(i + " passes were needed to sort the elements.");
                break;
            }
            onePassBubbleSort(quakeData, i);
        }
    }


    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> quakeData) {
        for (int i=0; i< quakeData.size(); i++) {
            if( checkInSortedOrder(quakeData) ){
                System.out.println(i + " passes were needed to sort the elements.");
                break;
            }
            int minIndex = getSmallestMagnitude(quakeData,i);
            QuakeEntry qi = quakeData.get(i);
            QuakeEntry minValue = quakeData.get(minIndex);
            quakeData.set(i,minValue);
            quakeData.set(minIndex,qi);
        }
    }


    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
        System.out.println("read data for "+list.size()+" quakes");    

        // sortByMagnitude(list);
        // sortByLargestDepth(list);
        // sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        // sortByMagnitudeWithCheck(list);
        // for (QuakeEntry qe: list) { 
        //     System.out.println(qe);
        // }     
    }


    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
    
    public static void main(String[] args) {
        
    }
}
