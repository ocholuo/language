import java.util.Comparator;

/**
 * TitleLastAndMagnitudeComparator
 * 
 * @Grace Jyl
 * @version 1
 */


public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {

        String last1 = q1.getInfo().substring(q1.getInfo().lastIndexOf(" ")+1);
        String last2 = q2.getInfo().substring(q2.getInfo().lastIndexOf(" ")+1);

        int compString = last1.compareTo(last2);
        if(compString == 0){
            compString = Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return compString;
    }
}



// public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
//     public int compare(QuakeEntry q1, QuakeEntry q2){
//         String info1 = q1.getInfo();
//         String info2 = q2.getInfo(); 
//         String last1 = info1.substring(info1.lastIndexOf(" ")+1);
//         String last2 = info2.substring(info2.lastIndexOf(" ")+1);
//         int compString = last1.compareTo(last2);
//         if(compString == 0){
//             return Double.compare(q1.getMagnitude(), q2.getMagnitude());
//         }
//         return compString;
//     }
// }


