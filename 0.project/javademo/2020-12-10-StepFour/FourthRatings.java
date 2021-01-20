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


// ArrayList<Rater> myRaters;
// <Rater1, Rater2, Rater3 ..... >
// Rater:
// String raterID;
// HashMap<String,Rating> ratings;


public class FourthRatings {

    // private variable: myRaters, store an ArrayList of Raters.
    private ArrayList<Rater> myRaters;

    // default constructor
    public FourthRatings() {
        this("data/ratings_short.csv", "True");
    }
 
    // second constructor
    // only one String parameter named ratingsfile. 
    // call the method RaterDatabase.getRaters() to fill the myRaters ArrayList.
    public FourthRatings(String ratingsfile, String trueorFalse) { 
        RaterDatabase.initialize(ratingsfile);
        // call RaterDatabase.initialize(ratingsfile);
        //        ourRaters = new HashMap<String raterID,Rater>();
        // call RaterDatabase.addRatings(ratingsfile);
        //        csv got the raterID, movieID, rating in ratingsfile
        // call RaterDatabase.addRaterRating(raterID,movieID,Double.parseDouble(rating));
        //        check rater id in ourRaters HashMap:
        //              yes: rater = ourRaters.get(raterID);
        //              no: rater = create EfficientRater; ourRaters.put(raterID,rater);
        //        EfficientRater.addRating(movieID,rating);

        myRaters = RaterDatabase.getRaters();
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


    // This method returns a double representing the average movie rating for this ID if there are at least minimalRaters ratings. 
    // If there are not minimalRaters ratings, then it returns 0.0.
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



    // private helper method named dotProduct
    // two parameters, a Rater named me and a Rater named r. 
    // This method should first translate a rating from the scale 0 to 10 to the scale -5 to 5 and return the dot product of the ratings of movies that they both rated. 
    // This method will be called by getSimilarities.
    // This method returns a double value with the affinity between two Raters. The higher the number, the higher the affinity as well 

    private double dotProduct(Rater me, Rater r) {
        // System.out.println(" ------------------ dotProduct() ------------------ ");
        double dotProduct = 0;

        HashMap<String, Rating> myRatings = me.getRatings();
        // HashMap<String movieID, Rating>
        HashMap<String, Rating> rRatings = r.getRatings();
        // System.out.println("    MyRatings has " +myRatings.size()+ " movies");
        // System.out.println("    rRatings has " + rRatings.size() + " movies");

        HashMap<String, Double> movieList = new HashMap<String, Double>();

        // follow myRatings index, check if movieID(key) is in rRatings's key: 
        // calculate the close by the movie been rated by both
        for(String movieID : myRatings.keySet()){
            // System.out.println("    my movie: " + movieID);
            if (rRatings.keySet().contains(movieID)){
                double myValue = myRatings.get(movieID).getValue();
                double rValue = rRatings.get(movieID).getValue();
                double avgValue = (myValue-5)*(rValue-5);
                movieList.put(movieID, avgValue);
                dotProduct += avgValue;
                // System.out.println(" --- found movie been rated by Rater " + r.getID());
                // System.out.println(" --- myValue: " + myValue + ", rValue: " + rValue + ", avgValue: " + avgValue);
                // System.out.println(" --- dotProduct: " + dotProduct);
            }
        }
        return dotProduct;
    }


    // private method named getSimilarities
    // computes a similarity rating for each rater in the RaterDatabase (except the rater with the ID given by the parameter) to see how similar they are to the Rater whose ID is the parameter to getSimilarities. 
    // This method returns an ArrayList of type Rating <rater’s ID, dot product comparison>
    // sorted by ratings from highest to lowest rating 
    // highest rating first and only including those raters who have a positive similarity  
    private ArrayList<Rating> getSimilarities(String id) {
        // System.out.println(" ------------------ getSimilarities() ------------------ ");
        ArrayList<Rating> similarRatings = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        
        for (Rater r : myRaters){ 
            if(!r.getID().equals(id)){ 
                // System.out.println(" -------- calculate the closeness betwenn " + id + " and " + r.getID());
                double closenessNum = dotProduct(me, r);
                // System.out.println("closeness is " + closenessNum);
                if(closenessNum>0){ 
                    similarRatings.add(new Rating(r.getID(), closenessNum));
                }
            }
        }

        Collections.sort(similarRatings,Collections.reverseOrder());
        // print info
        // for (Rating r : similarRatings) {
        //     System.out.println("Rater" + r.getItem() + ", the closeness: " + r.getValue());
        // }

        return similarRatings;
    }


    // public method named getSimilarRatings
    // three parameters: 
    // a String named id representing a raterID, 
    // an integer named numSimilarRaters, 
    // and an integer named minimalRaters. 

    // return an ArrayList of type Rating, of movies and their weighted average ratings 
    //    - using only the top numSimilarRaters with positive ratings 
    //    - including only those movies that have at least minimalRaters ratings from those most similar raters 
    //    - (not just minimalRaters ratings overall). 
    // For example, 
    // if minimalRaters is 3 and a movie has 4 ratings but only 2 of those ratings were made by raters in the top numSimilarRaters, that movie should not be included. 
    // These Rating objects should be returned in sorted order by weighted average rating from largest to smallest ratings. 
    // This method is very much like the getAverageRatings method have written previously. In particular this method should:
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        System.out.println(" ------------------ getSimilarRatings() ------------------ ");
        
        // Rater me = RaterDatabase.getRater(id); 

        MovieDatabase.initialize("data/ratedmovies_short.csv");
        ArrayList<String> movieIDList = MovieDatabase.getMovies();
        // ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        // HashMap<String, Rating> myMovies = me.getRatings();
        // <movie ID, movieID, rating>


        // For every rater, get their similarity rating to the given parameter rater id. Include only those raters with positive similarity ratings
        ArrayList<Rating> similarRatings = getSimilarities(id);
            // ArrayList<Rating>:
            //    - rating:
            //    - rater’s ID, dot product comparison


        // calculate the moviesNumList: in the top numSimilarRaters, calculate same movie number,
        HashMap<String, Integer> moviesNumList = new HashMap<String, Integer>();
        HashMap<String, Double> moviesWeight = new HashMap<String, Double>();

        
        for(int i=0; i<numSimilarRaters; i++) {

            String nextRaterID = similarRatings.get(i).getItem();
            double close = similarRatings.get(i).getValue();
            Rater nextRater = RaterDatabase.getRater(nextRaterID); 

            // iterrate every movie
            for(String movieID : movieIDList) {
                // if movie is in rater's movie list, movieNum + 1
                if (nextRater.hasRating(movieID)) {
                    
                    double nextValue = nextRater.getRating(movieID);
                    double weightValue = nextValue*close;

                    if (!moviesNumList.containsKey(movieID)) {
                        moviesNumList.put(movieID, 0);
                        moviesWeight.put(movieID, weightValue);
                    }
                    else {
                        double currWeight = moviesWeight.get(movieID);
                        // calculate the weighted average rating
                        moviesWeight.put(movieID, (currWeight+weightValue)/2);
                    }

                    int currNum = moviesNumList.get(movieID);
                    moviesNumList.put(movieID, currNum+1);

                    // System.out.println("movie " + movieID + " been rated by " + nextRaterID);
                    // System.out.println("closeness: " + close + ", rating: " + nextValue + ", weighted: " + moviesWeight.get(movieID));
                }
            }
        } 

        // print detailed:
        // System.out.println( moviesNumList.size() + " movies has been rated by top" + numSimilarRaters + " close Rater, with more than " + minimalRaters + " rating.");
        // for (String movieID : moviesNumList.keySet()) {
        //     System.out.println("movie" + movieID + " has been rated " + moviesNumList.get(movieID) + ", Weighted: " + moviesWeight.get(movieID));
        // }


        try {
            ArrayList<Rating> anwList = new ArrayList<Rating>();
            for (String movieID : moviesNumList.keySet()) {
                if (moviesNumList.get(movieID) >= minimalRaters) {
                    anwList.add(new Rating(movieID, moviesWeight.get(movieID)));
                }
            }
            // sort them to have a rational TOP:
            Collections.sort(anwList,Collections.reverseOrder());
            return anwList;
        }
        
        catch (Exception e){
            System.out.println("One of the variables is out of bounds, insert smaller parameter variables or another user");
            return null;
        }
    }




    // public method getSimilarRatingsByFilter
    // similar to the getSimilarRatings method but has one additional Filter parameter named filterCriteria and uses that filter to access and rate only those movies that match the filter criteria. 

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) { 
        // System.out.println(" ------------------ getSimilarRatings(with filter) ------------------ ");
         
        MovieDatabase.initialize("data/ratedmovies_short.csv");
        ArrayList<String> movieIDList = MovieDatabase.filterBy(filterCriteria);
        // <movie ID> 


        // For every rater, get their similarity rating to the given parameter rater id. Include only those raters with positive similarity ratings
        ArrayList<Rating> similarRatings = getSimilarities(id);
            // ArrayList<Rating>:
            //    - rating:
            //    - rater’s ID, dot product comparison


        // calculate the moviesNumList: in the top numSimilarRaters, calculate same movie number,
        HashMap<String, Integer> moviesNumList = new HashMap<String, Integer>();
        HashMap<String, Double> moviesWeight = new HashMap<String, Double>();

        
        for(int i=0; i<numSimilarRaters; i++) {

            String nextRaterID = similarRatings.get(i).getItem();
            double close = similarRatings.get(i).getValue();
            Rater nextRater = RaterDatabase.getRater(nextRaterID); 

            // iterrate every movie
            for(String movieID : movieIDList) {
                // if movie is in rater's movie list, movieNum + 1
                if (nextRater.hasRating(movieID)) {
                    
                    double nextValue = nextRater.getRating(movieID);
                    double weightValue = nextValue*close;

                    if (!moviesNumList.containsKey(movieID)) {
                        moviesNumList.put(movieID, 0);
                        moviesWeight.put(movieID, weightValue);
                    }
                    else {
                        double currWeight = moviesWeight.get(movieID);
                        // calculate the weighted average rating
                        moviesWeight.put(movieID, (currWeight+weightValue)/2);
                    }
                    
                    int currNum = moviesNumList.get(movieID);
                    moviesNumList.put(movieID, currNum+1);

                    // System.out.println("movie " + movieID + " been rated by " + nextRaterID);
                    // System.out.println("closeness: " + close + ", rating: " + nextValue + ", weighted: " + moviesWeight.get(movieID));
                }
            }
        } 

        // print detailed:
        // System.out.println( moviesNumList.size() + " movies has been rated by top" + numSimilarRaters + " close Rater, with more than " + minimalRaters + " rating.");
        // for (String movieID : moviesNumList.keySet()) {
        //     System.out.println("movie" + movieID + " has been rated " + moviesNumList.get(movieID) + ", Weighted: " + moviesWeight.get(movieID));
        // }


        try {
            ArrayList<Rating> anwList = new ArrayList<Rating>();
            for (String movieID : moviesNumList.keySet()) {
                if (moviesNumList.get(movieID) >= minimalRaters) {
                    anwList.add(new Rating(movieID, moviesWeight.get(movieID)));
                }
            }
            // sort them to have a rational TOP:
            Collections.sort(anwList,Collections.reverseOrder());
            return anwList;
        }
        
        catch (Exception e){
            System.out.println("One of the variables is out of bounds, insert smaller parameter variables or another user");
            return null;
        }
    }

        
    // public static void main(String[] args) {
    //     FourthRatings pr = new FourthRatings("data/test1.csv", "True");
    //     Rater a = RaterDatabase.getRater("15");
    //     Rater b = RaterDatabase.getRater("20"); 
    //     double num = pr.dotProduct(a, b);
    //     System.out.println(num);
    //     // FourthRatings pr = new FourthRatings("data/ratings_short.csv", "True");
    //     // ArrayList<Rating> tainglist = pr.getSimilarRatings("1", 2, 1);
    // }
}

