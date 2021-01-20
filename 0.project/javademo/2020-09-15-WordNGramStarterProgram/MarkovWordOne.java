
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @Grace Jyl
 * @version 1
 */

import java.lang.reflect.Array;
import java.util.*;
import edu.duke.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}

	private int indexOf(String[] words, String key, int from){
		for(int i = from; i < words.length; i++){
			if( words[i].equals(key)){
				return i;
			}
		}
		return -1;
	}
	
	public void textindexOf() {
		String[] myText = {"this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test"};
		System.out.println(indexOf(myText, "this", 0));
		System.out.println(indexOf(myText, "this", 3));
		System.out.println(indexOf(myText, "frog", 0));
		System.out.println(indexOf(myText, "this", 5));
		System.out.println(indexOf(myText, "simple", 2));
		System.out.println(indexOf(myText, "test", 5));
   	}
	

   	public ArrayList<String> getFollows(String key) {
		// String[] myText = {"this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test"};
		// System.out.println("key: " + key);
		ArrayList<String> follows = new ArrayList<String>();
		int pos = 0;
		while(pos < myText.length){
			int startIndex = indexOf(myText, key, pos);
			if(startIndex == -1 || (startIndex + key.length()) >= myText.length){
				break;
			}
			String next = myText[startIndex+1];
			follows.add(next);
			// System.out.print(next + ",");
			pos = startIndex+1;
		}
		// System.out.println();
		return follows;
	}

	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");

		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		return sb.toString().trim();
	}
	
	public static void main(String[] args) {

        FileResource fr = new FileResource("data/confucius.txt"); 
        String st = fr.asString(); 
		st = st.replace('\n', ' '); 
		
		MarkovWordOne markov = new MarkovWordOne();

		// markov.textindexOf();
		// ArrayList<String> follows = markov.getFollows("is");


		markov.setTraining(st);
		String out = markov.getRandomText(120);
		System.out.println(out);

	}

}
