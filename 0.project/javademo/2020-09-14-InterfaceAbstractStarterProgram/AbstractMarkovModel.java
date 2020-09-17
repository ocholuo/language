
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * 
 * @Grace Jyl
 * @version 1
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {

    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }

    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key) {
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
            String next = myText.substring(start+key.length(), start+key.length()+1);
            follows.add(next);
            pos = start+1;
        }
        return follows;
    }
}
