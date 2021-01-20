
/**
 * Write a description of class Rater here.
 *
 * @graceljy
 * @1
 */

import java.util.*;

public class EfficientRater implements Rater{

    private String myID;
    private HashMap<String,Rating> myRatings;
    // Rating (String anItem, double aValue)

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String movieID, double ratingvalue) {
        if (!myRatings.containsKey(movieID)) {
            Rating newRating = new Rating(movieID,ratingvalue);
            myRatings.put(movieID, newRating);
        }
    }

    public boolean hasRating(String movieID) {
        if (myRatings.containsKey(movieID)) {
            return true;
        }
        return false;
    }

    public String getID() {
        return myID;
    }


    public HashMap<String,Rating> getRatings() {
        return myRatings;
    }


    public double getRating(String movieID) {
        if (myRatings.containsKey(movieID)) {
            return myRatings.get(movieID).getValue();
        }
        return -1;
    }


    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String movieID : myRatings.keySet()){
            list.add(movieID);
        }
        return list;
    }

    public void printRatingHash() {
        System.out.println("RaterID: " + myID);
        // HashMap<String,Rating> myRatings;
        for (String movieID : myRatings.keySet()) {
            System.out.println("---- movieID: " + movieID + ", " + " Rating: " + myRatings.get(movieID));
        }
    }


}
