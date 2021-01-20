
/**
 * Write a description of class Rater here.
 *
 * @graceljy
 * @1
 */

import org.apache.commons.csv.*;
import java.util.*;


public interface Rater {

    public void addRating(String item, double rating);

    public boolean hasRating(String item);

    public String getID();

    public HashMap<String,Rating> getRatings();

    public double getRating(String item);

    public int numRatings();

    public ArrayList<String> getItemsRated();

    public void printRatingHash();
}
