

// Create a new class named GenreFilter that implements Filter. 
// The constructor should have one parameter named genre representing one genre, 
// and the satisfies method should return true if a movie has this genre. 
// Note that movies may have several genres.

public class GenreFilter implements Filter {
	private String myGenre;
	
	public GenreFilter(String genre) {
		myGenre = genre;
		System.out.println("finding Genre: " + myGenre);
	}
	
	@Override
	public boolean satisfies(String id) {
		// String movieGenre = MovieDatabase.getGenres(id);
		// System.out.println(movieGenre);
		// if(movieGenre.contains(myGenre)){
		// 	System.out.println("with genre");
		// }
		// else {System.out.println("no genre found");}
		// String myStr = "Crime, Drama";
		// System.out.println(myStr.contains("Crime")); 

		return MovieDatabase.getGenres(id).contains(myGenre);
	}
}
