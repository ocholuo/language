
/**
 * Write a description of class MarkovFour here.
 * 
 * @Grace Jyl
 * @version 1
 */

import java.util.ArrayList;
import java.util.Random;
import edu.duke.*;

public class MarkovFour extends AbstractMarkovModel {
	
	public MarkovFour() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();  // all wrds
	}
	
	public String toString(){
	    return "MarkovModel of order 4";
	}
 
	public String getRandomText(int numChars){
		if (myText == null) {
            return "";
        }
		StringBuilder sb = new StringBuilder();

		// generate a random key from the file provided.
		int index = myRandom.nextInt(myText.length()-4);  // search in myText
		String key = myText.substring(index, index+4);    // random start char
		sb.append(key);

		for(int k=0; k < numChars-4; k++){                // add rest of the chars
			ArrayList<String> follows = getFollows(key);
			if(follows.size() == 0){
				break;
			}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			key = key.substring(1) + next;
		}
		return sb.toString();
	}

}