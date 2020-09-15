
/**
 * Write a description of class MarkovZero here.
 * 
 * @Grace Jyl
 * @version 1
 */

import java.util.ArrayList;
import java.util.Random;

public class MarkovTwo {

    private String myText;
	private Random myRandom;
	
	public MarkovTwo() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();  // all wrds
	}
	
	public String getRandomText(int numChars){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-2);  // search in myText for 2 char
		String key = myText.substring(index, index+2);    // random start 2 char
		sb.append(key);

		for(int k=0; k < numChars-2; k++){                // add rest of the chars
			ArrayList<String> follows = getFollows(key);
			if(follows.size() == 0){
				break;
			}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			// key = sb.substring(k+1, k+3);
			key = key.substring(1) + next;
		}
		return sb.toString();
	}

	public ArrayList<String> getFollows(String key) {
		ArrayList<String> follows = new ArrayList<String>();

		// for(int i=0; i < myText.length()-1; i++){
		// 	if(myText.substring(i, i+2).equals(key)){
		// 		String nextchar = myText.substring(i+2, i+3);
		// 		follows.add(nextchar);
		// 	}
		// }

		int pos = 0;
		while(pos < myText.length()){
			int start = myText.indexOf(key, pos);
			if(start == -1){
				break;
			}
			if(start + key.length() >= myText.length()){
				break;
			}
			String next = myText.substring(start+key.length(), start+key.length()+1);
			follows.add(next);
			pos = start+1;
		}
		return follows;
	}

}