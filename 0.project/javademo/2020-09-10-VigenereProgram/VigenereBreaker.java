
/**
 *
 * 
 * @author (Grace Jyl) 
 * @version (10/Sep/2020)
 */


import java.util.*;
import lib.edu.duke.*;

public class VigenereBreaker {


    // ------------------------------------------------------------
    // every word in file been put in HashSet<String> dictionaryList, return the HashSet<String> dictionaryList
    // ------------------------------------------------------------
    // The method return the HashSet representing the words in a dictionary.
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionaryList = new HashSet<String>();
        for(String word : fr.lines()){
            dictionaryList.add(word.toLowerCase());
        }
        // System.out.println("readDictionary() -> HashSet<String> readDictionary");
        return dictionaryList;
    }


    // ------------------------------------------------------------
    // return the languages HashMap<languagename, HashSet<String> dictionaryList>
    // ------------------------------------------------------------
    public HashMap<String, HashSet<String>> readDictionaryFromAllLanguage() {
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        // DirectoryResource dr = new DirectoryResource();
        // for( FileResource fr : dr.selectedFiles() ){
        //     String languagename = fr.getName();
        //     HashSet<String> dictionaryList = readDictionary(fr);
        //     languages.put(languagename, dictionaryList);
        // }
        FileResource English = new FileResource("dictionaries/English");
        languages.put("English", readDictionary(English));
        FileResource Danish = new FileResource("dictionaries/Danish");
        languages.put("Danish", readDictionary(Danish));
        FileResource Dutch = new FileResource("dictionaries/Dutch");
        languages.put("Dutch", readDictionary(Dutch));
        FileResource French = new FileResource("dictionaries/French");
        languages.put("French", readDictionary(French));
        FileResource German = new FileResource("dictionaries/German");
        languages.put("German", readDictionary(German));
        FileResource Italian = new FileResource("dictionaries/Italian");
        languages.put("Italian", readDictionary(Italian));
        FileResource Portuguese = new FileResource("dictionaries/Portuguese");
        languages.put("Portuguese", readDictionary(Portuguese));
        FileResource Spanish = new FileResource("dictionaries/Spanish");
        languages.put("Spanish", readDictionary(Spanish));
        // System.out.println("readDictionaryFromAllLanguage() -> HashMap<String, HashSet<String>> AlldictionaryList");
        return languages;
    }


    // ------------------------------------------------------------
    // take a encrypted String, slice it according to keylength
    // ------------------------------------------------------------
    // This method returns a String consisting of every totalSlices-th character from message, starting at the whichSlice-th character.
    // sliceString("abcdefghijklm", 0, 3) should return "adgjm"
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder ans = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i+= totalSlices){
            char currChar = message.charAt(i);
            ans.append(currChar);
        } 

        // System.out.println("sliceString()");
        return ans.toString();
    }


    // ------------------------------------------------------------
    // take a encrypted String, get the SliceEach by keylength, new CaesarCracker(), cc.getKey(SliceEach) each SliceEach return a key, add to int[]
    // ------------------------------------------------------------
    // This method:
    // gets a String (a encrypted text as a String from another method), 
    // slices it in an amount of portions as big as the key length, 
    // calculates the key for each portion of encrypted string sliced. 
    // After that, gets every key and stores it in an Array, returned at the end
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker();
        for(int i = 0; i < klength; i++){
            String SliceEach = sliceString(encrypted, i, klength);
            int newKey = cc.getKey(SliceEach);
            key[i] = newKey;
        }
        // System.out.println("tryKeyLength() -> int[] key ");
        return key;
    }



    // ------------------------------------------------------------
    // take a encrypted String, .tryKeyLength() to get the key Arraylist, create VigenereCipher(int[] key) to decrypt it.
    // ------------------------------------------------------------
    // This void method should put everything together, so that you can create a new VigenereBreaker in BlueJ, call this method on it, and crack the cipher used on a message.
    public void breakVigenereforOneLanguage (int keylength) {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();

        // int[] key = tryKeyLength(encrypted, 4, 'e');
        FileResource f = new FileResource("dictionaries/English");
        HashSet<String> dictionaryList = readDictionary(f);
        int[] key = tryKeyLength(encrypted, keylength, mostCommonCharIn(dictionaryList));

        // for(int i = 0; i < key.length; i++){
        //     System.out.println(key[i]);
        // }
    
        VigenereCipher vc = new VigenereCipher(key);
        String answer = vc.decrypt(encrypted);
        // System.out.println("breakVigenere() -> plaintext: ");
        System.out.println(answer);
    }



    // ------------------------------------------------------------
    // find how many word in text is in HashSet<String> dictionaryList
    // ------------------------------------------------------------
    // This method should return the integer count of how many valid words it found.
    public int countWords(String text, HashSet<String> dictionaryList) {
        String[] messageSplit = text.toLowerCase().split("\\W+");
        int commonWords = 0;
        for(int i=0; i < messageSplit.length; i++){
            String word = messageSplit[i];
            if (dictionaryList.contains(word)){
                commonWords++;
            }
        }
        // System.out.println("countWords() -> " + commonWords + " words in text is in HashSet<String> dictionaryList");
        return commonWords;
    }



    // ------------------------------------------------------------
    // return this most commonly occurring character in HashSet<String> dictionaryList
    // ------------------------------------------------------------
    public char mostCommonCharIn(HashSet<String> dictionaryList) {
        HashMap<Character,Integer> countChar = new HashMap<Character,Integer>();
        int maxNum = 0;
        char maxChar = 'e';
        for(String wrd : dictionaryList){
            for(int i=0; i < wrd.length(); i++){
                char c = wrd.charAt(i);
                if( !countChar.containsKey(c)){
                    countChar.put(c,1);
                }
                else{
                    countChar.put(c,countChar.get(c)+1);
                }
            }
        }
        for(char c : countChar.keySet()){
            if( maxNum < countChar.get(c)){
                maxNum = countChar.get(c);
                maxChar = c;
            }
        }
        // System.out.println("mostCommonCharIn() -> the most Common Char In dictionaryList is: " + maxChar);
        return maxChar;
    }


    
    // ------------------------------------------------------------
    // try all key lengths from 1 to 100 (tryKeyLength method)
    // For each key length, decrypt the message (VigenereCipher’s decrypt method)
    // count how many of the “words” in it are in dictionary (countWords method). 
    // ------------------------------------------------------------
    // This method should figure out which decryption gives the largest count of real words, and return that String decryption. Note that there is nothing special about 100; we will just give you messages with key lengths in the range 1–100. If you did not have this information, you could iterate all the way to encrypted.length(). Your program would just take a bit longer to run.
    public String breakForLanguage(String encrypted, HashSet<String> dictionaryList) {
        int maxcommonwrd = 0;
        int[] rightkey = new int[100];
        String plaintext = "";
        for(int i = 2; i <= 100; i++){
            // plaintext = breakVigenere (i);
            int[] key = tryKeyLength(encrypted, i, mostCommonCharIn(dictionaryList));
            VigenereCipher vc = new VigenereCipher(key);
            String answer = vc.decrypt(encrypted);

            int commonWords = countWords(answer, dictionaryList);
            if(maxcommonwrd < commonWords){
                maxcommonwrd = commonWords;
                plaintext = answer;
                rightkey = key;
            }
        }
        System.out.println("breakForLanguage() ->");
        System.out.println("valid words in the decrypted String: " + maxcommonwrd);
        System.out.println("the key size is: " + rightkey.length);
        System.out.println("the key is: ");
        for(int i = 0; i < rightkey.length; i++){
            System.out.print(rightkey[i] + " ");
            System.out.println();
        }
        System.out.println("the plaintext is: ");
        System.out.println(plaintext);
        return plaintext;
    }

    
    public void breakVigenereForAllLangs (String encrypted, HashMap<String, HashSet<String>> languages) {
        for(String Onelanguage : languages.keySet()){
            System.out.println("Test The Language: " + Onelanguage);
            HashSet<String> dictionaryList = languages.get(Onelanguage);
            String plaintext = breakForLanguage(encrypted, dictionaryList);
        }
    }



    public static void main(String[] args) {
        VigenereBreaker pr = new VigenereBreaker();

        FileResource fr = new FileResource("messages/secretmessage4.txt");
        String encrypted = fr.asString();


        FileResource languagefile = new FileResource("dictionaries/French");
        HashSet<String> dictionaryList = pr.readDictionary(languagefile);

        // System.out.println("---------------test when know keylength---------------");
        // int []key = pr.tryKeyLength(encrypted,38,'e');
        // // for (int i=0; i< key.length; i++){
        // //     System.out.println(key[i]);
        // // }
        // VigenereCipher vc = new VigenereCipher(key);
        // String answer = vc.decrypt(encrypted);
        // System.out.println("breakVigenere() -> plaintext: ");
        // System.out.println(answer);
        
        // int commonWords = pr.countWords(answer, dictionaryList);
        // System.out.println("valid words in the decrypted String: " + commonWords);


        // System.out.println("---------------test when not know keylength---------------");
        // String plaintext = pr.breakForLanguage(encrypted, dictionaryList);


        System.out.println("---------------test multiple language---------------");
        HashMap<String, HashSet<String>> languages = pr.readDictionaryFromAllLanguage();
        pr.breakVigenereForAllLangs(encrypted, languages);
    }

}
