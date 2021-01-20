
/**
 * Write a description of class MarkovRunner here.
 * 
 * @Grace Jyl
 * @version 1
 */

import java.lang.reflect.Array;
import java.util.*;
import edu.duke.*;

public class MarkovRunner {

    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    }  

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource("data/confucius.txt"); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordOne markovWord = new MarkovWordOne(); 
        // runModel(markovWord, st, 120); 
        runModel(markovWord, st, 120, 139);
    } 

    public void runMarkov2() { 
        FileResource fr = new FileResource("data/confucius.txt"); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordTwo markovWord2 = new MarkovWordTwo(); 
        runModel(markovWord2, st, 50, 832); 
    } 



    public void testing() {
        String[] myText = {"this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test"};
		MarkovWordOne markov = new MarkovWordOne();
		markov.textindexOf();
		ArrayList<String> follows = markov.getFollows("is");
    }



    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
