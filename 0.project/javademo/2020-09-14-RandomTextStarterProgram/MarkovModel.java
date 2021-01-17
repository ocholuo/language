
/**
 * Write a description of class MarkovModel.
 * 
 * @Grace Jyl
 * @version 1
 */


import java.util.ArrayList;
import java.util.Random;
import lib.edu.duke.*;

public class MarkovModel {

    private String myText;
    private Random myRandom;
    private int n;
    
    public MarkovModel(){
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

	public void setTraining(String s){
		myText = s.trim();  // all wrds
	}

	public void setModel(int N){
		n = N;
    }
    
    public String getRandomText(int numChars) {
        if(myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int indexofKey = myRandom.nextInt(myText.length()-n);
        String key = myText.substring(indexofKey, indexofKey+n);

        sb.append(key);

        for(int i = 0; i < numChars - n; i++){
            ArrayList<String> follows = getFollows(key);
            int nextIndex = myRandom.nextInt(follows.size());
            String nextWrd = follows.get(nextIndex);
            sb.append(nextWrd);
            key = key.substring(1) + nextWrd;
        }
        return sb.toString();
    }

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length()){
            int start = myText.indexOf(key, pos);
            if(start == -1){
                break;
            }
            if(start + key.length() >= myText.length()){
                break;
            }
            String next = myText.substring(start+n, start+n+1);
            follows.add(next);
            pos = start+1;
        }
        return follows;
    }

}
