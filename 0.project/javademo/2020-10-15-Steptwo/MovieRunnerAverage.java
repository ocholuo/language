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
    
    

    public void printAverageRatings() {

        int minimalRaters = 3;

        // Create a SecondRatings object and use the CSV filenames of movie information and ratings information from the first assignment when calling the constructor.
        SecondRatings sratings = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");


        // Print the number of movies and number of raters from the two files by calling the appropriate methods in the SecondRatings class.
        System.out.println("Number of total movies: " + sratings.getMovieSize());
        System.out.println("Number of total of Raters: " + sratings.getRaterSize());
        

        // In the MovieRunnerAverage class in the printAverageRatings method, add code to print a list of movies and their average ratings, for all those movies that have at least a specified number of ratings, sorted by averages. 
        // Specifically, this method should print the list of movies, one movie per line (print its rating first, followed by its title) in sorted order by ratings, lowest rating to highest rating. 
        // For example, if printAverageRatings is called on the files ratings_short.csv and ratedmovies_short.csv with the argument 3, then the output will display two movies:
        // 8.25 Her
        // 9.0 The Godfather
        ArrayList<Rating> movieAvg = sratings.getAverageRatings(minimalRaters);
        //Method to sort them as requested:
        Collections.sort(movieAvg);
        for(Rating r : movieAvg){
            System.out.println(r);
            String movieID = r.getItem();
            String movieTitle = sratings.getTitle(movieID);
            //We also used a method to round the rating value:
            System.out.println(r.getValue() + " " + movieTitle);
        }
        
        // System.out.println("Movies with at least " + minimum + " ratings: " + arrayMovies.size());



    }




    


    public static void main(String[] args) {

        MovieRunnerAverage pr = new MovieRunnerAverage();
        pr.printAverageRatings();
    }
}
