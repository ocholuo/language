
/**
 * Write a description of class MarkovModel.
 * 
 * @Grace Jyl
 * @version 1
 */


import java.util.ArrayList;
import java.util.Random;
import edu.duke.*;

public class MarkovModel extends AbstractMarkovModel {

    private int n;
    
    public MarkovModel(int N){
        myRandom = new Random();
        n = N;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

	public void setTraining(String s){
        myText = s.trim();  // all wrds
        System.out.println(myText);
    }
    
    // public void setModel(int N){
    //     model = N;
    // }
    
    public String toString(){
	    return "MarkovModel of order " + n;
    }
    
    public String getRandomText(int numChars) {
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

}
