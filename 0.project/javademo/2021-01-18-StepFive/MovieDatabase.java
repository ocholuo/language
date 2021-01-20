import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.FileResource;

public class MovieDatabase {

    // - A HashMap named ourMovies
    // maps a movie ID String to a Movie object with all the information about that movie.
    private static HashMap<String, Movie> ourMovies;

    // - A private initialize method
    // with no parameters
    // will load the movie file ratedmoviesfull.csv if no file has been loaded.
    // This method is called as a safety check with any of the other public methods to make sure there is movie data in the database.
    private static void initialize() {
        if (ourMovies == null) {
            ourMovies = new HashMap<String,Movie>();
            loadMovies("data/ratedmoviesfull.csv");
        }
    }

    // - A public initialize method
    // with one String parameter named moviefile.
    // call this method with the name of the file used to initialize the movie database.
    // public static void initialize(String moviefile) {
    //     if (ourMovies == null) {
    //         ourMovies = new HashMap<String,Movie>();
    //         loadMovies("data/" + moviefile);
    //     }
    // } 
    public static HashMap<String, Movie> initialize(String moviefile) {
        // System.out.println("MovieDatabase.initialize");
        if (ourMovies == null) {
            // System.out.println("MovieDatabase.initialize: null ourMovies");
            ourMovies = new HashMap<String,Movie>();
            // System.out.println("MovieDatabase.loadMovies:");
            loadMovies(moviefile);
        }
        // System.out.println(ourMovies);
        return ourMovies;
    } 

	
    // - A private loadMovies method
    // build the HashMap.
    private static void loadMovies(String filename) {
        FirstRatings fr = new FirstRatings();
        // System.out.println("FirstRatings.loadMovies:");
        ArrayList<Movie> list = fr.loadMovies(filename);
        // System.out.println(list);
        for (Movie m : list) {
            ourMovies.put(m.getID(), m);
            // System.out.println(m);
        }
    }

    // - A containsID method
    // with one String parameter named id.
    // returns true if the id is a movie in the database, and false otherwise.
    public static boolean containsID(String id) {
        initialize();
        return ourMovies.containsKey(id);
    }

    public static ArrayList<String> getMovies() {
        initialize();
        ArrayList<String> movieIDList = new ArrayList<String>();
        for (String movieID : ourMovies.keySet()) {
            movieIDList.add(movieID);
        }
        return movieIDList;
    }

    
    // - Several getter methods
    // including getYear, getTitle, getMovie, getPoster, getMinutes, getCountry, getGenres, and getDirector.
    // takes a movie ID as a parameter and returns information about that movie.
    public static int getYear(String id) {
        initialize();
        return ourMovies.get(id).getYear();
    }

    public static String getGenres(String id) {
        initialize();
        return ourMovies.get(id).getGenres();
    }

    public static String getTitle(String id) {
        initialize();
        return ourMovies.get(id).getTitle();
    }

    public static Movie getMovie(String id) {
        initialize();
        return ourMovies.get(id);
    }

    public static String getPoster(String id) {
        initialize();
        return ourMovies.get(id).getPoster();
    }

    public static int getMinutes(String id) {
        initialize();
        return ourMovies.get(id).getMinutes();
    }

    public static String getCountry(String id) {
        initialize();
        return ourMovies.get(id).getCountry();
    }

    public static String getDirector(String id) {
        initialize();
        return ourMovies.get(id).getDirector();
    }

    // - A size method
    // returns the number of movies in the database.
    public static int size() {
        return ourMovies.size();
    }


    // - A filterBy method
    // has one Filter parameter named f.
    // returns an ArrayList of type String of movie IDs that match the filtering criteria.
    public static ArrayList<String> filterBy(Filter f) {
        initialize();
        ArrayList<String> list = new ArrayList<String>();
        for(String id : ourMovies.keySet()) {
            if (f.satisfies(id)) {
                list.add(id);
            }
        }
        return list;
    }

}
