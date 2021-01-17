
/**
 * Write a description of class MarkovZero here.
 * 
 * @Grace Jyl
 * @version 1
 */

import java.util.ArrayList;
import java.util.Random;
import lib.edu.duke.*;


public class Test {
    
    public void testGetFollows(){
        String st = "this is a test yes this is a test.";

        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);

        System.out.println("test testGetFollows():");
        ArrayList<String> follows = markov.getFollows("es");
        for(String wrd : follows){
            System.out.print(wrd + ",");
        }
    }

        
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.trim();

        MarkovTwo markov = new MarkovTwo();
        markov.setTraining(st);
        // markov.setRandom(42);

        System.out.println("test testGetFollowsWithFile():");
        ArrayList<String> follows = markov.getFollows("he");
        int count = 0;
        for(String wrd : follows){
            // System.out.print(wrd + ",");
            count++;
        }
        System.out.println("look for the characters that follow 't'. You should get " + count);

    }
}

