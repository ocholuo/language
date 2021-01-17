/**
 * class MarkovWord
 * 
 * @Grace Jyl
 * @version 1
 */


import java.lang.reflect.Array;
import java.util.*;
import lib.edu.duke.*;

public class MarkovWord implements IMarkovModel{

    private String[] myText;
    private Random myRandom;
    private int myOrder;

    // This method should initialize myOrder and myRandom.
    public MarkovWord(int theOrder){
        myOrder = theOrder;
        myRandom = new Random();
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
    }

    public String toString(){
        return "MarkovWord of order " + myOrder;
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

    // for each wrd in myText, start with it, search
    public ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> out = new ArrayList<String>();
        // System.out.println(kGram.toString());
        for(int i = 0; i < myText.length-1; i++){
            int start = indexOf(myText, kGram, i);
            if(start != -1){
                // String nextWrd = myText[start + kGram.length()];
                String nextWrd = myText[start+myOrder];
                if(!out.contains(nextWrd)){
                    out.add(nextWrd); 
                }
            }
            else{
                break;
            }
        }
        // System.out.println(out);
        return out;
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key.toString());
        sb.append(" ");

        for(int i = 0; i < numWords - myOrder; i++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            int nextIndex = myRandom.nextInt(follows.size());
            String next = follows.get(nextIndex);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }

    // code from github: does not work
    // private ArrayList<String> getFollows(WordGram kGram) {
    //     ArrayList<String> follows = new ArrayList<String>();
    //     int pos =0;
    //     while(pos < myText.length){
    //         int start = indexOf(myText,kGram,pos);
    //         if (start == -1){
    //            break;
    //         }
    //         if (start >= myText.length-1){
    //            break;  
    //         }
    //         String next = myText[start+myOrder];
    //         follows.add(next);
    //         pos = start+1;
    //     }
    //     return follows;
    // }

    // public String getRandomText(int numWords){
    //     StringBuilder sb = new StringBuilder();
    //     int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
    //     WordGram key = new WordGram(myText,index,myOrder);
    //     sb.append(key.toString());
    //     sb.append(" ");
    //     for(int k=0; k < numWords-myOrder; k++){
    //         ArrayList<String> follows = getFollows(key);
    //         if (follows.size() == 0) {
    //             break;
    //         }
    //         index = myRandom.nextInt(follows.size());
    //         String next = follows.get(index);
    //         sb.append(next);
    //         sb.append(" ");
    //         key = key.shiftAdd(next);
    //     }
    //     return sb.toString().trim();
    // }

    public static void main(String[] args) {
        FileResource fr = new FileResource("data/confucius.txt"); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(3); 
        markovWord.setTraining(st);
        markovWord.setRandom(621);
        String out = markovWord.getRandomText(100);
        System.out.println(out);
    }
}
