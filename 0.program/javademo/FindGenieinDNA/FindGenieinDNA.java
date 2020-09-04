

import java.lang.Object;
import edu.duke.URLResource;


// Nucleotide =+ Condon
// ATG start Condon
// TAA stop Condon


public class FindGenieinDNA {
    

    // Part 1: Finding a Gene - Using the Simplified Algorithm
    // public String findGeneSimple(String dna){
    //     String result = "";
    //     int startIndex = dna.indexOf("ATG");
    //     if(startIndex == -1){
    //         return "cannot find ATG";
    //     }
    //     int currIndex = dna.indexOf("TAA", startIndex+3);
    //     while(currIndex != -1){
    //         if((currIndex - startIndex) % 3 == 0){
    //             result = dna.substring(startIndex, currIndex+3);
    //             return result;
    //         }
    //         else {
    //             currIndex = dna.indexOf("TAA", currIndex+1);
    //         }
    //     }
    //     return "cannot find TAA";
    // }


    // public void testSimpleGene() {
    //     String dna1 = "ATGxxxTAAxxxyyyxxx";
    //     String dna2 = "ATGxxTAAxxxyyyxxx";
    //     String gene1 = findGeneSimple(dna1);
    //     String gene2 = findGeneSimple(dna2);
    //     System.out.println("DNA stand:" + dna1);
    //     System.out.println("Gene is:" + gene1);
    //     System.out.println("DNA stand:" + dna2);
    //     System.out.println("Gene is:" + gene2);
    // }



    // better code
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = taaIndex;
        if(taaIndex == -1 || tgaIndex != -1 && tgaIndex < taaIndex){
            minIndex = tgaIndex;
        }
        if(minIndex == -1 || tagIndex != -1 && tagIndex < minIndex){
            minIndex = tagIndex;
        }
        if(minIndex == -1){
            return "";
        }
        return dna.substring(startIndex, minIndex+3);
    }


    public int findStopCodon(String dna, int startIndex, String stopCondon){
        int currIndex = dna.indexOf(stopCondon, startIndex+3);
        while(currIndex != -1){
            if((currIndex - startIndex) % 3 == 0){
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCondon, currIndex+1);
            }
        }
        return -1;
    }


    // public void testFindStop() {
    //     String dna = "xxxyyyxxxATGxxxTAAxx";
    //     int stopCondon = findStopCodon(dna, 0, "TAA");
    //     System.out.println("DNA stand:" + dna);
    //     System.out.println("stopCondon position is:" + stopCondon);
    // }



    // find multiple Genes
    public int findMultipleGene(String dna){
        int count = 0;
        int startIndex = 0;
        while(true){
            String currGene = findGene(dna, startIndex);
            if(currGene.isEmpty()){
                break;
            }
            System.out.println(currGene);
            count++;
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return count;
    }



    // Part 3: Problem Solving with Strings
    // Write the method named twoOccurrences that has two String parameters named stringa and stringb. This method returns true if stringa appears at least twice in stringb, otherwise it returns false. For example, the call twoOccurrences(“by”, “A story by Abby Long”) returns true as there are two occurrences of “by”, the call twoOccurrences(“a”, “banana”) returns true as there are three occurrences of “a” so “a” occurs at least twice, and the call twoOccurrences(“atg”, “ctgtatgta”) returns false as there is only one occurence of “atg”.

    // public boolean twoOccurrences(String smalldna, String bigdna){
    //     String gene1 = findGeneSimple(smalldna);
    //     if(bigdna.indexOf(gene1) != -1){
    //         int fristIndex = bigdna.indexOf(gene1);
    //         if(bigdna.indexOf(gene1, fristIndex+3) != -1){
    //             return true;
    //         }
    //     }
    //     return false;
    // } 

    // Write the method named lastPart that has two String parameters named stringa and stringb. This method finds the first occurrence of stringa in stringb, and returns the part of stringb that follows stringa. If stringa does not occur in stringb, then return stringb. For example, the call lastPart(“an”, “banana”) returns the string “ana”, the part of the string after the first “an”. The call lastPart(“zoo”, “forest”) returns the string “forest” since “zoo” does not appear in that word.

    // public String lastPart(String stringa, String stringb) {
    //     if(stringb.indexOf(stringa) != -1){
    //         int beginIndex = stringb.indexOf(stringa) + stringa.length();
    //         String result = stringb.substring(beginIndex, stringb.length());
    //         return result;
    //     }
    //     return "";
    // }



    public static void main(String[] args) {
        FindGenieinDNA pr = new FindGenieinDNA();

        // System.out.println("test testSimpleGene()");
        // pr.testSimpleGene();

        // System.out.println("test twoOccurrences()");
        // if(pr.twoOccurrences(dna1, dna2)){
        //     System.out.println("there are two occurrences of dna gene of small dna in big dna.");
        // }

        // System.out.println("test lastPart()");
        // String stringa = "an";
        // String stringb = "banana";
        // String result = pr.lastPart(stringa, stringb);
        // System.out.println("The part of the string after '" + stringa + "' in '" + stringb + "': " + result);

        // System.out.println("test testFindStop()");
        // pr.testFindStop();

        // System.out.println("test findGene()");
        // String result = pr.findGene("xxxyyyxxxATGxxxTAAxx");
        // System.out.println(result);

        System.out.println("test findMultipleGene()");
        //                              ATCv  TAAv  ATGv  v  v  TGAv
        int num1 = pr.findMultipleGene("ATCoooTAAoooATGoooooooooTGAooo");
        //                              ATGv  v  v  v  TAAv  v  v  ATGTAA
        int num2 = pr.findMultipleGene("ATGooooooooooooTAAoooooooooATGTAA");
        System.out.println("num1 has: " + num1 + "Gene");
        System.out.println("num2 has: " + num2 + "Gene");
    }

}
