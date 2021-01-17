
/**
 * Write a description of class MarkovRunner here.
 * 
 * @Grace Jyl
 * @version 1
 */


import lib.edu.duke.*; 

public class MarkovRunnerWithInterface {

	// setup model 
	// This method will work with any markov object that implements IMarkovModel.
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
		System.out.println(markov);    // running with MarkovFour@4b53b922
		markov.setTraining(text);
		markov.setRandom(seed);
        for(int k=0; k < 1; k++){
			String out= markov.getRandomText(size);
			printOut(out);
		}
	}
	
	// give text file, put differne model
	// This method creates one of the types of Markov models, and calls runModel with it to generate random text.
    public void runMarkov() {
        FileResource fr = new FileResource("data/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 500;
		// int seed = 1024;
        // MarkovZero mz = new MarkovZero();
        // runModel(mz, st, size, seed);
        // MarkovOne mOne = new MarkovOne();
        // runModel(mOne, st, size, seed);
        // MarkovModel mThree = new MarkovModel(3);
        // runModel(mThree, st, size, seed);
        // MarkovFour mFour = new MarkovFour();
        // runModel(mFour, st, size, seed);
	}

	public void compareMethods() {
        FileResource fr = new FileResource("data/hawthorne.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 1000;
		int seed = 42;
		
		
		long startTime = System.nanoTime();

        MarkovModel markov = new MarkovModel(2);
		runModel(markov, st, size, seed);

		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);

		System.out.println("-------------------------------------------------------");

		
		startTime = System.nanoTime();

		EfficientMarkovModel effMarkov = new EfficientMarkovModel(2);
		runModel(effMarkov, st, size, seed);

		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
        
	}
	
 
	public void testHashMap() {
        FileResource fr = new FileResource("data/confucius.txt");
		String st = fr.asString();
        // String st = "yes-this-is-a-thin-pretty-pink-thistle";

        EfficientMarkovModel markov = new EfficientMarkovModel(5);
        System.out.println(markov);

		markov.setTraining(st);
        // markov.setRandom(615);
        
        // System.out.println("buildMap(): ");
        // markov.buildMap();

		markov.setRandom(531);
		
        for(int k=0; k < 1; k++){
			String out= markov.getRandomText(50);
			printOut(out);
		}
		
        // System.out.println("printHashMapInfo(): ");
        markov.printHashMapInfo();
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
