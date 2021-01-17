// Create a new class named MovieRunnerWithFilters
// use to find the average rating of movies using different filters. 
// Copy the printAverageRatings method from the MovieRunnerAverage class into this class. 


import java.security.Principal;
import java.util.*;
import lib.edu.duke.*;
import lib.org.apache.commons.csv.*;


public class MovieRunnerWithFilters {


    private int helperMoviesAndRatings(int miniRater) {
        // String moviefile = "test.movie.csv";
        // String ratingfile = "test.ratings.csv";

        String moviefile = "ratedmoviesfull.csv";
        String ratingfile = "ratings.csv";

        // String moviefile = "ratedmovies_short.csv";
        // String ratingfile = "ratings_short.csv";

        int minimum = miniRater;

        ThirdRatings tRatings = new ThirdRatings(ratingfile);
        System.out.println("Number of total of Raters: " + tRatings.getRaterSize());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of total of movies: " + MovieDatabase.size());
        //Insert the minimum raters here:
        
        System.out.println("minimum: " + minimum);
        return minimum;
    }


    public void printAverageRatings() {
        System.out.println("---------printAverageRatings---------");
        int minimalRaters = helperMoviesAndRatings(35);
        // ThirdRatings tratings = new ThirdRatings("ratings_short.csv");
        ThirdRatings tratings = new ThirdRatings("ratings.csv");

        // add code to print a list of movies and their average ratings, for all those movies that have at least a specified number of ratings, sorted by averages.
        ArrayList<Rating> avgRatingL = tratings.getAverageRatings(minimalRaters);
        // Method to sort them as requested:
        Collections.sort(avgRatingL);

        for(Rating r : avgRatingL){
            String movieID = r.getItem();
            String movieTitle = MovieDatabase.getTitle(movieID);
            System.out.println( (double)Math.round(r.getValue()*10000d) / 10000d+ " " + movieTitle);
        }
        System.out.println("found " + avgRatingL.size() + " movies");
        System.out.println("Movies with at least " + minimalRaters + " ratings: " + avgRatingL.size());
        // System.out.println("Movie lowest rating: " + lowestMovie + " rating: " + lowestValue);
    }


    // create a void method named printAverageRatingsByYear
    // should be similar to printAverageRatings,
    // create a YearAfterFilter and call getAverageRatingsByFilter to get an ArrayList of type Rating of all the movies that have a specified number of minimal ratings and came out in a specified year or later. 
    // Print the number of movies found, and for each movie found, print its rating, its year, and its title. 
    // For example ratings_short.csv and ratedmovies_short.csv, minimal rater of 1 and the year 2000 
    public void printAverageRatingsByYear() {
        System.out.println("---------printAverageRatingsByYear---------");
        YearAfterFilter filterYear = new YearAfterFilter(2000);
        // ThirdRatings tratings = new ThirdRatings("ratings_short.csv");
        ThirdRatings tratings = new ThirdRatings("ratings.csv");
        int minimalRaters = helperMoviesAndRatings(20);

        ArrayList<Rating> anwList = tratings.getAverageRatingsByFilter(minimalRaters,filterYear);
        Collections.sort(anwList);
        System.out.println("found " + anwList.size() + " movies");
        for(Rating r : anwList){
             String item = r.getItem();
             String movieTitle = MovieDatabase.getTitle(item);
             int movieYear = MovieDatabase.getYear(item);
             System.out.println((double)Math.round(r.getValue() * 10000d) / 10000d + " " + movieYear + " "  + movieTitle);
        }
        System.out.println("Movies returned = " + anwList.size());
    }


    // create a void method named printAverageRatingsByGenre
    // create a GenreFilter
    // call getAverageRatingsByFilter to get an ArrayList of type Rating of all the movies that have a specified number of minimal ratings and include a specified genre. 
    // Print the number of movies found, and for each movie, print its rating and its title on one line, and its genres on the next line. 
    // For example, if you run the printAverageRatingsByGenre method on the files ratings_short.csv and ratedmovies_short.csv with a minimal rater of 1 and the genre “Crime”, you should see
    public void printAverageRatingsByGenre() {
        System.out.println("---------printAverageRatingsByGenre---------");
        GenreFilter filterGenre = new GenreFilter("Comedy");
        // ThirdRatings tratings = new ThirdRatings("ratings_short.csv");
        ThirdRatings tratings = new ThirdRatings("ratings.csv");
        int minimalRaters = helperMoviesAndRatings(20);
        
        ArrayList<Rating> anwList = tratings.getAverageRatingsByFilter(minimalRaters, filterGenre);
        Collections.sort(anwList);
        System.out.println("found " + anwList.size() + " movies");

        for(Rating r : anwList){
            String movieID = r.getItem();
            String movieTitle = MovieDatabase.getTitle(movieID);
            String movieGenre = MovieDatabase.getGenres(movieID);
            System.out.println((double)Math.round(r.getValue() * 10000d) / 10000d + " " + movieTitle);
            System.out.println("     " + movieGenre);
       }
       System.out.println("Movies returned = " + anwList.size());
    }


    // create a void method named printAverageRatingsByMinutes
    // create a MinutesFilter and call getAverageRatingsByFilter to get an ArrayList of type Rating of all the movies that have a specified number of minimal ratings and their running time is at least a minimum number of minutes and no more than a maximum number of minutes. 
    // Print the number of movies found, and for each movie print its rating, its running time, and its title on one line. 
    // For example
    // ratings_short.csv and ratedmovies_short.csv, minimal rater of 1, minimum minutes of 110, and maximum minutes of 170 
    public void printAverageRatingsByMinutes() {
        System.out.println("---------printAverageRatingsByMinutes---------");
        MinutesFilter filterMin = new MinutesFilter(105, 135);
        // ThirdRatings tratings = new ThirdRatings("ratings_short.csv");
        ThirdRatings tratings = new ThirdRatings("ratings.csv");
        int minimalRaters = helperMoviesAndRatings(5);
        
        ArrayList<Rating> anwList = tratings.getAverageRatingsByFilter(minimalRaters, filterMin);
        Collections.sort(anwList);
        System.out.println("found " + anwList.size() + " movies");

        for(Rating r : anwList){
            String movieID = r.getItem();
            String movieTitle = MovieDatabase.getTitle(movieID);
            int movieMin = MovieDatabase.getMinutes(movieID);
            System.out.println((double)Math.round(r.getValue() * 10000d) / 10000d + " Time:" + movieMin + " " + movieTitle);
       }
       System.out.println("Movies returned = " + anwList.size());
    }


    // create a void method named printAverageRatingsByDirectors
    // create a DirectorsFilter
    // call getAverageRatingsByFilter to get an ArrayList of type Rating of all the movies that have a specified number of minimal ratings and include at least one of the directors specified. 
    // Print the number of movies found, and for each movie print its rating and its title on one line, and all its directors on the next line. 
    // For example, ratings_short.csv and ratedmovies_short.csv with a minimal rater of 1 and the directors set to "Charles Chaplin,Michael Mann,Spike Jonze"
    public void printAverageRatingsByDirectors() {
        System.out.println("---------printAverageRatingsByDirectors---------");
        // String targetDir = "Charles Chaplin,Michael Mann,Spike Jonze";
        // ThirdRatings tratings = new ThirdRatings("ratings_short.csv");
        String targetDir = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        ThirdRatings tratings = new ThirdRatings("ratings.csv");
        DirectorsFilter filterDir = new DirectorsFilter(targetDir);
        int minimalRaters = helperMoviesAndRatings(4);
        
        ArrayList<Rating> anwList = tratings.getAverageRatingsByFilter(minimalRaters, filterDir);
        Collections.sort(anwList);
        System.out.println("found " + anwList.size() + " movies");

        for(Rating r : anwList){
            String movieID = r.getItem();
            String movieTitle = MovieDatabase.getTitle(movieID);
            String movieDirs = MovieDatabase.getDirector(movieID);
            System.out.println((double)Math.round(r.getValue() * 10000d) / 10000d + " " + movieTitle);
            
            for(String directorName : targetDir.split(",")) {
                if(movieDirs.contains(directorName)) {
                    System.out.println("    " + directorName);
                    break;
                }
            }
       }
       System.out.println("Movies returned = " + anwList.size());
    }


    // create a void method named printAverageRatingsByYearAfterAndGenre
    // create an AllFilters object that includes criteria based on movies that came out in a specified year or later and have a specified genre as one of its genres. 
    // call getAverageRatingsByFilter to get an ArrayList of type Rating of all the movies that have a specified number of minimal ratings and the two criteria based on year and genre. 
    // Print the number of movies found, and for each movie, print its rating, its year, and its title on one line, and all its genres on the next line. 
    // For example ratings_short.csv and ratedmovies_short.csv
    // minimal rater of 1, the year set to 1980, and the genre set to “Romance”
    // minimal rater of 1, minimum minutes set to 30, maximum minutes set to 170, and the directors set to "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"
    public void printAverageRatingsByYearAfterAndGenre() {
        System.out.println("---------printAverageRatingsByYearAfterAndGenre---------");
        // ThirdRatings tratings = new ThirdRatings("ratings_short.csv");
        ThirdRatings tratings = new ThirdRatings("ratings.csv");
        int minimalRaters = helperMoviesAndRatings(3);

        AllFilters allFilters = new AllFilters();

        // int targetYear = 1990;
        // YearAfterFilter filterYear = new YearAfterFilter(targetYear);

        // String targetGen = "Drama";
        // GenreFilter filterGen = new GenreFilter(targetGen);

        String targetDir = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        DirectorsFilter filterDir = new DirectorsFilter(targetDir);

        MinutesFilter filterMin = new MinutesFilter(90, 180);

        // allFilters.addFilter(filterGen);
        // allFilters.addFilter(filterYear);
        allFilters.addFilter(filterDir);
        allFilters.addFilter(filterMin);
        
        ArrayList<Rating> anwList = tratings.getAverageRatingsByFilter(minimalRaters, allFilters);
        Collections.sort(anwList);
        System.out.println("found " + anwList.size() + " movies");

        for(Rating r : anwList){
            String movieID = r.getItem();
            String movieTitle = MovieDatabase.getTitle(movieID);
            String movieGenre = MovieDatabase.getGenres(movieID);
            String movieDirs = MovieDatabase.getDirector(movieID);
            int movieYear = MovieDatabase.getYear(movieID);
            int movieMin = MovieDatabase.getMinutes(movieID);

            System.out.println((double)Math.round(r.getValue() * 10000d) / 10000d + " Time:" + movieMin + " Year: " + movieYear + " "  + " Title: " + movieTitle);
            System.out.println("     Genere: " + movieGenre);

            for(String directorName : targetDir.split(",")) {
                if(movieDirs.contains(directorName)) {
                    System.out.println("    director: " + directorName);
                    break;
                }
            }
       }
       System.out.println("Movies returned = " + anwList.size());
    }


    public static void main(String[] args) {
        MovieRunnerWithFilters pr = new MovieRunnerWithFilters();
        // pr.printAverageRatings();
        // pr.printAverageRatingsByYear();
        // pr.printAverageRatingsByGenre();
        // pr.printAverageRatingsByMinutes();
        // pr.printAverageRatingsByDirectors();
        pr.printAverageRatingsByYearAfterAndGenre();
    }
}
