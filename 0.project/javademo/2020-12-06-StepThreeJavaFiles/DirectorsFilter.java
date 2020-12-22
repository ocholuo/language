import java.util.ArrayList;

// Create a new class named DirectorsFilter that implements Filter. 
// The constructor should have one parameter named directors representing a list of directors separated by commas (Example: "Charles Chaplin,Michael Mann,Spike Jonze"), and its satisfies method should return true if a movie has at least one of these directors as one of its directors. 

public class DirectorsFilter implements Filter {
	private String myDir;
	
	public DirectorsFilter(String directors) {
		myDir = directors;
	}
	
	@Override
	public boolean satisfies(String id) {
		String currDir = MovieDatabase.getDirector(id);
		for(String directorName : myDir.split(",")) {
			if(currDir.contains(directorName)) {
				return true;
			}
		}
		return false;
	}
}

