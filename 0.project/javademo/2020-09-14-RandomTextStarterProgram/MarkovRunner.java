
/**
 * Write a description of class MarkovRunner here.
 * 
 * @Grace Jyl
 * @version 1
 */

import edu.duke.*;

public class MarkovRunner {

    public void runMarkovZero() {
		FileResource fr = new FileResource("data/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');

		MarkovZero markov = new MarkovZero();
		markov.setTraining(st);
		markov.setRandom(88);
		// markov.setRandom(101);  // every time you run it you get the same text.

		for(int k=0; k < 2; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
	

	public void runMarkovOne() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');

		MarkovOne markov = new MarkovOne();
		markov.setTraining(st);
		markov.setRandom(273);
		// markov.setRandom(101);  // every time you run it you get the same text.

		for(int k=0; k < 2; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}


	public void runMarkovFour() {
		FileResource fr = new FileResource("data/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');

		MarkovFour markov = new MarkovFour();
		markov.setTraining(st);
		markov.setRandom(371);
		// markov.setRandom(101);  // every time you run it you get the same text.

		for(int k=0; k < 2; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}


	public void runMarkovN() {
		FileResource fr = new FileResource("data/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');

		MarkovModel markov = new MarkovModel();
		markov.setModel(8);
		markov.setTraining(st);
		markov.setRandom(365);

		for(int k=0; k < 2; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
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
