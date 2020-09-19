/**
 * class that represents words that have an ordering
 * 
 * @Grace Jyl
 * @version 1
 */


import java.lang.reflect.Array;
import java.util.*;

// The class WordGram is a class that represents words that have an ordering. 
// For example, a WordGram might be the four words “this” “is” “a” test”, in this order.
public class WordGram {

    // to store the words in order, one word per slot
    private String[] myWords;
    // use WordGrams as a key with a HashMap
    private int myHash;


    // The constructor 
    // copies the size number of words from source 
    // starting at the position start into a new WordGram.
    // create myWords.
    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
        // copies an array from srcPos with size to destPos
        // arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
    }

    // return the String at myWords String[index]
    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    // return the myWords String[] length
    public int length(){
        return myWords.length;
    }

    // print the myWords
    public String toString(){
        String ret = "";
        for(int i = 0; i < myWords.length; i++){
            ret += myWords[i] + " ";
        }
        return ret.trim();
    }


    // This method returns true if two WordGrams are equal and false otherwise. 
    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(this.length() != other.length()){
            return false;
        }
        for(int k=0; k < this.length()-1; k++){
            if( !myWords[k].equals(other.wordAt(k))){
                return false;
            }
        }
        return true;
    }


    // shift all words one towards 0 and add word at the end. 
    // you lose the first word
    public WordGram shiftAdd(String word) {	

        String[] newWords = new String[this.length()];
        for (int i=0 ; i<newWords.length-1 ; i++) {
        	newWords[i] = this.wordAt(i+1);
        }
        newWords[newWords.length-1] = word;

        WordGram out = new WordGram(newWords, 0, newWords.length);
        return out;
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

    
    public static void main(String[] args) {


        String[] source = {"this", "is", "a", "test"};
        int start = 0;
        int size = 4;

        WordGram wg = new WordGram(source, start, size);
        WordGram newwg = wg.shiftAdd("yes");
        
        System.out.println(wg);
        System.out.println(newwg);

    }

}