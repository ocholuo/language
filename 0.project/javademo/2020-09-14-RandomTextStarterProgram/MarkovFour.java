
/**
 * Write a description of class MarkovFour here.
 * 
 * @Grace Jyl
 * @version 1
 */

import java.util.ArrayList;
import java.util.Random;
import lib.edu.duke.*;

public class MarkovFour {

    private String myText;
	private Random myRandom;
	
	public MarkovFour() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();  // all wrds
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

	public ArrayList<String> getFollows(String key) {
		ArrayList<String> follows = new ArrayList<String>();
		// for(int i=0; i < myText.length()-4; i++){
		// 	if(myText.substring(i, i+4).equals(key)){
		// 		String nextchar = myText.substring(i+4, i+5);
		// 		follows.add(nextchar);
		// 	}
		// }
		int pos = 0;
		while(pos < myText.length()){
			int start = myText.indexOf(key, pos);
			if(start == -1){
				break;
			}
			if(start + key.length() >= myText.length()-1){
				break;
			}
			String next = myText.substring(start+key.length(), start+key.length()+1);
			follows.add(next);
			pos = start+key.length();
		}
		return follows;
	}

}