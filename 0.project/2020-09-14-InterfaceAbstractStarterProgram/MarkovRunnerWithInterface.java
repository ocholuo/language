
/**
 * Write a description of class MarkovRunner here.
 * 
 * @Grace Jyl
 * @version 1
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {

	// setup model 
	// This method will work with any markov object that implements IMarkovModel.
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
		markov.setTraining(text);
		markov.setRandom(seed);
        System.out.println("running with " + markov);    // running with MarkovFour@4b53b922
        // for(int k=0; k < 1; k++){
		// 	String st= markov.getRandomText(size);
		// 	printOut(st);
		// }
	}
	
	// give text file, put differne model
	// This method creates one of the types of Markov models, and calls runModel with it to generate random text.
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 50;
		int seed = 20;
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);
	}
	

	public void testHashMap() {
		String st = "yes-this-is-a-thin-pretty-pink-thistle";
		int size = 50;
		int seed = 42;
        EfficientMarkovModel mz = new EfficientMarkovModel(2);
		runModel(mz, st, size, seed);
		mz.printHashMapInfo();
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
