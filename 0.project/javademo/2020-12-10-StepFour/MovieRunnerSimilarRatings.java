// Create a new class named MovieRunnerSimilarRatings
// use to find the average rating of movies using different filters. 
// Copy the printAverageRatings method from the MovieRunnerAverage class into this class. 

import java.util.*; 

public class MovieRunnerSimilarRatings {

    String moviefile;
    String ratingfile;
    String datainfoTrueorFalse;
    FourthRatings fRatings;

    // initialize where to get the movie and rater data, 
    // movie.csv + ratings.csv
    public MovieRunnerSimilarRatings() {
        System.out.println("---------initialize the MovieRunnerSimilarRatings---------");
        // moviefile = "data/test.movie.csv";
        // ratingfile = "data/test.ratings.csv";
        moviefile = "data/ratedmoviesfull.csv";
        ratingfile = "data/ratings.csv";
        // moviefile = "data/ratedmovies_short.csv";
        // ratingfile = "data/ratings_short.csv";
        datainfoTrueorFalse = "1True";

        fRatings = new FourthRatings(ratingfile, datainfoTrueorFalse);
        //   call FirstRatings.loadRaters(ratingsfile) to fill the myRaters ArrayList from ratingfile.
        //        got ArrayList<Rater> myRaters;
        //   if moreinfoTrueorFalse == True
        //        print "Print the Rater detailed info"
        //        print out all the raterID, movieID, and it's Rating under the rater
        System.out.println("Number of total of Raters: " + fRatings.getRaterSize());

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
        ArrayList<Rating> avgRatingL = fRatings.getAverageRatings(minimalRaters);         
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
        
        ArrayList<Rating> anwList = fRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
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

    
    // This method creates a new FourthRatings object, reads data into the MovieDatabase and RaterDatabase, and then calls getSimilarRatings for a particular rater ID, a number for the top number of similar raters, and a number of minimal rateSimilarRatings, and then lists recommended movies and their similarity ratings. 
    // For example, using the files ratedmoviesfull.csv and ratings.csv and the rater ID 65, the number of minimal raters 5, and the number of top similar raters set to 20, the movie returned with the top rated average is “The Fault in Our Stars”.
    public void printSimilarRatings (String rater_id, int numTopRaters, int minimalRate) { 
        System.out.println(" ---------------- printSimilarRatings ---------------- ");
        
        ArrayList<Rating> getSimilarRatings = fRatings.getSimilarRatings(rater_id, numTopRaters, minimalRate);

        for(Rating r : getSimilarRatings){
            // System.out.println("for rater: " + r.getItem());
            //MovieDatabase class gets superhandy:
            System.out.println("The movie returned with the top rated average is: " + MovieDatabase.getTitle(r.getItem()) + ", weighted is: " + r.getValue());
            break;
        }
    }


    // uses a genre filter and then lists recommended movies and their similarity ratings, and for each movie prints the movie and its similarity rating on one line and its genres on a separate line below it. 
    // For example, using the files ratedmoviesfull.csv and ratings.csv, the genre “Action”,  the rater ID 65, the number of minimal raters set to 5, and the number of top similar raters set to 20, the movie returned with the top rated average is “Rush”.
    public void printSimilarRatingsByGenre(String rater_id, int numTopRaters, int minimalRate, String genreWord) { 
        System.out.println(" ---------------- printSimilarRatingsByGenre ---------------- ");

        GenreFilter filterGenre = new GenreFilter(genreWord);
        
        ArrayList<Rating> getSimilarRatingsByFilter = fRatings.getSimilarRatingsByFilter(rater_id, numTopRaters, minimalRate, filterGenre);

        for(Rating r : getSimilarRatingsByFilter){
            System.out.println("The movie returned with the top rated average is: " + MovieDatabase.getTitle(r.getItem()) + ", weighted is: " + r.getValue());
            break;
        }
    }
    

    // This method is similar to printSimilarRatings but also uses a director filter and then lists recommended movies and their similarity ratings, and for each movie prints the movie and its similarity rating on one line and its directors on a separate line below it. 
    // For example, using the files ratedmoviesfull.csv and ratings.csv, the directors “Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone”, the rater ID 1034, the number of minimal raters set to 3, and the number of top similar raters set to 10, the movie returned with the top rated average is “Unforgiven”.
    public void printSimilarRatingsByDirector(String rater_id, int numTopRaters, int minimalRate, String directors) { 
        System.out.println(" ---------------- printSimilarRatingsByDirector ---------------- ");

        DirectorsFilter filterDirector = new DirectorsFilter(directors);
        ArrayList<Rating> getSimilarRatingsByFilter = fRatings.getSimilarRatingsByFilter(rater_id, numTopRaters, minimalRate, filterDirector);

        for(Rating r : getSimilarRatingsByFilter){
            System.out.println("The movie returned with the top rated average is: " + MovieDatabase.getTitle(r.getItem()) + ", weighted is: " + r.getValue());
            break;
        }
    }
    

    // This method is similar to printSimilarRatings but also uses a genre filter and a minutes filter and then lists recommended movies and their similarity ratings, and for each movie prints the movie, its minutes, and its similarity rating on one line and its genres on a separate line below it. 
    // For example, using the files ratedmoviesfull.csv and ratings.csv, the genre “Adventure”,  minutes between 100 and 200 inclusive, the rater ID 65, the number of minimal raters set to 5, and the number of top similar raters set to 10, the movie returned with the top rated average is “Interstellar”.
    public void printSimilarRatingsByGenreAndMinutes(String rater_id, int numTopRaters, int minimalRate, String genWord, int minMin, int maxMin) { 
        System.out.println(" ---------------- printSimilarRatingsByGenreAndMinutes ---------------- ");

        AllFilters GenreAndMinutes = new AllFilters();
        GenreFilter genre = new GenreFilter(genWord);
        MinutesFilter minutes = new MinutesFilter(minMin, maxMin);
        GenreAndMinutes.addFilter(genre);
        GenreAndMinutes.addFilter(minutes);
        
        ArrayList<Rating> getSimilarRatingsByFilter = fRatings.getSimilarRatingsByFilter(rater_id, numTopRaters, minimalRate, GenreAndMinutes);

        for(Rating r : getSimilarRatingsByFilter){
            System.out.println("The movie returned with the top rated average is: " + MovieDatabase.getTitle(r.getItem()) + ", weighted is: " + r.getValue());
            break;
        }
    }
    

    public void printSimilarRatingsByYearAfterAndMinutes(String rater_id, int numTopRaters, int minimalRate, int Year,int minMin, int maxMin) { 
        System.out.println(" ---------------- printSimilarRatingsByYearAfterAndMinutes ---------------- ");

        AllFilters YearAfterAndMinutes = new AllFilters();
        YearAfterFilter year = new YearAfterFilter(Year);
        MinutesFilter minutes = new MinutesFilter(minMin, maxMin);

        YearAfterAndMinutes.addFilter(year);
        YearAfterAndMinutes.addFilter(minutes);
        
        ArrayList<Rating> getSimilarRatingsByFilter = fRatings.getSimilarRatingsByFilter(rater_id, numTopRaters, minimalRate, YearAfterAndMinutes);

        for(Rating r : getSimilarRatingsByFilter){
            System.out.println("The movie returned with the top rated average is: " + MovieDatabase.getTitle(r.getItem()) + ", weighted is: " + r.getValue());
            break;
        }
    }



    public static void main(String[] args) {
        MovieRunnerSimilarRatings pr = new MovieRunnerSimilarRatings();
        // test result:
        // pr.printAverageRatings(1, "1True");
        // pr.printAverageRatingsByYearAfterAndGenre(1, "1True");
        // pr.printSimilarRatings ("65", 20, 5);
        pr.printSimilarRatingsByGenre ("65", 20, 5, "Action");
        // pr.printSimilarRatingsByDirector ("1034", 10, 3, "Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone");
        // pr.printSimilarRatingsByGenreAndMinutes ("65", 10, 5, "Adventure", 100, 200);
        // pr.printSimilarRatingsByYearAfterAndMinutes ("65", 10, 5, 2000, 80, 100);


        // Quiz:
        pr.printSimilarRatings ("337", 10, 3);
        pr.printSimilarRatingsByGenre ("964", 20, 5, "Mystery");
        pr.printSimilarRatings ("71", 20, 5);
        pr.printSimilarRatingsByDirector ("120", 10, 2, "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        pr.printSimilarRatingsByGenreAndMinutes ("168", 10, 3, "Drama", 80, 160);
        pr.printSimilarRatingsByYearAfterAndMinutes ("314", 10, 5, 1975, 70, 200);
    }
}

 