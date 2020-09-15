
/**
 * Write a description of class EfficientMarkovModel.
 * 
 * @Grace Jyl
 * @version 1
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import edu.duke.*;

public class EfficientMarkovModel extends AbstractMarkovModel {

    private int n;
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int N){
        myRandom = new Random();
        n = N;
        map = new HashMap<String, ArrayList<String>>();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

	public void setTraining(String s){
        myText = s.trim();  // all wrds
        buildMap();
    }
    
    // public void setModel(int N){
    //     model = N;
    // }
    

    public String toString(){
	    return "this is the EfficientMarkovModel class of " + n;
    }
    

    public void buildMap() {
        for(int i = 0; i < myText.length()-n; i++){
            String currkey = myText.substring(i, i+n);
            if( !map.containsKey(currkey)){
                ArrayList<String> wrdlist = getFollows(currkey);
                map.put(currkey, wrdlist);
            }
        }
    }

    public void printHashMapInfo(){
        buildMap();
        System.out.println("Keys in the hashmap: "+(map.size()+1));
        int index =0;
        String maxkey = "";
        for (String s : map.keySet()){
            if(map.get(s).size()> index){
                index = map.get(s).size();
                maxkey = s;
            }
            // System.out.println(s + "  " + map.get(s));
        }
        System.out.println("max num of keys = " +index);
        System.out.println("the key is this: " + maxkey);
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


    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = map.get(key);
        return follows;
    }
}
