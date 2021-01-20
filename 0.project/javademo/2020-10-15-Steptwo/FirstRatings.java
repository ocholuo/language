

/**
 * This class has been created as a requirement for this Assignment.
 *
 * @grace
 * @1.1
 */


import java.security.Principal;
import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;


public class FirstRatings{

    // loadMovies -> movieData [Movie, Movie, Movie, Movie, ...]
    // id,title,year,country,genre,director,minutes,poster
    // 0006414,"Screen",1916,"USA","Short, Comedy",30,"http://...jpg"
    // return an ArrayList of type Movie with all of the movie data from the file.
    public ArrayList<Movie> loadMovies(String filename){
        FileResource fr = new FileResource("data/" + filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> movieData = new ArrayList<Movie>();

        try {
            for(CSVRecord csvr : parser.getRecords()) {
                //Making a temp movie variable to make a Movie Object with data in csvr, this will be added to movieData
                Movie temp = new Movie(csvr.get("id"), csvr.get("title"), csvr.get("year"), csvr.get("genre"),
                    csvr.get("director"), csvr.get("country"), csvr.get("poster"), Integer.parseInt(csvr.get("minutes").trim()));
                //Adding temp to movieData
                movieData.add(temp);
                // System.out.println(temp);
            }
        } catch(Exception ioe) {
            System.out.println("IOException caught");
        }
        //Returning an ArrayList of type Movie with data processed from file [filename]
        return movieData;
    }


    // movieData [Movie, Movie, Movie, Movie, ...]
    public void testLoadMovies(){
        // String filename = "ratedmovies_short.csv";
        // String filename = "ratedmoviesfull.csv";
        String filename = "test.movie.csv";
        ArrayList<Movie> movieData = loadMovies(filename);

        System.out.println("the number of movies: " + movieData.size());
        System.out.println("print each movie: ");
        for(Movie i:movieData){
            System.out.print(i.getTitle() + ", ");
        }
        System.out.println();

        int ccount=0;
        int lcount=0;
        int mNum = 0;
        HashMap<String, Integer> dmap = new HashMap<String, Integer>();
        HashSet<String> directorsWithMaxMovies = new HashSet<String>();
        for(Movie i:movieData){

            // how many movies include the Comedy genre.
            if(i.getGenres().contains("Comedy")){
                ccount+=1;
            }
            // how many movies are greater than 150 minutes in length
            if(i.getMinutes() > 150){
                lcount+=1;
            }

            // determine the maximum number of movies by any director, and who the directors are that directed that many movies.
            String[] directors = i.getDirector().replaceAll(", ", "").split(",");
            for(String dname : directors) {
                if(dmap.containsKey(dname)) {
                    dmap.put(dname, dmap.get(dname) + 1);
                } else {
                    dmap.put(dname, 1);
                }
                mNum = dmap.get(dname) > mNum? dmap.get(dname):mNum;
            }
        }
        for(String dname : dmap.keySet()) {
            if(dmap.get(dname) == mNum) {
                directorsWithMaxMovies.add(dname);
            }
        }
        System.out.print("how many movies include the Comedy genre: ");
        System.out.println(ccount);


        System.out.print("how many movies are greater than 150 minutes in length: ");
        System.out.println(lcount);


        System.out.println("the maximum number of movies by any director is " + mNum);
        System.out.println(directorsWithMaxMovies.size() + " directors are directed that many movies are: ");
        for(String dir : directorsWithMaxMovies) {
            // System.out.println(dir + ", ");
            String[] questionL = {"Ridley Scott", "Alfred Hitchcock", "Steven Spielberg", "Woody Allen", "Martin Scorsese"};
            for(String ans : questionL){
                if(ans.equals(dir)){
                    System.out.println(dir);
                    System.out.println(dmap.get(dir));
                }
            }
        }
        System.out.println();
    }



    // -> raterData [Rater, Rater, Rater, ...]
    // -> raterList [String: [Rating, Rating, Rating], String: [Rating, Rating, Rating], ...]
                    // rater_id,movie_id,rating,time
                    // 1,0068646,10,1381620027
    // process every record from the CSV file, a file of raters and their ratings, and return an ArrayList of type Rater with all the rater data from the file.
    public ArrayList<Rater> loadRaters(String filename){
        FileResource fr = new FileResource("data/" + filename);
        CSVParser pr = fr.getCSVParser();

        ArrayList<Rater> raterData = new ArrayList<Rater>();
        HashMap<String, ArrayList<Rating>> raterList = new HashMap<String, ArrayList<Rating>>();

        try {
            for(CSVRecord csvr : pr.getRecords()){
                String ratingItem = csvr.get("movie_id");
                double ratingValue = Double.parseDouble(csvr.get("rating"));
                String raterID = csvr.get("rater_id");
                Rating tempRating = new Rating(ratingItem, ratingValue);
                // System.out.println(raterID);
                if(raterList.containsKey(raterID)){
                    // System.out.println("has");
                    raterList.get(raterID).add(tempRating);
                }
                else{
                    // System.out.println("dont have");
                    raterList.put(raterID, new ArrayList<Rating>());
                    raterList.get(raterID).add(tempRating);
                }
            }
            for(String rname : raterList.keySet()){
                Rater r = new Rater(rname);
                for(Rating values : raterList.get(rname)){
                    r.addRating(values.getItem(), values.getValue());
                }
                raterData.add(r);
            }
        }
        catch(Exception ioe) {
            System.out.println("IOException caught");
        }
        return raterData;
    }


    // -> raterData [Rater, Rater, Rater, ...]
    // -> raterList [String: [Rating, Rating, Rating], String: [Rating, Rating, Rating], ...]
                    // rater_id,movie_id,rating,time
                    // 1,0068646,10,1381620027
    public void testLoadRaters(int raterID, String movieID){
        // String filename = "data/ratings_short.csv";
        String filename = "data/ratings.csv";
        ArrayList<Rater> raterData = loadRaters(filename);

        String targetid = Integer.toString(raterID);
        int mNum = 0;

        // Print the total number of raters.
        System.out.println("the total number of raters: " + raterData.size());

        for(Rater i : raterData){

            System.out.println("==============================================");

            String id = i.getID();
            ArrayList<String> itemL = i.getItemsRated();

            // Then for each rater, print the rater’s ID and the number of ratings they did on one line, followed by each rating (both the movie ID and the rating given) on a separate line.
            // System.out.println("the rater’s ID: " + id);
            // System.out.println("    the number of ratings they did: " + itemL.size());
            // for(String item : itemL){
            //     double ratingValue = i.getRating(item);
            //     System.out.print("      movie ID: " + item);
            //     System.out.println(". the rating given: " + ratingValue);
            // }
            // System.out.println("==============================================");


            // Add code to find the number of ratings for a particular rater you specify in your code.
            // For example, if you run this code on the rater whose rater_id is 2 for the file ratings_short.csv, you will see they have three ratings.
            if(id.equals(targetid)){
                System.out.println("the target rater_id: "+ targetid);
                System.out.println("the rater has " + itemL.size() + " ratings: ");
                for(String item : itemL){
                    double ratingValue = i.getRating(item);
                    System.out.print("    movie ID: " + item);
                    System.out.println(". the rating given: " + ratingValue);
                }
            }
            mNum = itemL.size()>mNum? itemL.size():mNum;
        }
        System.out.println("==============================================");


        // Add code to find the maximum number of ratings by any rater. Determine how many raters have this maximum number of ratings and who those raters are.
        // If you run this code on the file ratings_short.csv, you will see rater 2 has three ratings, the maximum number of ratings of all the raters, and that there is only one rater with three ratings.
        System.out.println("the maximum number of ratings by any rateris: "+mNum);
        System.out.print("who those raters are: ");
        for(Rater i : raterData){
            if(i.getItemsRated().size()==mNum){
                System.out.print(i.getID() + ", ");
            }
        }
        System.out.println();
        System.out.println("==============================================");



        // Add code to find the number of ratings a particular movie has.
        // If you run this code on the file ratings_short.csv for the movie “1798709”, you will see it was rated by four raters.
        int ratercount = 0;
        for(Rater i : raterData){
            if(i.hasRating(movieID)){
                ratercount += 1;
            }
        }
        System.out.println("Movie " + movieID + " was rated by " + ratercount + " raters.");
        System.out.println("==============================================");



        // Add code to determine how many different movies have been rated by all these raters.
        // If you run this code on the file ratings_short.csv, you will see there were four movies rated.
        ArrayList<String> movieL = new ArrayList<String>();
        for(Rater i : raterData){
            ArrayList<String> itemL = i.getItemsRated();
            for( String moviename : itemL){
                if(!movieL.contains(moviename)){
                    movieL.add(moviename);
                }
            }
        }
        System.out.println("In " + filename + ": there were " + movieL.size() + " movies rated.");
    }


    public static void main(String[] args) {

        FirstRatings pr = new FirstRatings();

        pr.testLoadMovies();
        // pr.testLoadRaters(193, "1798709");
    }

}
