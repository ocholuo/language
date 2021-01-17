
/**
 * Write a description of class EfficientMarkovModel.
 * 
 * @Grace Jyl
 * @version 1
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import lib.edu.duke.*;

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
    }
    
    // public void setModel(int N){
    //     model = N;
    // }
    
    public String toString(){
	    return "this is the EfficientMarkovModel class of " + n;
    }

    // key: every n char in wrd in myText
    // ArrayList<String> wrdlist = getFollows(key);
    // public void buildMap() {
    //     for(int i = 0; i < myText.length()-n; i++){
    //         String currkey = myText.substring(i, i+n);
    //         if( !map.containsKey(currkey)){
    //             System.out.print("key: " + currkey + " ,\t");
    //             ArrayList<String> wrdlist = getFollows(currkey);
    //             map.put(currkey, wrdlist);
    //             System.out.print("ArrayList<String>: ");
    //             for(String wrd : wrdlist){
    //                 System.out.print(wrd + ",");
    //             }
    //             System.out.println();
    //         }
    //     }
    // }


    // key: every n char in wrd in myText
    // ArrayList<String> wrdlist = getFollows(key);
    public void buildMap() {
        for(int i = 0; i < myText.length()-n+1; i++){
            String currkey = myText.substring(i, i+n);
            if( !map.containsKey(currkey)){
                ArrayList<String> wrdlist = new ArrayList<String>();
                System.out.print("key: " + currkey + " ,\t");
                int pos = 0;
                while(pos < myText.length()){
                    int start = myText.indexOf(currkey, pos);
                    if(start == -1 || (start + currkey.length()) >= myText.length()){
                        break;
                    }
                    String next = myText.substring(start+currkey.length(), start+currkey.length()+1);
                    wrdlist.add(next);
                    pos = start+1;
                }
                map.put(currkey, wrdlist);
                // System.out.print("size: " + wrdlist.size() + "\t -> ArrayList<String>: ");
                // for(String wrd : wrdlist){
                //     System.out.print(wrd + ",");
                // }
                System.out.println();
            }
        }
        System.out.println("buildMap() finished");
        // for(String nWord : map.keySet()){
        //     System.out.println(nWord);
        //     ArrayList<String> followWord = map.get(nWord);
        //     System.out.print("size: " + followWord.size() + "\t -> ArrayList<String>: ");
        //     for(String wrd : followWord){
        //         System.out.print(wrd + ",");
        //     }
        //     System.out.println();
        // }
    }

    // HashMap < wrd, followWrd ArrayList<String>> map
    // buildmap first
    // for key, find max num of key
    public void printHashMapInfo(){
        buildMap();
        System.out.println("Keys in the hashmap: " + map.size() );
        int maxSize =0;
        for(String nWord : map.keySet()){
            int arraysize = map.get(nWord).size();
            if(arraysize > maxSize){
                maxSize = arraysize;
                // System.out.println(maxSize);
            }
        }
        System.out.println("max num of keys = " + maxSize);
        System.out.print("the key is: ");
        for (String s : map.keySet()){
            int arraysize = map.get(s).size();
            if(arraysize == maxSize){
                // String maxkey = map.get(s);
                System.out.print(s + ",");
            }
        }
        System.out.println();
    }
    
    // getFollowsFromHM
    public ArrayList<String> getFollows(String key){
        return map.get(key);
    }

    public String getRandomText(int numChars) {
        buildMap();
        StringBuilder sb = new StringBuilder();
        int indexofKey = myRandom.nextInt(myText.length()-n);
        String key = myText.substring(indexofKey, indexofKey+n);
        // System.out.println(key);
        sb.append(key);

        for(int i = 0; i < numChars - n; i++){
            if( getFollows(key) != null){
                ArrayList<String> follows = getFollows(key);
                // System.out.println(follows);
                // ArrayList<String> follows = getFollows(key);
                // if (follows.size() == 0){
                //     return sb.toString();
                // }
                int nextIndex = myRandom.nextInt(follows.size());
                String nextWrd = follows.get(nextIndex);
                sb.append(nextWrd);
                key = key.substring(1) + nextWrd;
                // System.out.println(sb);
            }
            else{
                break;
            }
        }
        return sb.toString();
    } 
    
    public static void main(String[] args) {
        FileResource fr = new FileResource("data/romeo.txt");
		String st = fr.asString();
        // String st = "yes-this-is-a-thin-pretty-pink-thistle";
        st = st.replace('\n', ' ');  // "yes this is a thin pretty pink thistle"

        EfficientMarkovModel markov = new EfficientMarkovModel(5);
        // System.out.println(markov);

		markov.setTraining(st);
        markov.setRandom(615);
        
        // System.out.println("buildMap(): ");
        // markov.buildMap();
        System.out.println("printHashMapInfo(): ");
        markov.printHashMapInfo();

        // System.out.println("getFollows(): ");
        // String key = "ye";
        // ArrayList<String> output = markov.getFollows(key);
        // System.out.println(output);

        // markov.setRandom(42);
        // String phase = markov.getRandomText(50);
        // System.out.println(phase);

    }
}
