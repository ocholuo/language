/**
 * Find N-closest quakes
 * 
 * @Grace JyL
 * @version 1.0, Sep 11 2015
 */


import java.util.*;


public class LargestQuakes {
    
    public ArrayList<QuakeEntry> findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> quakeData  = parser.read(source);

        ArrayList<QuakeEntry> answer = getLargest(quakeData, 5);

        for(QuakeEntry qe : answer){
            System.out.println(qe);
        }
        System.out.println("total number of earthquakes read in: " + answer.size());
        return answer;
    }


    public int indexOfLargest(ArrayList<QuakeEntry> quakeData) {
        int largestIndex = 0;
        double largestMagnitude = 0;
        for(int i = 0; i < quakeData.size(); i++){
            QuakeEntry currQe = quakeData.get(i);
            double currMagnitude = currQe.getMagnitude();
            if(largestMagnitude < currMagnitude){
                largestMagnitude = currMagnitude;
                largestIndex = i;
            }
        }
        System.out.println("the largest such earthquake is at location " + largestIndex + " and has magnitude " + largestMagnitude);
        return largestIndex;
    }


    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(int i = 0; i < howMany; i++){
            int largestIndex = indexOfLargest(copy);
            answer.add(copy.get(largestIndex));
            copy.remove(largestIndex);
        }
        return answer;
    }



    // public static void main(String[] args) {
    //     LargestQuakes pr = new LargestQuakes();

    //     ArrayList<QuakeEntry> quakeData = pr.findLargestQuakes();
    //     // int largestIndex = pr.indexOfLargest(quakeData);
    //     ArrayList<QuakeEntry> answer = pr.getLargest(quakeData, 5);

    // }


}
