
/**
 * Write a description of class MarkovZero here.
 * 
 * @Grace Jyl
 * @version 1
 */
 
import java.util.ArrayList;
import java.util.Random;
import edu.duke.*;

public class MarkovOne extends AbstractMarkovModel {

	public MarkovOne() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();  // all wrds
	}
	
	public String toString(){
	    return "MarkovModel of order 1";
	}
	
	public String getRandomText(int numChars){
		if (myText == null) {
            return "";
        }
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-1);  // search in myText
		String key = myText.substring(index, index+1);    // random start char
		sb.append(key);

		for(int k=0; k < numChars-1; k++){                // add rest of the chars
			ArrayList<String> follows = getFollows(key);
			if(follows.size() == 0){
				break;
			}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			key = next;
		}
		return sb.toString();
	}

}