/**
 * class EfficientMarkovWord
 * 
 * @Grace Jyl
 * @version 1
 */


import java.lang.reflect.Array;
import java.util.*;
import edu.duke.*;

public class EfficientMarkovWord implements IMarkovModel{

    private HashMap<WordGram, ArrayList<String>> map;
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    // This method should initialize myOrder and myRandom.
    public EfficientMarkovWord(int theOrder){
        myOrder = theOrder;
        myRandom = new Random();
        map = new HashMap<WordGram, ArrayList<String>>();
    }
    
    // to generate the same random text each time
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    // The void method setTraining has one String parameter named text. The String text is split into words and stored in myText. The words are used to initialize the training text
    public void setTraining(String text) {
        // text = text.replace("[", "");
        // text = text.replace("]", "");
        myText = text.split("\\s+");
        buildMap();
    }

    public String toString(){
        return "EfficientMarkovWord of order " + myOrder;
    }

    // ---------------------------------------------------------
    // k < words.length - myOrder,
    // for each k, new WordGram(words,k,myOrder);
    // compare 2 WordGram
    // ---------------------------------------------------------
    // The indexOf method has three parameters, a String array of all the words in the training text named words, a WordGram named target, and an integer named start indicating where to start looking for a WordGram match in words. 
    // This method should return the first position from start that has words in the array words that match the WordGram target. If there is no such match then return -1.
    public int indexOf(String[] words, WordGram target, int start) {
		for(int k=start; k < words.length - myOrder; k++) {
			WordGram wg = new WordGram(words,k,myOrder);
			if(wg.equals(target)) {
				return k;
			}
		}
        return -1;
    }

    public void buildMap() {
        // ------------------------------------------------
        // this one search from each index, make a kGram
        // kGram!xxx
        // xkGram!xx
        // xxkGram!x
        // ------------------------------------------------
		for(int k=0; k <= myText.length - myOrder; k++) {
            WordGram kGram = new WordGram(myText,k,myOrder);
            System.out.println("the key: " + kGram.toString());
            if(k==myText.length - myOrder){
                // System.out.println("lastone");
                // System.out.println(kGram);
                map.put(kGram, new ArrayList<String>());
            }
            else{
                String nextWrd = myText[k+myOrder];
                if(!map.containsKey(kGram)){
                    map.put(kGram, new ArrayList<String>());
                }
                map.get(kGram).add(nextWrd);
            }
        }
        // ------------------------------------------------
        // for(int i = 0; i < myText.length-myOrder+1; i++){
        //     WordGram kGram = new WordGram(myText,i,myOrder);
        //     if( !map.containsKey(kGram)){
        //         System.out.print(kGram + " start at: ");
        //         ArrayList<String> wrdlist = new ArrayList<String>();
        //         int pos = 0; 
        //         while(pos < myText.length){
        //             int start = indexOf(myText, kGram, pos);
        //             System.out.println(start);
        //             if(start == -1 || (start + kGram.length()) >= myText.length){
        //                 break;
        //             }
        //             else{
        //                 String next = myText[start+myOrder];
        //                 wrdlist.add(next);
        //                 pos = start+1;
        //             }
        //         }
        //         map.put(kGram, wrdlist);
        //     }
        // }
        // System.out.println("buildMap() finished");
    }


    public void printMapInfo() {
        System.out.println("the number of keys in the HashMap: " + map.size());
        int maxCount = 0;
        WordGram maxGram = null;
        for(WordGram kGram : map.keySet()){
            System.out.print("key: " + kGram.toString() + ", \t \t");
            System.out.print("ArrayList<String>: ");
            // to print the each word in ArrayList<String>
            for(String wrd : map.get(kGram)){
                System.out.print(wrd + ",");
            }
            System.out.println();
            if(maxCount < map.get(kGram).size()){
                maxCount = map.get(kGram).size();
                maxGram = kGram;
            }
        }
        System.out.println("--- the size of the largest value in the HashMap: " + maxCount);
        for(WordGram kGram : map.keySet()){
            if(maxCount == map.get(kGram).size()){
                System.out.println("--- the keys that have the maximum size value are: " + kGram);
                System.out.print("--- the maximum size value are: ");
                for(String wrd : map.get(kGram)){
                    System.out.print(wrd + ",");
                }
                System.out.println();
            }
        }
    }

    // for each wrd in myText, start with it, search
    public ArrayList<String> getFollows(WordGram kGram) {
        return map.get(kGram);
    }


    public String getRandomText(int numWords){
        printMapInfo();
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key.toString());
        sb.append(" ");

        for(int i = 0; i < numWords - myOrder; i++){
            if( getFollows(key).size() != 0){
                ArrayList<String> follows = getFollows(key);
                int nextIndex = myRandom.nextInt(follows.size());
                String next = follows.get(nextIndex);
                sb.append(next);
                sb.append(" ");
                key = key.shiftAdd(next);
            }
            else{
                break;
            }
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        FileResource fr = new FileResource("data/confucius.txt"); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        // String st = "this is a test yes this is really a test yes a test this is wow";
        EfficientMarkovWord markovWord = new EfficientMarkovWord(3); 
        markovWord.setTraining(st);
        markovWord.setRandom(621);
        String out = markovWord.getRandomText(200);
        System.out.println(out);
    }
}
