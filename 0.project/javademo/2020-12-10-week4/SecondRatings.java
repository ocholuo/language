
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

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
        // this("data/ratedmoviesfull.csv", "data/ratings.csv");
        this("ratedmovies_short.csv", "ratings_short.csv");
    }
 
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fratings = new FirstRatings();
        myMovies = fratings.loadMovies(moviefile);
        for(Movie m : myMovies){
            System.out.println(m);
        }
        myRaters = fratings.loadRaters(ratingsfile);
        for(Rater r : myRaters){
            System.out.println(r.getID() + r.getItemsRated());
        }
    }

    public int getMovieSize() {
        return myMovies.size();
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


    // This method should find the average rating for every movie that has been rated by at least minimalRaters raters. Store each such rating in a Rating object in which the movie ID and the average rating are used in creating the Rating object. The method getAverageRatings should return an ArrayList of all the Rating objects for movies that have at least the minimal number of raters supplying a rating.
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        // System.out.println("=======getAverageRatings()======");
        ArrayList<Rating> ratingL = new ArrayList<Rating>();
        for(Movie m : myMovies){
            Double avgRating = getAverageByID(m.getID(), minimalRaters);
            if(avgRating != 0){
                ratingL.add(new Rating(m.getID(), avgRating));
                // System.out.println("movieID: " + m.getID() + " AvgRating: " +avgRating);
            }
        }
        // System.out.println(ratingL.size());
        return ratingL;
    }


    // This method returns the title of the movie with that ID. If the movie ID does not exist, then this method should return a String indicating the ID was not found.
    public String getTitle(String id) {
        String movieTitle = "";
        for(Movie m : myMovies){
            if(m.getID().equals(id)){
                // System.out.println(movieTitle + m.getTitle());
                return movieTitle + m.getTitle();
            }
        }
        // System.out.println(movieTitle + "not found");
        return movieTitle + "not found";
    }


    public String getID(String title) {
        for(Movie m : myMovies){
            if(m.getTitle().equals(title)){
                String movieID = m.getID();
                return movieID;
            }
        }
        // System.out.println(movieTitle + "not found");
        return "NO SUCH TITLE.";
    }



    public static void main(String[] args) {
        SecondRatings pr = new SecondRatings();
        ArrayList<Rating> ratingL = pr.getAverageRatings(2);
        // String movieTitle = pr.getTitle("1798709");
        // String movieTitle1 = pr.getTitle("17909");
    }
}
