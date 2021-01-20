/**
 * Write a description of SecondRatings here.
 *
 * @graceljy
 * @1
 */


import java.security.Principal;
import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class ThirdRatings {

    // ThirdRatings has only one private variable named myRaters to store an ArrayList of Raters.
    private ArrayList<Rater> myRaters;

    // default constructor
    public ThirdRatings() {
        this("ratings_short.csv", "True");
    }
 
    // A second constructor should have only one String parameter named ratingsfile. 
    // This constructor should call the method loadRaters from the FirstRatings class to fill the myRaters ArrayList.
    public ThirdRatings(String ratingsfile, String trueorFalse) {
        FirstRatings fratings = new FirstRatings();
        myRaters = fratings.loadRaters(ratingsfile);
        if (trueorFalse.equals("True")){
            System.out.println("Print the Rater detailed info");
            for(Rater r : myRaters){
                r.printRatingHash();
            }
        }
    }

    public int getRaterSize() {
        return myRaters.size();
    }


    // This method returns a double representing the average movie rating for this ID if there are at least minimalRaters ratings. If there are not minimalRaters ratings, then it returns 0.0.
    public double getAverageByID(String id, int minimalRaters) {
        // System.out.println("=======getAverageByID()======");
        double avgRating = 0.0;
        ArrayList<Rater> movieRaterL = new ArrayList<Rater>();
        ArrayList<Double> movieRatingL = new ArrayList<Double>();
        for(Rater i : myRaters){
            if(i.hasRating(id)){
                movieRaterL.add(i);
                movieRatingL.add(i.getRating(id));
            }
        }
        if(movieRaterL.size() >= minimalRaters){
            for(Double value : movieRatingL){
                avgRating += value;
            }
            avgRating = avgRating / movieRaterL.size();
        }
        return avgRating;
    }


    // modify getAverageRatings.
    // yMovies no longer exists. need to get all the movies from the MovieDatabase class and store them in an ArrayList of movie IDs. 
    // modify getAverageRatings to call MovieDatabase with a filter, and use the TrueFilter to get every movie.
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> avgRatingL = new ArrayList<Rating>();
        MovieDatabase movieBase = new MovieDatabase();
        // TrueFilter f = new TrueFilter();
        ArrayList<String> movieIDList = movieBase.filterBy(new TrueFilter());

        for(String movieID : movieIDList){
            Double avgRating = getAverageByID(movieID, minimalRaters);
            if(avgRating != 0){
                avgRatingL.add(new Rating(movieID, avgRating));
                // System.out.println("movieID: " + m.getID() + " AvgRating: " +avgRating);
            }
        }
        // System.out.println(avgRatingL.size());
        return avgRatingL;
    }


    // write a public helper method named getAverageRatingsByFilter
    // has two parameters, 
    // an int named minimalRaters for the minimum number of ratings a movie must have
    // a Filter named filterCriteria. 
    // This method should create and return an ArrayList of type Rating of all the movies that have at least minimalRaters ratings and satisfies the filter criteria.
    // This method will need to create the ArrayList of type String of movie IDs from the MovieDatabase using the filterBy method before calculating those averages.
    // at least minimalRaters ratings + satisfies the filter criteria -> calculating those averages
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> anwList  = new ArrayList<Rating>();
        ArrayList<String> movieIDList = MovieDatabase.filterBy(filterCriteria);

        for(String movieID : movieIDList){
            Double avgRating = getAverageByID(movieID, minimalRaters);
            if(avgRating != 0){
                anwList.add(new Rating(movieID, avgRating));
                // System.out.println("movieID: " + m.getID() + " AvgRating: " +avgRating);
            }
        }
        // System.out.println(anwList.size());
        return anwList;
    }


    // public static void main(String[] args) {
    //     SecondRatings pr = new SecondRatings();
    //     ArrayList<Rating> ratingL = pr.getAverageRatings(2);
    //     // String movieTitle = pr.getTitle("1798709");
    //     // String movieTitle1 = pr.getTitle("17909");
    // }
}

