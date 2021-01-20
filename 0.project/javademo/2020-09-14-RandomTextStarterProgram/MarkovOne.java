
/**
 * Write a description of class MarkovZero here.
 * 
 * @Grace Jyl
 * @version 1
 */

import java.util.ArrayList;
import java.util.Random;
import edu.duke.*;

public class MarkovOne {

    private String myText;
	private Random myRandom;
	
	public MarkovOne() {
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

	public ArrayList<String> getFollows(String key) {
		ArrayList<String> follows = new ArrayList<String>();
		for(int i=0; i < myText.length()-1; i++){
			if(myText.substring(i, i+1).equals(key)){
				String nextchar = myText.substring(i+1, i+2);
				follows.add(nextchar);
			}
		}
		return follows;
	}


	// public static void main(String[] args) {

	// 	// FileResource fr = new FileResource();
	// 	// String st = fr.asString();
	// 	// st = st.replace('\n', ' ');
	// 	String st = "this is a test yes this is a test.";

	// 	MarkovOne markov = new MarkovOne();
	// 	markov.setTraining(st);

	// 	System.out.println("test getFollows():");
	// 	ArrayList<String> follows = markov.getFollows("es");
	// 	for(String wrd : follows){
	// 		System.out.print(wrd + ",");
	// 	}


	// }
}