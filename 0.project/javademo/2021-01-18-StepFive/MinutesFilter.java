

// Create a new class named MinutesFilter that implements Filter. 
// return true if a movie’s running time is at least min minutes and no more than max minutes.  

public class MinutesFilter implements Filter {
	private int myMin;
	private int myMax;
	
	public MinutesFilter(int min, int max) {
		myMin = min;
		myMax = max;
		System.out.println("time period: " + myMin + " - " + myMax);
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

		return myMin <= MovieDatabase.getMinutes(id) && MovieDatabase.getMinutes(id) <= myMax;
	}
}
