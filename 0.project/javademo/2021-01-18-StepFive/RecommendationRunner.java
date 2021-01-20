import java.util.*;


/**
 * Implement this interface to allow your code to be integrated with our
 * web site.
 * 
 * When users first visit the recommender website, our code will call the
 * method <code>getItemsToRate()</code> to get a list of movies to display
 * on the web page for users to rate.
 * 
 * When a user submits their ratings, our code will call the method <code>
 * printRecommendationsFor</code> to get your recommendations based on the
 * user's ratings.  The ID given to this method is for a new Rater that we 
 * have already added to the RaterDatabase with ratings for the movies 
 * returned by the first method.  Whatever is printed from that method will 
 * be displayed on the web page: HTML, plain text, or debugging information.
 * 
 */
 

 public class RecommendationRunner implements Recommender {
 
    // It returns a list of strings representing movie IDs that will be used to present movies to the user for them to rate. 
    // You can choose how to select movies for this list, for example, you could select recent movies, movies from a specific genre, randomly chosen movies, or something else. 
    // The movies returned will be displayed on a web page, so the number of movies you choose to return may affect how long the page takes to load, and how willing users will be to rate the movies. 
    // 10-20 movies should be fine to get a good profile of the user’s opinions, but 50 may be too many.
    public ArrayList<String> getItemsToRate() {

        AllFilters all = new AllFilters();

        YearAfterFilter year = new YearAfterFilter(1994);
        MinutesFilter minutes = new MinutesFilter(90,200);

        all.addFilter(year);
        all.addFilter(minutes);
        ArrayList<String> myMovies = MovieDatabase.filterBy(all);

        ArrayList<String> myMoviesbase = new ArrayList<String>(); 
        Random randomGenerator = new Random();
        for(int i=0; i<40 ;i++){
            // get a ramdom index
             int index = randomGenerator.nextInt(myMovies.size());
             myMoviesbase.add(myMovies.get(index));
        }
        return myMoviesbase;
    }


    // It prints out an HTML table of movies recommended by your program for the user based on the movies they rated. 
    // It has one parameter webRaterID, a String that is the ID of the user, who has been added by our code to the RaterDatabase with the ratings they entered. 
    // To get the movies recommended by your program, you may want to use your FourthRatings class. Because the HTML printed by this method will be displayed on a webpage, the number of recommended movies you choose to display may affect how long the page takes to load. 
    // For example, you may want to display only the top 10-20 recommended movies, or to not include movies the user rated. 
    // In some rare cases there may not be any recommended movies, for example, if no movies were rated by the number of minimal raters specified in the recommender. If no movies are recommended, you should notify the user with a message. 
    // Whatever is printed by this method will be displayed on the web page: HTML, plain text, or debugging information. 
    // If you want to style this HTML page, please include the CSS styling directly within the page using the <style> tag.
    public void printRecommendationsFor(String webRaterID) {

        FourthRatings frating = new FourthRatings("ratings.csv", "False");
        
        // MovieDatabase moviebase = new MovieDatabase();
        // moviebase.initialize("data/rratedmoviesfull.csv");
        
        MovieDatabase.initialize("data/rratedmoviesfull.csv");

        // RaterDatabase raterbase = new RaterDatabase();
        // raterbase.initialize("data/ratings.csv");

        RaterDatabase.initialize("data/ratings.csv");


        try{
            ArrayList<Rating> recommendations = frating.getSimilarRatings(webRaterID, 5, 2);
            ArrayList<Rating> finalRecommendations = new ArrayList<Rating>();

            //These if statements are for printing just 10 movies if the final list is too large or every movie if the list is between 1 and 10.
            if(recommendations.size()==0){
                System.out.println("Sorry, no movies found for you in the database with these parameters, try to vote again and the program will generate better movies for you to rate ;)");
               }
            if (recommendations.size()>10){
               for(int i=0;i<10;i++){
                   finalRecommendations.add(recommendations.get(i));
               }
               System.out.println("The system recommends you all these movies:");
               System.out.println("<style>th{color:blue}img{  border: 1px solid #ddd;border-radius: 4px;padding: 5px;width: 150px;}</style>");
               System.out.println("<table>");
               System.out.println("<tr><th>Movie</th><th>Poster</th></tr>");
               for(Rating r : finalRecommendations){
                    System.out.println("<tr>");
                    String movieTitle = MovieDatabase.getTitle(r.getItem()); 
                    String poster = MovieDatabase.getPoster(r.getItem());
                    System.out.println("<th>"+movieTitle +"</th>"+"<th>"+"<img src="+poster+">"+"</th>");
                    System.out.println("</tr>");
               }
               System.out.println("</table>");
            }
            else{
                System.out.println("The system recommends you all these movies:");
                System.out.println("<style>th{font-size:150% color:black}#th1{color:blue font-size:175%}img{  border: 1px solid #ddd;border-radius: 4px;padding: 5px;width: 150px;}</style>");
                System.out.println("<table>");
                System.out.println("<tr><th id="+"th1"+">Movie</th><th id="+"th1"+">Poster</th></tr>");
                for(Rating r : recommendations){
                    System.out.println("<tr>");
                    String movieTitle = MovieDatabase.getTitle(r.getItem()); 
                    String poster = MovieDatabase.getPoster(r.getItem());
                    System.out.println("<th>"+movieTitle +"</th>"+"<th>"+"<img src="+poster+">"+"</th>");
                     System.out.println("</tr>");
                }
                System.out.println("</table>");
               }
       }
       catch(Exception e){
           System.out.println(".Please <a link href="+"http://www.dukelearntoprogram.com/capstone/recommender.php?id=MZgRWAHqz6NuLV"+">click here to return</a>.");
       }
   }
   
 }