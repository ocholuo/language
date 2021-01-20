// Create a new class named MovieRunnerWithFilters
// use to find the average rating of movies using different filters.
// Copy the printAverageRatings method from the MovieRunnerAverage class into this class.

import java.util.*;

public class MovieRunnerWithFilters {

    String moviefile;
    String ratingfile;
    String datainfoTrueorFalse;
    ThirdRatings tRatings;

    // initialize where to get the movie and rater data,
    // movie.csv + ratings.csv
    public MovieRunnerWithFilters() {
        System.out.println("---------initialize the MovieRunnerWithFilters---------");
        // moviefile = "test.movie.csv";
        // ratingfile = "test.ratings.csv";
        moviefile = "data/ratedmoviesfull.csv";
        ratingfile = "data/ratings.csv";
        // moviefile = "data/ratedmovies_short.csv";
        // ratingfile = "data/ratings_short.csv";
        datainfoTrueorFalse = "True";

        tRatings = new ThirdRatings(ratingfile, datainfoTrueorFalse);
        //   call FirstRatings.loadRaters(ratingsfile) to fill the myRaters ArrayList from ratingfile.
        //        got ArrayList<Rater> myRaters;
        //   if moreinfoTrueorFalse == True
        //        print "Print the Rater detailed info"
        //        print out all the raterID, movieID, and it's Rating under the rater
        System.out.println("Number of total of Raters: " + tRatings.getRaterSize());

        MovieDatabase.initialize(moviefile);
        System.out.println("Number of total of movies: " + MovieDatabase.size());
        //   call FirstRatings.loadMovies(moviefile) to fill the Movies ArrayList from moviefile.
        //        got ArrayList<Movie> list
    }



    public void printAverageRatings(int minRatersNum, String moreinfoTrueorFalse) {
        // setup the minimum Rater Number, and whethere to print out the detailed rater info.
        System.out.println("---------printAverageRatings---------");
        int minimalRaters = minRatersNum;

        // get the average movie rating for this ID if there are at least minimalRaters ratings.
        ArrayList<Rating> avgRatingL = tRatings.getAverageRatings(minimalRaters);
        // Method to sort according the rating
        Collections.sort(avgRatingL);

        System.out.println(" +++++++++ Movies with at least " + minimalRaters + " ratings");
        System.out.println(" +++++++++ Average Ratings is: " + avgRatingL.size());

        if(moreinfoTrueorFalse.equals("True")){
            for(Rating r : avgRatingL){
                String movieID = r.getItem();
                String movieTitle = MovieDatabase.getTitle(movieID);
                System.out.println( (double)Math.round(r.getValue()*10000d) / 10000d+ " " + movieTitle);
            }
        }
        //   if moreinfoTrueorFalse == True
        //        print out the movie average rating and movie name
    }


    // create a void method named printAverageRatingsByYear
    // should be similar to printAverageRatings,
    // create a YearAfterFilter and call getAverageRatingsByFilter to get an ArrayList of type Rating of all the movies that have a specified number of minimal ratings and came out in a specified year or later.
    // Print the number of movies found, and for each movie found, print its rating, its year, and its title.
    // For example ratings_short.csv and ratedmovies_short.csv, minimal rater of 1 and the year 2000
    public void printAverageRatingsByYear(int minRatersNum, int yearNum, String moreinfoTrueorFalse) {
        System.out.println("---------printAverageRatingsByYear---------");
        int minimalRaters = minRatersNum;

        YearAfterFilter filterYear = new YearAfterFilter(yearNum);

        ArrayList<Rating> anwList = tRatings.getAverageRatingsByFilter(minimalRaters,filterYear);

        Collections.sort(anwList);

        System.out.println(" +++++++++ Movies with at least " + minimalRaters + " ratings and By " + yearNum);
        System.out.println(" +++++++++ Movies found: " + anwList.size());

        if(moreinfoTrueorFalse.equals("True")){
            for(Rating r : anwList){
                String item = r.getItem();
                String movieTitle = MovieDatabase.getTitle(item);
                int movieYear = MovieDatabase.getYear(item);
                System.out.println((double)Math.round(r.getValue() * 10000d) / 10000d + " " + movieYear + " "  + movieTitle);
            }
        }
        //   if moreinfoTrueorFalse == True
        //        print out the movie average rating and movie name
    }


    // create a void method named printAverageRatingsByGenre
    // create a GenreFilter
    // call getAverageRatingsByFilter to get an ArrayList of type Rating of all the movies that have a specified number of minimal ratings and include a specified genre.
    // Print the number of movies found, and for each movie, print its rating and its title on one line, and its genres on the next line.
    // For example, if you run the printAverageRatingsByGenre method on the files ratings_short.csv and ratedmovies_short.csv with a minimal rater of 1 and the genre “Crime”, you should see

    public void printAverageRatingsByGenre(int minRatersNum, String geneWord, String moreinfoTrueorFalse) {
        System.out.println("---------printAverageRatingsByGenre---------");

        int minimalRaters = minRatersNum;

        GenreFilter filterGenre = new GenreFilter(geneWord);

        ArrayList<Rating> anwList = tRatings.getAverageRatingsByFilter(minimalRaters, filterGenre);
        Collections.sort(anwList);

        System.out.println(" +++++++++ Movies with at least " + minimalRaters + " ratings and Genre as " + geneWord);
        System.out.println(" +++++++++ Movies found: " + anwList.size());

        if(moreinfoTrueorFalse.equals("True")){
            for(Rating r : anwList){
                String movieID = r.getItem();
                String movieTitle = MovieDatabase.getTitle(movieID);
                String movieGenre = MovieDatabase.getGenres(movieID);
                System.out.println((double)Math.round(r.getValue() * 10000d) / 10000d + " " + movieTitle);
                System.out.println("     " + movieGenre);
            }
        }
    }


    // create a void method named printAverageRatingsByMinutes
    // create a MinutesFilter and call getAverageRatingsByFilter to get an ArrayList of type Rating of all the movies that have a specified number of minimal ratings and their running time is at least a minimum number of minutes and no more than a maximum number of minutes.
    // Print the number of movies found, and for each movie print its rating, its running time, and its title on one line.
    // For example
    // ratings_short.csv and ratedmovies_short.csv, minimal rater of 1, minimum minutes of 110, and maximum minutes of 170


    public void printAverageRatingsByMinutes(int minRatersNum, int minMin, int maxMin, String moreinfoTrueorFalse) {
        System.out.println("---------printAverageRatingsByMinutes---------");
        int minimalRaters = minRatersNum;
        MinutesFilter filterMin = new MinutesFilter(minMin, maxMin);

        ArrayList<Rating> anwList = tRatings.getAverageRatingsByFilter(minimalRaters, filterMin);
        Collections.sort(anwList);

        System.out.println(" +++++++++ Movies with at least " + minimalRaters + " ratings and duration between " + minMin + " and " + maxMin);
        System.out.println(" +++++++++ Movies found: " + anwList.size());

        if(moreinfoTrueorFalse.equals("True")){
            for(Rating r : anwList){
                String movieID = r.getItem();
                String movieTitle = MovieDatabase.getTitle(movieID);
                int movieMin = MovieDatabase.getMinutes(movieID);
                System.out.println((double)Math.round(r.getValue() * 10000d) / 10000d + " Time:" + movieMin + " " + movieTitle);
            }
        }
    }


    // create a void method named printAverageRatingsByDirectors
    // create a DirectorsFilter
    // call getAverageRatingsByFilter to get an ArrayList of type Rating of all the movies that have a specified number of minimal ratings and include at least one of the directors specified.
    // Print the number of movies found, and for each movie print its rating and its title on one line, and all its directors on the next line.
    // For example, ratings_short.csv and ratedmovies_short.csv with a minimal rater of 1 and the directors set to "Charles Chaplin,Michael Mann,Spike Jonze"

    public void printAverageRatingsByDirectors(int minRatersNum, String dirName, String moreinfoTrueorFalse) {
        System.out.println("---------printAverageRatingsByDirectors---------");
        int minimalRaters = minRatersNum;
        String targetDir = dirName;

        DirectorsFilter filterDir = new DirectorsFilter(targetDir);

        ArrayList<Rating> anwList = tRatings.getAverageRatingsByFilter(minimalRaters, filterDir);
        Collections.sort(anwList);

        System.out.println(" +++++++++ Movies with at least " + minimalRaters + " ratings and by directory " + dirName);
        System.out.println(" +++++++++ Movies found: " + anwList.size());

        if(moreinfoTrueorFalse.equals("True")){
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
        }
    }




    // create a void method named printAverageRatingsByYearAfterAndGenre
    // create an AllFilters object that includes criteria based on movies that came out in a specified year or later and have a specified genre as one of its genres.
    // call getAverageRatingsByFilter to get an ArrayList of type Rating of all the movies that have a specified number of minimal ratings and the two criteria based on year and genre.
    // Print the number of movies found, and for each movie, print its rating, its year, and its title on one line, and all its genres on the next line.
    // For example ratings_short.csv and ratedmovies_short.csv
    // minimal rater of 1, the year set to 1980, and the genre set to “Romance”
    // minimal rater of 1, minimum minutes set to 30, maximum minutes set to 170, and the directors set to "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"
    public void printAverageRatingsByYearAfterAndGenre(int minRatersNum, String moreinfoTrueorFalse) {
        System.out.println("---------printAverageRatingsByYearAfterAndGenre---------");
        int minimalRaters = minRatersNum;
        int targetYear = 0;
        String targetGen = "null";
        String targetDir = "null";
        int minMin = 0;
        int maxMin = 0;


        AllFilters allFilters = new AllFilters();

        // targetYear = 1990;
        // YearAfterFilter filterYear = new YearAfterFilter(targetYear);

        // targetGen = "Drama";
        // GenreFilter filterGen = new GenreFilter(targetGen);

        targetDir = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        DirectorsFilter filterDir = new DirectorsFilter(targetDir);

        minMin = 90;
        maxMin = 180;
        MinutesFilter filterMin = new MinutesFilter(minMin, maxMin);

        // allFilters.addFilter(filterGen);
        // allFilters.addFilter(filterYear);
        allFilters.addFilter(filterDir);
        allFilters.addFilter(filterMin);

        ArrayList<Rating> anwList = tRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        Collections.sort(anwList);

        System.out.println(" +++++++++ Movies with at least " + minimalRaters + " ratings and by filter:");
        System.out.println(" +++++++++ Year: " + targetYear);
        System.out.println(" +++++++++ Gene: " + targetGen);
        System.out.println(" +++++++++ Director: " + targetDir);
        System.out.println(" +++++++++ Duration: " + minMin + ", " + maxMin);
        System.out.println(" +++++++++ Movies found: " + anwList.size());


        if(moreinfoTrueorFalse.equals("True")){
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
        }
    }


    public static void main(String[] args) {
        MovieRunnerWithFilters pr = new MovieRunnerWithFilters();

        // test result:
        // pr.printAverageRatings(1, "1True");
        // pr.printAverageRatingsByYear(1, 2000, "1True");
        // pr.printAverageRatingsByGenre(1, "Crime", "1True");
        // pr.printAverageRatingsByMinutes(1, 110, 170, "1True");
        // pr.printAverageRatingsByDirectors(1, "Charles Chaplin,Michael Mann,Spike Jonze", "1True");
        // pr.printAverageRatingsByYearAfterAndGenre(1, "1True");

        // Quiz:
        pr.printAverageRatings(35, "1True");
        pr.printAverageRatingsByYear(20, 2000, "1True");
        pr.printAverageRatingsByGenre(20, "Comedy", "1True");
        pr.printAverageRatingsByMinutes(5, 105, 135, "1True");
        pr.printAverageRatingsByDirectors(4, "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack", "1True");
        pr.printAverageRatingsByYearAfterAndGenre(3, "1True");
    }
}



// Number of total of Raters: 1048
// Number of total of movies: 3143
// ---------printAverageRatings---------
//  +++++++++ Movies with at least 35 ratings
//  +++++++++ Average Ratings is: 29
// ---------printAverageRatingsByYear---------
//  +++++++++ Movies with at least 20 ratings and By 2000
//  +++++++++ Movies found: 88
// ---------printAverageRatingsByGenre---------
// finding Genre: Comedy
//  +++++++++ Movies with at least 20 ratings and Genre as Comedy
//  +++++++++ Movies found: 19
// ---------printAverageRatingsByMinutes---------
// time period: 105 - 135
//  +++++++++ Movies with at least 5 ratings and duration between 105 and 135
//  +++++++++ Movies found: 231
// ---------printAverageRatingsByDirectors---------
//  +++++++++ Movies with at least 4 ratings and by directory Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack
//  +++++++++ Movies found: 22
// ---------printAverageRatingsByYearAfterAndGenre---------
// finding Genre: Drama
// time period: 30 - 170
//  +++++++++ Movies with at least 8 ratings and by filter:
//  +++++++++ Year: 1990
//  +++++++++ Gene: Drama
//  +++++++++ Director: Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola
//  +++++++++ Duration: 30, 170
//  +++++++++ Movies found: 132
// ---------printAverageRatingsByYearAfterAndGenre---------
// time period: 90 - 180
//  +++++++++ Movies with at least 3 ratings and by filter:
//  +++++++++ Year: 0
//  +++++++++ Gene: null
//  +++++++++ Director: Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack
//  +++++++++ Duration: 90, 180
//  +++++++++ Movies found: 15
