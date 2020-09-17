
/**
 * Write a description of class MarkovWordTwo here.
 * 
 * @Grace Jyl
 * @version 1
 */

import java.lang.reflect.Array;
import java.util.*;
import edu.duke.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}

	private int indexOf(String[] words, String key1, String key2, int from){
		for(int i = from; i < words.length; i++){
			if( words[i].equals(key1) && words[i+1].equals(key2)){
				return i;
			}
		}
		return -1;
	}

   	public ArrayList<String> getFollows(String key1, String key2) {
		// String[] myText = {"this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test"};
		// System.out.println("key: " + key1 + " " + key2);
		ArrayList<String> follows = new ArrayList<String>();
		int pos = 0;
		while(pos < myText.length){
			int startIndex = indexOf(myText, key1, key2, pos);
			if(startIndex == -1) {
				break;
			}
			if(startIndex >= myText.length - 1) {
				break;
			}
			String next = myText[startIndex+2];
			follows.add(next);
			// System.out.print(next + ",");
			pos = startIndex+1;
		}
		// System.out.println();
		return follows;
	}

	public String getRandomText(int numWords){
		// myText = {"this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test"};
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-2);  // random word to start with
		String key1 = myText[index];
		String key2 = myText[index+1];
		System.out.println(key1 + "," + key2);
		sb.append(key1);
		sb.append(" ");
		sb.append(key2);
		sb.append(" ");

		for(int k=0; k < numWords-2; k++){
		    ArrayList<String> follows = getFollows(key1, key2);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key1 = key2;
			key2 = next;
		}
		return sb.toString().trim();
	}
	
	public static void main(String[] args) {

        FileResource fr = new FileResource("data/confucius.txt"); 
        String st = fr.asString(); 
		st = st.replace('\n', ' '); 
		
		MarkovWordTwo markov = new MarkovWordTwo();

		// ArrayList<String> follows = markov.getFollows("this", "is");

		markov.setTraining(st);
		String out = markov.getRandomText(549);
		System.out.println(out);

	}

}
