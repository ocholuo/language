import edu.duke.*;
import java.util.*;

public class VigenereCipher {
    
    CaesarCipher[] ciphers;

    // ------------------------------------------------------------
    // int[] key = key1, key2, ...
    // return CaesarCipher[] ciphers = CaesarCipher(key1), CaesarCipher(key2) ...
    // ------------------------------------------------------------
    // the constructor, takes a key (an array of integers) and initializes the field ciphers(an array of CaesarCipher objects).
    public VigenereCipher(int[] key) {
        ciphers = new CaesarCipher[key.length];
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }


    // ------------------------------------------------------------
    // takes input, each char c has a charindex, encryoted char with match CaesarCipher[] ciphers, an d add it to String answer.
    // ------------------------------------------------------------
    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.encryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    


    // ------------------------------------------------------------
    // takes input, each char c has a charindex, encryoted char with match CaesarCipher[] ciphers, an d add it to String answer.
    // ------------------------------------------------------------
    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    

    public String toString() {
        return Arrays.toString(ciphers);
    }
    
}
