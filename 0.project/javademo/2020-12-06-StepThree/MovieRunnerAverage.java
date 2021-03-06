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


public class MovieRunnerAverage {

    // String moviefile = "test.movie.csv";
    // String ratingfile = "test.ratings.csv";

    String moviefile = "ratedmoviesfull.csv";
    String ratingfile = "ratings.csv";

    // String moviefile = "ratedmovies_short.csv";
    // String ratingfile = "ratings_short.csv";


    public void printAverageRatings() {

        int minimalRaters = 20;

        // Create a SecondRatings object and use the CSV filenames of movie information and ratings information from the first assignment when calling the constructor.
        SecondRatings sratings = new SecondRatings(moviefile, ratingfile);

        // Print the number of movies and number of raters from the two files by calling the appropriate methods in the SecondRatings class.
        System.out.println("Number of total movies: " + sratings.getMovieSize());
        System.out.println("Number of total of Raters: " + sratings.getRaterSize());


        // add code to print a list of movies and their average ratings, for all those movies that have at least a specified number of ratings, sorted by averages.
        // Specifically, this method should print the list of movies, one movie per line (print its rating first, followed by its title) in sorted order by ratings, lowest rating to highest rating.
        // For example, if printAverageRatings is called on the files ratings_short.csv and ratedmovies_short.csv with the argument 3, then the output will display two movies:
        // 8.25 Her
        // 9.0 The Godfather
        ArrayList<Rating> movieAvg = sratings.getAverageRatings(minimalRaters);
        //Method to sort them as requested:
        Collections.sort(movieAvg);

        double lowestValue = Integer.MAX_VALUE;
        String lowestMovie = "";

        for(Rating r : movieAvg){
            System.out.println(r);
            String movieID = r.getItem();
            String movieTitle = sratings.getTitle(movieID);
            //We also used a method to round the rating value:
            System.out.println(r.getValue() + " " + movieTitle);
            
            if(r.getValue() < lowestValue) {
                lowestValue = r.getValue();
                lowestMovie = movieTitle;
            }
        }
        System.out.println("Movies with at least " + minimalRaters + " ratings: " + movieAvg.size());
        System.out.println("Movie lowest rating: " + lowestMovie + " rating: " + lowestValue);
    }



    
    // write the void method getAverageRatingOneMovie,
    // has no parameters. 
    // This method should first create a SecondRatings object, reading in data from the movie and ratings data files. 
    // Then this method should print out the average ratings for a specific movie title, such as the movie “The Godfather”. 
    // (ratedmovies_short.csv, and ratings_short.csv, then the average for the movie “The Godfather”  would be 9.0.)
    // public Rating (String anItem, double aValue)
    public void getAverageRatingOneMovie() {
        // SecondRatings sratings = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        SecondRatings sratings = new SecondRatings(moviefile, ratingfile);

        // String movieTitle = "The Godfather";
        // String movieTitle = "No Country for Old Men";
        // String movieTitle = "Inside Llewyn Davis";
        String movieTitle = "The Maze Runner";
        // String movieTitle = "Vacation";
        String movieID = sratings.getID(movieTitle);

        // Rating (String anItem, double aValue)
        double avgRating = sratings.getAverageByID(movieID, 0);
        System.out.println("the average for the movie:" + movieTitle + " is: " + avgRating);
    }




    public static void main(String[] args) {
        MovieRunnerAverage pr = new MovieRunnerAverage();
        // pr.printAverageRatings();
        pr.getAverageRatingOneMovie();
    }
}
