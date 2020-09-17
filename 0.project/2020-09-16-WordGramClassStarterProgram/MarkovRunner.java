
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
 
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
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 
 
    public void runMarkov() { 
        FileResource fr = new FileResource("data/confucius.txt"); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        // String st = "this is a test yes this is really a test yes a test this is wow";
        // runModel(IMarkovModel markov, String text, int size)

        // MarkovWordOne markovWord = new MarkovWordOne(); 
        // runModel(markovWord, st, 643); 
        
        // System.out.println("MarkovWord: ");
        // MarkovWord markovWord = new MarkovWord(2); 
        // long start = System.nanoTime();
        // runModel(markovWord, st, 100, 42); 
        // long end = System.nanoTime();
        // System.out.println(end - start);
        
        // System.out.println("EfficientMarkovWord: ");
        // EfficientMarkovWord markovWord2 = new EfficientMarkovWord(2); 
        // start = System.nanoTime();
        // runModel(markovWord2, st, 100, 42); 
        // end = System.nanoTime();
        // System.out.println(end - start);

        
        // EfficientMarkovWord markovWord = new EfficientMarkovWord(3); 
        // runModel(markovWord, st, 100, 621); 

        // EfficientMarkovWord markovWord = new EfficientMarkovWord(5); 
        // runModel(markovWord, st, 100, 844); 

        
        // EfficientMarkovWord markovWord = new EfficientMarkovWord(3); 
        // runModel(markovWord, st, 100, 371); 

        EfficientMarkovWord markovWord = new EfficientMarkovWord(2); 
        runModel(markovWord, st, 100, 65); 
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
            // if (psize > 120) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
